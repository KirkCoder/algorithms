fun main() {
    println(getFib(3))
}

fun getFib(i: Int): Int{
    if( i == 1) return 1
    if (i == 0) return 0
    return getFib(i - 1) + getFib(i - 2)
}