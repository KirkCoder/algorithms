package leetcode.facebook
//
//Validate if a given string can be interpreted as a decimal number.
//
//Some examples:
//"0" => true
//" 0.1 " => true
//"abc" => false
//"1 a" => false
//"2e10" => true
//" -90e3   " => true
//" 1e" => false
//"e3" => false
//" 6e-1" => true
//" 99e2.5 " => false
//"53.5e93" => true
//" --6 " => false
//"-+3" => false
//"95a54e53" => false
//
//Note: It is intended for the problem statement to be ambiguous. It would be best if you gathered all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:
//
//Numbers 0-9
//Exponent - "e"
//Positive/negative sign - "+"/"-"
//Decimal point - "."
//Of course, the context of these characters also matters in the input.
//
//
//
//Example 1:
//
//Input: s = "0"
//Output: true
//Example 2:
//
//Input: s = "3"
//Output: true
//
//
//Constraints:
//
//1 <= s.length <= 20
//s consists of only English letters, digits, space ' ', plus '+', minus '-', or dot '.'.


fun isNumber(s: String): Boolean {
    val str = s.trim()
    var state: State = State.Empty
    var exFound = false
    var dotFound = false

    for(i in 0 until str.length) {
        val c = str[i]
        val newState = when {
            c =='+' || c =='-' -> {
                State.Sign
            }
            c in '0' .. '9' -> {
                State.Num
            }
            c == 'e' -> {
                State.Ex
            }
            (i == 0 && c == '.') || (i == 1 && state is State.Sign && c == '.') -> {
                State.FirstDot
            }
            !exFound && c == '.' -> {
                State.Dot
            }
            else -> null
        }
        if(newState == null) return false
        if(state.isNextValid(newState)) {

            if(newState is State.Dot || newState is State.FirstDot) {
                if(dotFound) return false
                dotFound = true
            }

            if(newState is State.Ex) {
                if(exFound) return false
                exFound = true
            }
            state = newState
        } else {
            return false
        }
    }
    return !state.requerNext()
}

private sealed class State {
    abstract fun isNextValid(state: State): Boolean
    abstract fun requerNext(): Boolean

    object Sign: State() {
        override fun isNextValid(state: State): Boolean {
            return state is Num || state is State.FirstDot
        }

        override fun requerNext(): Boolean = true
    }

    object Num: State() {
        override fun isNextValid(state: State): Boolean {
            return state is Num || state is Dot || state is Ex
        }
        override fun requerNext(): Boolean = false
    }

    object Dot: State() {
        override fun isNextValid(state: State): Boolean {
            return state is State.Num || state is State.Ex
        }
        override fun requerNext(): Boolean = false
    }

    object FirstDot: State() {
        override fun isNextValid(state: State): Boolean {
            return state is State.Num
        }
        override fun requerNext(): Boolean = true
    }

    object Ex: State() {
        override fun isNextValid(state: State): Boolean {
            return state is State.Num || state is State.Sign
        }
        override fun requerNext(): Boolean = true
    }

    object Empty: State() {
        override fun isNextValid(state: State): Boolean {
            return state is State.Num || state is State.Sign || state is State.Dot || state is FirstDot
        }
        override fun requerNext(): Boolean = true
    }
}