package y.m

import showIterable

/**
Разворот списка
PRB 83
Описание
Имеется односвязный список, заданный ссылкой head на первый элемент. Предложите и реализуйте алгоритм, который осуществляет разворот списка: получается новый список, элементы которого следуют в обратном порядке относительно исходного списка.
Задача спалена на Glassdoor
Публикации на внешних ресурсах
Что хотим услышать
public Element reverse(Element head) {
Element next = null;
Element current = head;
Element prev = null;
while (current != null) {
next = current.next;
current.next = prev;
prev = current;
current = next;
}
return prev;
}
Было раньше (исправлено ignat@):
--1) Оптимальный алгоритм (один проход, три переменные).
Какая переменная содержит возвращаемое значение.
Опционально: что такое список, свойства списка как структуры данных.--
Сначала надо поговорить с кандидатом про то, что такое односвязный список и как он представлен в памяти.
В итоге требуется получить решение за линейное время, лучше всего в ходе обсуждения придти с кандидатом к решению за один проход.
 */

fun main() {
    val list = ReverseLinkedList<Int>()
    for (i in 0..20) {
        list.add(i)
    }
    showIterable(list.iterator())
    list.reverse()
    showIterable(list.iterator())
}

class ReverseLinkedList<T> : Iterable<T> {

    private var start: Node<T>? = null

    fun add(value: T) {
        val node = Node(value)
        val tmpStart = start
        if (tmpStart == null) {
            start = node
        } else {
            var current = tmpStart
            var next = tmpStart.next
            while (next != null) {
                current = next
                next = current.next
            }
            current!!.next = node
        }
    }

    fun reverse() {
        var prev: Node<T>? = null
        var current = start
        while (current != null) {
            val tmp = current.next
            current.next = prev
            prev = current
            current = tmp
        }
        start = prev
    }

    override fun iterator(): Iterator<T> {
        return ReverseLinkedListIterator(start)
    }

    private class Node<T>(
        val value: T,
        var next: Node<T>? = null
    )

    private class ReverseLinkedListIterator<T>(
        private var node: Node<T>?
    ) : Iterator<T> {

        override fun hasNext() = node != null

        override fun next(): T {
            val current = node ?: throw NoSuchElementException()
            node = current.next
            return current.value
        }

    }

}

































