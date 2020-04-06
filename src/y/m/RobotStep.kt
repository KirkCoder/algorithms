package y.m

import java.util.concurrent.Semaphore

private const val PERMITS = 2

/**
 * Шагающий робот
PRB 239
Описание
Научите робота ходить "по-человечески": шаги правой и левой ногой строго по очереди
class Foot implements Runnable {
private final String name;

public Foot(String name) {
this.name = name;
}

public void run() {
for (;;)
step();
}

private void step() {
System.out.println("Step by " + name);
}
}

public class Robot {
public static void main(String[] args){
new Thread(new Foot("left")).start();
new Thread(new Foot("right")).start();
}
}
После успешного решения изначальной задачи, предложить переделать код под "робота-сороконожку".
Что хотим услышать
[30 мин, макс. 6 балла]
Рабочий код:
без spin-waiting
со spin-waiting, но с объяснением, в каких условиях это применимо (некритично потребление CPU, но критична скорость, на этом примере spin-waiting работает в три раза быстрее)
 */

fun main() {
    val leftPermit = 2
    val rightPermit = 1
    val semaphore = Semaphore(PERMITS)
    semaphore.acquire(leftPermit)
    Thread(Foot("left", semaphore, leftPermit, rightPermit)).start()
    Thread(Foot("right", semaphore, rightPermit, leftPermit)).start()
}

class Foot(
    private val name: String,
    private val semaphore: Semaphore,
    private val permit: Int,
    private val otherPermit: Int
) : Runnable {
    override fun run() {
        while (true) step()
    }

    private fun step() {
        try {
            if (semaphore.availablePermits() == PERMITS - otherPermit) {
                semaphore.release(otherPermit)
                println("Step by $name")
                semaphore.acquire(permit)
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}