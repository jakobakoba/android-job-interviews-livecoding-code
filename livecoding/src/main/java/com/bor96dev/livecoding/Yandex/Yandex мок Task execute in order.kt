package com.bor96dev.livecoding.Yandex

//условие задачи
//class Task (var name: String, priority: Int)
//
//fun main(){
//    val tasks = listOf(
//        Task("task1", 1),
//        Task("task2", 2),
//        Task("task2", 3)
//    )
//    tasks.executeInOrder({it.priority} ){
//        println(it.name)
//        if (it.name == "task2"){
//            return
//        }
//    }
//}
//fun <reified T> List<T>.executeInOrder(order: (T) -> Int, process: (T) -> Unit){
//    sortedBy(order).forEach(process)
//}








// Возможное решение
//усложнение убрать второй task2 в set. Если name одинаковые, то считаются одинаковыми объектами
//class Task(val name: String, val priority: Int) {
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other !is Task) return false
//        return name == other.name
//    }
//
//    override fun hashCode(): Int {
//        return name.hashCode()
//    }
//}
//
//fun main() {
//    val tasks = listOf(
//        Task("task1", 1),
//        Task("task2", 2),
//        Task("task2", 3)
//    )
//    tasks.toSet().executeInOrder({ it.priority }) {
//        println(it.name)
//    }
//}
//
//inline fun <T> Set<T>.executeInOrder(crossinline order: (T) -> Int, process: (T) -> Unit) {
//    sortedBy(order).forEach(process)
//}