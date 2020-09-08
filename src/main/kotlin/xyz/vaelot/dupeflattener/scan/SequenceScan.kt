package xyz.vaelot.dupeflattener.scan

import java.io.File

class SequenceScan(override val root: File) : IScan {
    init { require (root.isDirectory) { "$root does not exists." } }
    override fun start(): Sequence<File> {
        return root.walk(FileWalkDirection.TOP_DOWN)
            .filter { it.canRead() && it.isFile }
    }
}