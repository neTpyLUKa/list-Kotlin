package com.dirRec

import java.io.File

fun rec(path: String, restricted: Set<String>) {
    val file = File(path)
    if (file.isFile) {
        println(path)
        return
    }
    var list = file.listFiles().map{it.toString()}
    if (path == "./") {
        list = list.map{it.substring(2)}
    }
    for (name in list) {
        if (name !in restricted) {
            rec(name, restricted)
        }
    }
}

fun foo() {
    val restricted = File("./.ignore").readLines().
            filter{it.firstOrNull { ch -> ch != ' ' } !in listOf('#', null)}.
            map{s -> if (s.last() == '/') s.substring(0, s.length - 1) else s}.toSet()
    rec("./", restricted)
}