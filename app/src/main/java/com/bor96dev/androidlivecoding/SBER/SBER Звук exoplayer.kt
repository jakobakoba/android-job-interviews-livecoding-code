//package com.bor96dev.androidlivecoding.SBER
//
//import android.content.Context
//import android.util.Log
//import androidx.media3.exoplayer.ExoPlayer
//import io.reactivex.rxjava3.core.Single
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.asFlow
//import kotlinx.coroutines.flow.filter
//import kotlinx.coroutines.flow.flatMapConcat
//import kotlinx.coroutines.flow.flowOn
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.launch
//import kotlin.collections.emptyList
//
//class ExoPlayerController(
//    val firstHolder: FirstHolder,
//    val secondManager: SecondManager,
//    var context: Context
//) {
//    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
//
//    var currentInfo: SecondInfoModel = SecondInfoModel(
//        countActivatedPlayers = 0,
//        allPlayersIds = emptyList()
//    )
//
//    fun initPlayer(model: AudioModel) {
//        scope.launch {
//            try {
//                init(model)
//            } catch (e: Exception) {
//                Log.e("TAG", "exception = ${it.message}")
//            }
//        }
//    }
//
//    @Synchronized
//    fun init(model: AudioModel) =
//        firstHolder.getPlayer(model.id)
//            .onEach { player ->
//                val newPlayer = if (player == null) {
//                    ExoPlayer.Builder(context).build()
//                } else {
//                    player
//                }
//                firstHolder.addPlayer(model.id, newPlayer)
//                player
//            }
//            .flowOn(Dispatchers.IO)
//            .flatMapConcat { player ->
//                Single.fromCallable {
//                    val isNeedPrepare =
//                        model.fileSize <= (1024 * 1024) || player.currentPosition > 0
//                    if (isNeedPrepare) {
//                        player.prepare()
//                    }
//                }
//            }
//            .flowOn(Dispatchers.Unconfined)
//            .onEach {
//                var newInfo = SecondInfoModel(
//                    countActivatedPlayers = currentInfo.countActivatedPlayers + 1,
//                    allPlayersIds = currentInfo.allPlayersIds.plus(model.id),
//                )
//                newInfo
//            }
//            .collect { newInfo -> currentInfo = newInfo }
//
//    @Synchronized
//    fun stopPlayers() {
//        firstHolder.getPlayers()
//            .flowOn(Dispatchers.Unconfined)
//            .map {
//                it.asFlow()
//            }
//            .filter { player ->
//                player.isPlaying()
//            }
//            .onEach { player ->
//                player?.stop()
//            }
//            .toList()
//            .apply {
//                secondManager.sendPlayersAnalytics(
//                    currentInfo.countActivatedPlayers,
//                    currentInfo.allPlayersIds
//                )
//                currentInfo = SecondInfoModel(
//                    countActivatedPlayers = 0,
//                    allPlayersIds = emptyList()
//                )
//            }
//    }
//}
//
//class SecondInfoModel(
//    val countActivatedPlayers: Int, // количество активированных players
//    val allPlayersIds: List<String> // id всех players которые пытались активировать
//)
//
//class AudioModel(
//    val id: String,
//    val audioUrl: String,
//    val fileSize: Long,
//    val title: String?,
//    val duration: Long,
//)
//
//interface FirstHolder {
//    /**
//     *  Данный holder по условию задачи может хранить десятки тысяч players
//     */
//
//    fun addPlayer(playerId: String, player: ExoPlayer): Flow<Unit>
//
//    fun getPlayer(playerId: String): Flow<ExoPlayer?>
//
//    fun getPlayers(): Flow<List<ExoPlayer?>>
//}
//
//interface SecondManager {
//    fun sendPlayersAnalytics(countActivatedPlayers: Int, allPlayersIds: List<String>)
//}