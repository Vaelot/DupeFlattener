package xyz.vaelot.dupeflattener.scan

import java.io.File

class SequenceScan(override val root: File) : IScan, Sequence<File> {
    private var walks: Sequence<File>

    init {
        require (root.isDirectory) { "$root does not exists." }
        walks = root.walk(FileWalkDirection.TOP_DOWN)
            .filter { it.canRead() && it.isFile }
    }

    override fun iterator(): Iterator<File> { return walks.iterator() }
}