package com.example.md_gbproject.data

data class Changes<out T>(val oldData: T, val newData: T)

fun <T> createCombinedPayloads(payloads: List<Changes<T>>): Changes<T> {
    assert(payloads.isNotEmpty())
    val firstChanges = payloads.first()
    val lastChanges = payloads.last()

    return Changes(firstChanges.oldData, lastChanges.newData)
}

