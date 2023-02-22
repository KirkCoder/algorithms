import java.util.*

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Codec() {
    // Encodes a URL to a shortened URL.

    private val nil = "null"

    fun serialize(root: TreeNode?): String {
        root ?: return ""
        val list = LinkedList<String>()
        var queue = LinkedList<TreeNode>()
        queue.add(root)
        list.add(root.`val`.toString())
        while (queue.isNotEmpty()) {
            val tmp = LinkedList<TreeNode>()
            for (node in queue) {
                val left = node.left
                if (left == null) {
                    list.add(nil)
                } else {
                    list.add(left.`val`.toString())
                    tmp.add(left)
                }

                val right = node.right
                if (right == null) {
                    list.add(nil)
                } else {
                    list.add(right.`val`.toString())
                    tmp.add(right)
                }
            }
            queue = tmp
        }
        return list.joinToString(",")
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        if (data.isEmpty()) return null
        if ("," !in data) {
            return if (data == nil) {
                null
            } else {
                TreeNode(data.toInt())
            }
        }
        val list = data.split(",")
        val root = TreeNode(list[0].toInt())
        var line = mutableListOf<TreeNode>()
        line.add(root)

        var i = 1
        while (i < list.size) {
            val tmp = mutableListOf<TreeNode>()
            var j = 0
            var right = false
            while (j < line.size && i < list.size) {
                val top = line[j]
                val s = list[i]
                val node = if (s != nil) {
                    TreeNode(s.toInt())
                } else {
                    null
                }
                if (right) {
                    top.right = node
                    j++
                    right = false
                } else {
                    top.left = node
                    right = true
                }
                if (node != null) {
                    tmp.add(node)
                }
                i++
            }
            line = tmp
        }

        return root
    }
}
