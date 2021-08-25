package com.tutuland.compose02

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tutuland.compose02.ui.theme.Compose02Theme

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Composable
fun TextWithPaddingToBaselinePreview() {
    Compose02Theme {
        Text(text = "The BaSeLiNe RuLeZ!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Composable
fun TextWithNormalPaddingPreview() {
    Compose02Theme {
        Text(text = "The BaSeLiNe RuLeZ!", Modifier.padding(top = 32.dp))
    }
}
