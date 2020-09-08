package xyz.vaelot.dupeflattener

import xyz.vaelot.dupeflattener.hash.*
import xyz.vaelot.dupeflattener.ds.*
import xyz.vaelot.dupeflattener.node.*
import xyz.vaelot.dupeflattener.scan.*
import java.io.Serializable
import kotlin.reflect.KClass

object Manager : Serializable {
    @JvmStatic private val serialVersionUID: Long = 1L

    lateinit var scan: IScan
    lateinit var ds: IDS<INode>
    @Transient lateinit var hashClass: KClass<IHash>
}