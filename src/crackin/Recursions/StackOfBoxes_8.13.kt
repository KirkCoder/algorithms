package crackin.Recursions

import java.util.*

data class Box(val h: Int, val w: Int, val d: Int)

//Stack of Boxes: You have a stack of n boxes, with widths Wi' heights hi' and depths d • The boxes
//1
//cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly larger than the box above it in width, height. and depth. Implement a method to compute the height of the tallest possible stack. The height of a stack is the sum of the heights of each box.

fun getMaxStack(boxes: List<Box>): List<Box> {
    var maxStack = emptyList<Box>()
    var maxStackSum = 0
    for (box in boxes) {
        val availableBoxes = boxes.filter { it != box }
        val stack = LinkedList<Box>()
        stack.add(box)
        val tmpStack = getMaxStack(stack, availableBoxes)
        val tmpStackSum = tmpStack.map { it.h }.sum()
        if (tmpStackSum > maxStackSum) {
            maxStack = tmpStack
            maxStackSum = tmpStackSum
        }
    }
    return maxStack
}

fun getMaxStack(stack: LinkedList<Box>, boxes: List<Box>): LinkedList<Box> {
    val last = stack.getLast()
    val availableBoxes = boxes.filter { box ->
        box.h < last.h && box.w < last.w && box.d < last.d
    }
    var maxStack = stack
    var maxStackSum = stack.map { it.h }.sum()
    for (box in availableBoxes) {
        val tmpStack = stack.toLinkedList()
        val tmpBoxes = availableBoxes.filter { it != box }
        tmpStack.add(box)
        val newStack = getMaxStack(tmpStack, tmpBoxes)
        val newSum = newStack.map { it.h }.sum()
        if (newSum > maxStackSum) {
            maxStackSum = newSum
            maxStack = newStack
        }
    }
    return maxStack
}

private fun List<Box>.toLinkedList(): LinkedList<Box> {
    val list = LinkedList<Box>()
    for (b in this) {
        list.add(b)
    }
    return list
}