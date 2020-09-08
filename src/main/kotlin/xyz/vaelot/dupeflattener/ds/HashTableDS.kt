package xyz.vaelot.dupeflattener.ds

import xyz.vaelot.dupeflattener.Manager
import xyz.vaelot.dupeflattener.node.INode
import java.io.File
import kotlin.collections.HashMap
import kotlin.reflect.full.primaryConstructor

class HashTableDS: DS() {
    private val st: HashMap<ByteArray, ArrayList<INode>> = hashMapOf()

    override fun add(d: File) {
        val n = Manager.nodeClass.primaryConstructor!!.call(d.path)
        st.getOrElse(n.hash) { st[n.hash] = arrayListOf(n); st[n.hash] }
            ?.find { it == d }
            ?.add(d.path)
    }
}