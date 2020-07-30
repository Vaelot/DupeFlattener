package xyz.vaelot.dupeflattener.model

import java.io.Serializable

/**
 * A Node from Struct.
 *
 * This class represent a node of data structures. It might be contain more than one files.
 *
 * @property files Files that contains this node.
 * @property count Files that contains this node.
 * @property fsize File Size represent of this node.
 * @property hash Files that contains this node.
 * @property left Files that contains this node.
 * @property right Files that contains this node.
 */

data class Node(val fsize: Int,
                val hash: ByteArray) : Serializable {
    val files: ArrayList<IFile> = arrayListOf()
    val count: Int
        get() { return files.size }
    var left: Node? = null
    var right: Node? = null
}
