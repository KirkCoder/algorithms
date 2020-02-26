package y.m

/**
Удалить смайлики вида ":-)))" и ":-((" из сообщения
PRB 4669
Описание
Есть сообщения из соцсети, например:
"Я работаю в Гугле :-)))"
"везет :-) а я туда собеседование завалил:-(("
"лол:)"
"Ааааа!!!!! :-))(())"
Хочется удалить из них смайлики, подпадающие под регулярку  ":-\)+|:-\(+" за линейное время. То есть, сделать так:
"Я работаю в Гугле "
"везет  а я туда собеседование завалил"
"лол:)"
"Aaaaa!!!!! (())"
Удалять вложенные смайлики не требуется (как в  :-:-)))((( ).
Что хотим услышать
Можно быстро обсудить как это сделать с помощью регулярных выражений на любимом языке кандидата или sed. Но требуем написать алгоритм руками.
Нужен однопроходный алгоритм а-ля поддержание состояния автомата, например, текущей длины заматчившегося смайлика.
Однако нужно внимательно следить за кандидатом: некоторые варианты реализации автомата могут быть довольно массивны по коду, из-за чего можно просто не успеть все-все написать. Отчасти это возникает из-за "что и когда писать в выход". В таких случаях не возбраняется помочь кандидату упростить автомат.
Есть альтернативное решение на базе идеи "пытаться приложить смайл на каждую позицию в строке".
Опционально можно попросить константу доп. памяти.
 */

private val withSmiles = "\"Я работаю в Гугле :-)))\"\n" +
        "\"везет :-) а я туда собеседование завалил:-((\"\n" +
        "\"лол:)\"\n" +
        "\"Ааааа!!!!! :-))(())\""

fun main() {

    RemoveWithRegex(withSmiles).showRes()

    val stateMachine = StateMachine()
    val chars = withSmiles.toCharArray()
    val result = StringBuilder()
    for (char in chars) {
        stateMachine.addChar(result, char)
    }
    println(result.toString())
}

class RemoveWithRegex(private val str: String) {
    fun showRes() {
        val reg = Regex("[:][\\-][)]+")
        val reg2 = Regex("[:][\\-][(]+")
        val result = str.replace(reg, "").replace(reg2, "")
        println(result)

        println()
        println()
        println()
    }
}

class StateMachine {

    companion object {
        private const val DOTS = ':'
        private const val MINUS = '-'
        private const val OPEN_BASKET = ')'
        private const val CLOSE_BASKET = '('
    }

    private var state: State = State.Empty

    fun addChar(sb: StringBuilder, char: Char) {
        state = state.addChar(sb, char)
    }

    sealed class State {

        abstract fun addChar(sb: StringBuilder, char: Char): State

        object Empty : State() {
            override fun addChar(sb: StringBuilder, char: Char): State {
                return if (char == DOTS) {
                    First
                } else {
                    sb.append(char)
                    Empty
                }
            }
        }

        object First : State() {
            override fun addChar(sb: StringBuilder, char: Char): State {
                return if (char == MINUS) {
                    Second
                } else {
                    sb.append(DOTS)
                    sb.append(char)
                    Empty
                }
            }
        }

        object Second : State() {
            override fun addChar(sb: StringBuilder, char: Char): State {
                return when (char) {
                    OPEN_BASKET -> Basket.Open
                    CLOSE_BASKET -> Basket.Close
                    else -> {
                        sb.append(DOTS)
                        sb.append(MINUS)
                        sb.append(char)
                        Empty
                    }
                }
            }
        }

        sealed class Basket : State() {

            object Open : Basket() {
                override fun addChar(sb: StringBuilder, char: Char): State {
                    return if (char == OPEN_BASKET) {
                        this
                    } else {
                        sb.append(char)
                        Empty
                    }
                }
            }

            object Close : Basket() {
                override fun addChar(sb: StringBuilder, char: Char): State {
                    return if (char == CLOSE_BASKET) {
                        this
                    } else {
                        sb.append(char)
                        Empty
                    }
                }
            }
        }
    }
}