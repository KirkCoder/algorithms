package y.m

import showList
import java.lang.StringBuilder

/**
Свертка списка в диапазоны
PRB 3980
Описание
Дан список интов, повторяющихся элементов в списке нет. Нужно преобразовать это множество в строку, сворачивая соседние по числовому ряду числа в диапазоны.
Примеры:
[1,4,5,2,3,9,8,11,0] => "0-5,8-9,11"
[1,4,3,2] => "1-4"
[1,4] => "1,4"
Что хотим услышать
Сортировка плюс один проход.
Типовые ошибки:
Выход за границу массива на пустом массиве
Выход за границу массива на массиве из 1 элемента (из-за того, что нужно сравнивать два элемента: текущий с предыдущим или текущий со следующим)
Забывают закрыть последний интервал или делают это неправильно
Неправильно пишут интервал из одного элемента (например "2-2")
Забывают ставить разделители-запятые между интервалами, или ставят лишнюю в самом конце строки-результата
 */

fun main() {
    val intList = listOf(1, 4, 5, 2, 3, 9, 8, 11, 0)
    val intList2 = listOf(69, 1, 4, 3, 2, 67, 68)
    showList(intList2.sorted())
    println(ListToRange(intList2).getRange())
}

class ListToRange(
    list: List<Int>
) {

    private val sortedList = list.sorted()

    fun getRange(): String {
        val current = sortedList.firstOrNull() ?: return ""
        var state: State = State.Start(current)
        val result = StringBuilder()
        for (i in 1 until sortedList.size) {
            state = state.getNextStateAndAppendResult(sortedList[i], result)
        }
        state.draw(result)
        return result.toString()
    }

    sealed class State {
        abstract val current: Int
        abstract fun getNextStateAndAppendResult(value: Int, result: StringBuilder): State
        fun draw(result: StringBuilder) {
            result.append(current)
        }

        data class Start(override val current: Int) : State() {
            override fun getNextStateAndAppendResult(value: Int, result: StringBuilder): State {
                return if (value - current == 1) {
                    result.append("$current-")
                    Range(value)
                } else {
                    result.append("$current, ")
                    Start(value)
                }
            }
        }

        data class Range(override val current: Int) : State() {
            override fun getNextStateAndAppendResult(value: Int, result: StringBuilder): State {
                return if (value - current == 1) {
                    Range(value)
                } else {
                    result.append("$current, ")
                    Start(value)
                }
            }
        }
    }
}






















