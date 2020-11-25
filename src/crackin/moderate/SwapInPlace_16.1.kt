package crackin.moderate

//Number Swapper: Write afunction to swap anumber in place (that is, without temporary variables).

fun main() {
    var a = 44
    var b = 78

    a = a xor b
    b = a xor b
    a = a xor b
    println(a)
    println(b)

    a = a - b
    b = b + a
    a = b - a

    println(a)
    println(b)

}