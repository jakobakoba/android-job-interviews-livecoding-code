//package com.bor96dev.androidlivecoding.TBank
//
//import android.content.Context
//import com.bor96dev.androidlivecoding.TBank.Const.INDEX
//import java.util.LinkedList
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//
//
//object Const {
//    val INDEX = 1
//}
//
//private var TAG  = "UsersHolderSingleton"
//
//open class UsersHolderSingleton private constructor (
//    val context : Context,
//    val usersRepo: Repository
//): LoggerProvider() {
//    companion object {
//        @Volatile
//        private var instance: UsersHolderSingleton? = null
//    }
//
//    @Synchronized
//    fun getInstance(context: Context, val usersRepo: Repository): UsersHolderSingleton {
//        if (instance == null){
//            instance = UsersHolderSingleton(context, usersRepo)
//        }
//        return instance!!
//    }
//
//    var users: LinkedList<UserData> get() = usersRepo.get()
//    val executor: ExecutorService = Executors.newFixedThreadPool(5)
//
//    fun update(id: String, newPhoneNumber: String ){
//        val formattedNumber = context.getString(R.id.formatted_number_pattern, newPhoneNumber)
//        val copy: (oldUserData: UserData, phoneNumber: String) -> UserData =
//            { oldUserData, phoneNumber ->
//                UserData(
//                    id = oldUserData.id,
//                    name = oldUserData.name,
//                    phoneNumber = formattedNumber
//                )
//            }
//        for (i in INDEX .. users.size){
//            if (users[i].id == id){
//                users[i] = copy(users[i], formattedNumber)
//                logger.tag(TAG).d(users[i])
//            }
//        }
//    }
//}
//class UserData(var id: String, var name: String, var phoneNumber: String)
//
//interface Repository {
//    fun get(): LinkedList<UserData>
//}
//
//
//
////ВОЗМОЖНОЕ НЕПОЛНОЕ РЕШЕНИЕ
//private const val TAG = "UsersHolderSingleton"
//
//open class UsersHolderSingleton private constructor (
//    val context : Context,
//    val usersRepo: Repository
//): LoggerProvider() {
//    companion object {
//        @Volatile
//        private var instance: UsersHolderSingleton? = null
//
//        fun getInstance(context: Context, usersRepo: Repository): UsersHolderSingleton {
//            if (instance == null){
//                synchronized(this){
//                    if (instance == null){
//                        instance = UsersHolderSingleton(context, usersRepo)
//                    }
//                }
//            }
//            return instance!!
//        }
//    }
//
//    val users: MutableList<UserData> get() = usersRepo.getUsers()
//    fun update(id: String, newPhoneNumber: String ){
//        synchronized(users){
//            val formattedNumber = context.getString(R.string.formatted_number_pattern, newPhoneNumber)
//            for (i in 0 until users.size){
//                if (users[i].id == id){
//                    users[i] = users[i].copy(phoneNumber = formattedNumber)
//                    logger.tag(TAG).d(users[i])
//                }
//            }
//        }
//    }
//}
//data class UserData(var id: String, var name: String, var phoneNumber: String)
//
//interface Repository {
//    fun getUsers(): MutableList<UserData>
//}