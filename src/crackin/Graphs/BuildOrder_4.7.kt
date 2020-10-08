package crackin.Graphs

import showList

//Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the second project is dependent on the first project). All of a project's dependencies must be built before the project is. Find a build order that will allow the projects to be built. If there is no valid build order, return an error.
//SOLUTION
//EXAMPLE Input:
//projects: a, b, c, d, e, f
//dependencies: (a, d), (f, b), (b, d), (f, a), (d, c) Output: f, e, a, b, d, c

fun main() {
//    val projects = listOf('a', 'b', 'c', 'd', 'e', 'f')
    val projects = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g')
//    val dependences = listOf(Pair('a', 'd'), Pair('f', 'b'), Pair('b', 'd'), Pair('f', 'a'), Pair('d', 'c'))
    val dependences =
        listOf(Pair('f', 'c'), Pair('f', 'b'), Pair('f', 'a'), Pair('a', 'e'), Pair('b', 'e'), Pair('d', 'g'))
    showList(buildOrder(projects, dependences))
    showList(buildOrder2(projects, dependences))
}

fun buildOrder2(projects: List<Char>, dependences: List<Pair<Char, Char>>): List<Char> {
    val nodes = mutableMapOf<Char, ProjectNode>()
    val result = mutableListOf<Char>()

    for (p in projects) {
        nodes[p] = ProjectNode(p)
    }

    for (pair in dependences) {
        nodes[pair.first]!!.lowers.add(nodes[pair.second]!!)
        nodes[pair.second]!!.dependant.add(nodes[pair.first]!!)
    }


    visitNodes(nodes.values.filter { it.dependant.isEmpty() }, result)

    if (result.size < projects.size) throw CantBuildProjectsException()

    return result
}

private fun removeLinks(head: ProjectNode) {
    for (node in head.lowers) {
        node.dependant.remove(head)
    }
}

private fun visitNodes(nodes: List<ProjectNode>, result: MutableList<Char>) {
    for (node in nodes) {
        if (node.dependant.isEmpty()) {
            result.add(node.char)
            removeLinks(node)
            if (node.lowers.isNotEmpty()) {
                visitNodes(node.lowers, result)
            }
        }
    }
}


fun buildOrder(projects: List<Char>, dependences: List<Pair<Char, Char>>): List<Char> {
    val nodesMap = mutableMapOf<Char, ProjectNode>()
    val result = mutableListOf<Char>()

    for (c in projects) {
        nodesMap[c] = ProjectNode(c)
    }

    for (pair in dependences) {

        val second = nodesMap[pair.second]!!
        val first = nodesMap[pair.first]!!

        second.dependant.add(first)
    }


    val nodes = nodesMap.values.toList()
    for (i in projects.indices) {
        val builded = buildNext(nodes)
        if (builded.isEmpty()) {
            break
        }
        result.addAll(builded)
    }

    if (result.size < projects.size) throw CantBuildProjectsException()

    return result
}

fun buildNext(nodes: List<ProjectNode>): List<Char> {
    val result = mutableListOf<Char>()
    for (node in nodes) {
        if (!node.isBuilded && (node.dependant.isEmpty() || node.dependant.all { it.isBuilded })) {
            node.isBuilded = true
            result.add(node.char)
        }
    }
    return result
}


class ProjectNode(
    val char: Char,
    val dependant: MutableList<ProjectNode> = mutableListOf(),
    val lowers: MutableList<ProjectNode> = mutableListOf(),
    var isBuilded: Boolean = false
)

class CantBuildProjectsException : Throwable()