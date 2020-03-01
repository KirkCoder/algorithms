package y.m

import SingleLinkedList

/**
 * N-ый с конца элемент
PRB 107
Описание
Есть односвязный список, заданный указателем на его начало. Нужно написать функцию, которая возвращает N-ый с конца элемент. Последний элемент считается 0-м с конца.
Например, есть список A → B → C → D → E → F и число 2. Нужно вернуть значение элемента D.
Какая структура данных?
Что будет, если список короче, чем запрошенное число?
Copy paste условия
Тестовые данные
Что хотим услышать
Каноническое решение — за один проход с использованием двух указателей. Первый сдвигаем на N, потом идем сразу двумя до конца.
https://medium.com/@vitkarpov/cracking-the-coding-interview-2-2-9565da9b6fc0
 */

fun main() {
    val list = SingleLinkedList<Int>()
    for (i in 0 .. 2) {
        println(i)
        list.add(i)
    }
    println(getElementAtIndexFromEnd(list, 1))
}

fun<T> getElementAtIndexFromEnd(list: SingleLinkedList<T>, index: Int): T {
    val firstIterator = list.iterator()
    val secondIterator = list.iterator()
    for (i in 0 .. index) {
        if (firstIterator.hasNext()) {
            firstIterator.next()
        } else {
            throw NoSuchElementException()
        }
    }

    var result = secondIterator.next()

    while (firstIterator.hasNext()) {
        firstIterator.next()
        result = secondIterator.next()
    }
    return result
}