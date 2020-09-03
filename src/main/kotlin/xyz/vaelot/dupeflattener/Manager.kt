package xyz.vaelot.dupeflattener

import xyz.vaelot.dupeflattener.hash.*
import xyz.vaelot.dupeflattener.ds.*

import kotlin.reflect.KClass

object Manager {
    var ds: KClass<out DS> = HeapTreeDS::class
    var hashAlg: KClass<out Hash> = Blake2bHash::class
}