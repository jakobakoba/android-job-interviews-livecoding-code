//package com.bor96dev.livecoding.Витрина_ТВ
//
//import java.util.PriorityQueue
//
///*
//Необходимо реализовать класс TaskManager, который представляет собой планировщик задач.
// */
//
//class TaskManager {
//
//    /*
//    метод addTask, который принимает исполняемый код задачи и опциональную задержку
//    перед ее выполнением (в миллисекундах)
//     */
//    fun addTask(/* ... */, delay: Long = 0L){
//        val currentTime = System.currentTimeMillis()
//    }
//
//    /*
//    Метод act периодически вызывается извне и выполняет активные задачи в соответствии
//    с текущим временем.
//     */
//    fun act() {
//        val currentTime = System.currentTimeMillis()
//    }
//}
//
////Пример добавления задачи
//fun main() {
//    val taskManager = TaskManager()
//    var isTaskManagerRunning = true
//
//    fun startTaskManager() {
//        Thread {
//            while (isTaskManagerRunning) {
//                taskManager.act()
//                Thread.sleep(5000)
//            }
//        }.start()
//    }
//
//    val task1 = taskManager.addTask(
//        { println("first task") }, 2000
//    )
//    val task2 = taskManager.addTask(
//        { println("second task") }
//    )
//    val task3 = taskManager.addTask(
//        { println("third task") }, 3000
//    )
//    startTaskManager()
//}
///*
//Код выше должен вывести строки в очередности:
//second task
//first task
//third task
// */
//
//
//
//
//
//
//
//
////Возможное решение
//class TaskManager {
//    data class Task(
//        val action: () -> Unit,
//        val scheduledTime: Long
//    )
//
//    val pq = PriorityQueue<Task>(compareBy { it.scheduledTime })
//
//    /*
//    метод addTask, который принимает исполняемый код задачи и опциональную задержку
//    перед ее выполнением (в миллисекундах)
//     */
//    @Synchronized
//    fun addTask(task: () -> Unit, delay: Long = 0L) {
//        val currentTime = System.currentTimeMillis()
//        pq.add(Task(task, currentTime + delay))
//    }
//
//    /*
//    Метод act периодически вызывается извне и выполняет активные задачи в соответствии
//    с текущим временем.
//     */
//    @Synchronized
//    fun act() {
//        val currentTime = System.currentTimeMillis()
//        while (pq.isNotEmpty() && pq.peek().scheduledTime <= currentTime) {
//            pq.poll().action()
//        }
//    }
//}