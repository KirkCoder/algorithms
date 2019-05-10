package tasks

// y = A*x + b
// A = dy / dx

fun main() {
//    val p1 = Point(2.0, 1.0)
//    val p2 = Point(10.0, 4.0)
//    val p3 = Point(4.0, 1.0)
//    val p4 = Point(5.0, 4.0)

//    val p1 = Point(2.0, 1.0)
//    val p2 = Point(10.0, 4.0)
//
//    val p3 = Point(17.0, 17.0)
//    val p4 = Point(21.0, 700.0)

    val p1 = Point(2.0, 1.0)
    val p2 = Point(10.0, 1.0)

    val p3 = Point(4.0, 1.0)
    val p4 = Point(5.0, 1.0)

    val p = intercept(p1, p2, p3, p4)
    println("x:${p?.x}, y:${p?.y}")
}

fun intercept(p1: Point, p2: Point, p3: Point, p4: Point): Point? {
    val lFirst = if (p1.x < p2.x) Line(p1, p2) else Line(p2, p1)
    val lSecond = if (p3.x < p4.x) Line(p3, p4) else Line(p4, p3)

    val l1: Line
    val l2: Line

    if (lFirst.p1.x <= lSecond.p1.x) {
        l1 = lFirst
        l2 = lSecond
    } else {
        l1 = lSecond
        l2 = lFirst
    }

    val a1 = (l1.p1.y - l1.p2.y) / (l1.p1.x - l1.p2.x)
    val a2 = (l2.p1.y - l2.p2.y) / (l2.p1.x - l2.p2.x)
    val b1 = l1.p2.y - a1 * l1.p2.x
    val b2 = l2.p2.y - a2 * l2.p2.x

    if (a1 == a2) {
        return if (isIntersect(l1.p1.x, l2.p1.x, l1.p2.x)) l2.p1
        else null
    }

    val x = (b2 - b1) / (a1 - a2)
    val y = a1*x + b1
    val p = Point(x, y)

    if (isIntersect(l1.p1.x, p.x, l1.p2.x) &&
        (isIntersect(l1.p1.y, p.y, l1.p2.y) || isIntersect(l2.p1.y, p.y, l2.p2.y))){
        return p
    }
    return null
}

fun isIntersect(start: Double, mid: Double, end: Double) = mid in start..end

class Point(
    val x: Double,
    val y: Double
)

class Line(
    val p1: Point,
    val p2: Point
)