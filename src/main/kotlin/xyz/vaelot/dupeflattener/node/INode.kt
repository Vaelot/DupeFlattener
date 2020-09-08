package xyz.vaelot.dupeflattener.node

import java.io.File

interface INode: Comparable<Any?> {
    val path: MutableList<String>
    val firstFile: File
    val size: Long
    val hash: ByteArray

    fun add(p: String)
}