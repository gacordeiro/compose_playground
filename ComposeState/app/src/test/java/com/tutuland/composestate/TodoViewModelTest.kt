package com.tutuland.composestate

import com.google.common.truth.Truth.assertThat
import com.tutuland.composestate.todo.TodoViewModel
import com.tutuland.composestate.util.generateRandomTodoItem
import org.junit.Test

class TodoViewModelTest {
    @Test
    fun `when removing item, updates list`() {
        val viewModel = TodoViewModel()
        val item1 = generateRandomTodoItem()
        val item2 = generateRandomTodoItem()
        viewModel.addItem(item1)
        viewModel.addItem(item2)

        viewModel.removeItem(item1)

        assertThat(viewModel.todoItems).isEqualTo(listOf(item2))
    }
}