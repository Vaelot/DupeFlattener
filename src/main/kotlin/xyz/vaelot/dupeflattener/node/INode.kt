package xyz.vaelot.dupeflattener.node

import java.io.File

interface INode {
    var path: MutableList<String>
    var first: File
    val size: Long
    val hash: Any

    fun add(p: String)
}