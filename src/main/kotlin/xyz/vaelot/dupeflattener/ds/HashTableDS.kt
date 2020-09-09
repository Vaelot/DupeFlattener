package xyz.vaelot.dupeflattener.ds

import xyz.vaelot.dupeflattener.Manager
import xyz.vaelot.dupeflattener.node.INode
import java.io.File
import kotlin.collections.HashMap
import kotlin.reflect.full.primaryConstructor

class HashTableDS: DS() {
    private val st: HashMap<List<Byte>, ArrayList<INode>> = hashMapOf()

    override fun add(d: File) {
        val n = Manager.nodeClass.primaryConstructor!!.call(d.path)
        val k = n.hash.toList()
        if (st.containsKey(k)) {
            st[k]!!.find { it == n }!!.add(d.path)
        } else {
            st[k] = arrayListOf(n)
        }
    }
}
