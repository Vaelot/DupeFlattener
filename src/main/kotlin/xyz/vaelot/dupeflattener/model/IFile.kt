package xyz.vaelot.dupeflattener.model

import java.io.Serializable

data class IFile(val name: String,
                 val path: String,
                 val inode: Int) : Serializable