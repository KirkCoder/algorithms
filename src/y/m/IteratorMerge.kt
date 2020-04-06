package y.m

import generateArray
import showIterable

/**
Написать мульти-итератор
PRB 2194
Описание
Написать класс  MultiIterator реализующий конкатенацию двух итераторов.
class MultiIterator<T> implements Iterator<T> {
private Iterator<T> crackin.a;
private Iterator<T> crackin.b;

public T next();
public boolean hasNext();
public void remove();
}
Документация для интерфейса  Iterator есть тут.
Что хотим услышать
Работающий код с краевыми случаями.
Писать можно и на С++, принципиальных отличий нет.

 */


fun main() {
    val i1 = generateArray(length = 10)
    showIterable(i1.iterator())
    val i2 = generateArray(length = 10)
    showIterable(i2.iterator())
    val i = IteratorMerge(i1.iterator(), i2.iterator())
    showIterable(i)
}

class IteratorMerge<T>(
    private vararg val iterators: Iterator<T>
) : Iterator<T> {

    private var position = 0

    override fun hasNext(): Boolean {
        if (position > iterators.size - 1) return false
        if (iterators[position].hasNext()) return true
        position++
        return hasNext()
    }

    override fun next(): T {
        if (position > iterators.size - 1) throw NoSuchElementException()
        return iterators[position].next()
    }
}