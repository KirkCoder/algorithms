package crackin.StacksAndQueues

import Queue
import SingleLinkedList
import showIterable

//Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first out"basis. People must adopt either the "oldest"(based on arrival time) of all animals at the shelter, or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal they would like. Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat. You may use the built-in LinkedList data structure.

fun main() {
    val shelter = AnimalShelter()

    shelter.enqueue(Cat(1))
    shelter.enqueue(Cat(2))
    shelter.enqueue(Dog(3))
    shelter.enqueue(Cat(4))
    shelter.enqueue(Cat(5))
    shelter.enqueue(Dog(6))
    shelter.enqueue(Dog(7))

    shelter.show()
    shelter.dequeueCat()
    shelter.show()
    shelter.dequeueDog()
    shelter.show()
    shelter.dequeueAny()
    shelter.show()
}

class AnimalShelter {

    private val cats = Queue<Animal>()
    private val dogs = Queue<Animal>()
    private val list = SingleLinkedList<Animal>()

    fun enqueue(animal: Animal) {
        when (animal) {
            is Dog -> {
                dogs.add(animal)
            }
            is Cat -> {
                cats.add(animal)
            }
        }
        list.add(animal)
    }

    fun dequeueAny(): Animal {
        val animalNode = list.start
        list.start = list.start?.next
        return when (animalNode?.value) {
            is Dog -> {
                dogs.remove()
            }
            is Cat -> {
                cats.remove()
            }
            else -> {
                throw NoSuchAnimalException()
            }
        }
    }

    fun dequeueCat(): Animal {
        return dequeueAnimal(cats)
    }

    fun dequeueDog(): Animal {
        return dequeueAnimal(dogs)
    }

    private fun dequeueAnimal(animals: Queue<Animal>): Animal {
        val animal = animals.remove()
        val i = list.nodesIterator()
        var prev = i.next()
        if (prev.value.id == animal.id) {
            list.start = list.start?.next
        }
        while (i.hasNext()) {
            val next = i.next()
            if (next.value.id == animal.id) {
                prev.next = next.next
                break
            }
            prev = next
        }
        return animal
    }

    class NoSuchAnimalException : Throwable()

    fun show() {
        showIterable(list.iterator())
        println("cats: ${cats.show()}")
        println("dogs: ${dogs.show()}")
        println("=====")
    }
}


abstract class Animal {
    abstract val id: Int
}

data class Dog(override val id: Int) : Animal()
data class Cat(override val id: Int) : Animal()










