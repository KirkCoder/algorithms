package y.m

import generateArray
import java.util.*
import kotlin.NoSuchElementException

/**
Мердж нескольких итераторов
PRB 4708
Описание
Написать функцию, на вход которой подается несколько итераторов, которые возвращают элементы в сортированном виде. Функция возвращает итератор по отсортированным значениям.
Аналог питоновского heapq.merge
https://docs.python.org/2/library/heapq.html#heapq.merge
Что хотим услышать
Ожидаем, что алгоритм не будет получать все значения в память.
 */

fun main() {
    val a1 = generateArray()
    a1.sort()
    val i1 = a1.iterator()
    val a2 = generateArray()
    a2.sort()
    val i2 = a2.iterator()
    val a3 = generateArray()
    a3.sort()
    val i3 = a3.iterator()
    val mi = MergeIterator(i1, i2, i3)
    while (mi.hasNext()) {
        print("${mi.next()}, ")
    }
}

class MergeIterator<T : Comparable<T>>(private vararg val iterators: Iterator<T>) : Iterator<T> {

    private val elements = LinkedList<T>()

    override fun hasNext(): Boolean {
        for (iterator in iterators) {
            if (iterator.hasNext()) return true
        }
        return elements.isNotEmpty()
    }

    override fun next(): T {
        var minValue: T? = if (elements.isNotEmpty()) elements.pop() else null
        for (iterator in iterators) {
            if (iterator.hasNext()) {
                val value = iterator.next()
                if (minValue == null) {
                    minValue = value
                }
                if (minValue > value) {
                    addElementToCache(minValue)
                    minValue = value
                } else {
                    addElementToCache(value)
                }
            }
        }
        if (minValue == null) throw NoSuchElementException()
        return minValue
    }

    private fun addElementToCache(element: T) {
        for (i in elements.indices) {
            if (elements[i] >= element) {
                elements.add(i, element)
                return
            }
        }
        elements.add(elements.size, element)
    }

}