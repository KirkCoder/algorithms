package crackin.moderate

//Intersection: Given two straight line segments (represented as a start point and an end point)' compute the point of intersection, if any.

// A*x + b = y
// A - наклон A = (dy) / dx
// b пересечение с Y

fun main() {
    val res = getIntersection(Point(2.0, 1.0), Point(5.0, 2.0), Point(2.0, 3.0), Point(5.0, 1.0))
    println("x=${res?.x}, y=${res?.y}")
}

fun getIntersection(p1: Point, p2: Point, p3: Point, p4: Point): Point? {
    if (p2.x < p1.x) {
        swap(p1, p2)
    }
    if (p4.x < p3.x) {
        swap(p3, p4)
    }
    if (p3.x < p1.x) {
        swap(p1, p3)
        swap(p2, p4)
    }
    val first = Line(p1, p2)
    val second = Line(p3, p4)

    if (first.k == second.k) {
        if (isBetwin(p1.x, p3.x, p2.x)) {
            return p3
        }
    }

    val x = (second.yi!! - first.yi!!) / (first.k!! - second.k!!)
    val y = second.k * x + second.yi
    val res = Point(x, y)

    return if (isBetwin(p1, res, p2) && isBetwin(p3, res, p4)) {
        res
    } else {
        null
    }
}

fun isBetwin(left: Point, middle: Point, end: Point): Boolean {
    return isBetwin(left.x, middle.x, end.x) && isBetwin(left.y, middle.y, end.y)
}

fun isBetwin(left: Double, middle: Double, end: Double): Boolean {
    return if (left < end) {
        middle in left..end
    } else {
        middle in end..left
    }
}

fun swap(first: Point, second: Point) {
    val x = first.x
    val y = first.y
    first.x = second.x
    first.y = second.y
    second.x = x
    second.y = y
}

class Line(first: Point, second: Point) {
    val k: Double? = if (first.x - second.x == 0.0) {
        null
    } else {
        (first.y - second.y) / (first.x - second.x)
    }

    val yi = if (k == null) {
        null
    } else {
        first.y - k * first.x
    }
}

class Point(
    var x: Double,
    var y: Double
)