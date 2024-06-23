package good.damn.kamchatka.extensions

fun <K,V> HashMap<K,V>.getKey(
    v: V
): K? {
    var key: K? = null
    forEach {
        if (it.value == v) {
            key = it.key
            return@forEach
        }
    }

    return key
}