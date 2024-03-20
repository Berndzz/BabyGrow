package com.hardus.babygrow.main_content.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.hardus.babygrow.util.data.Simulasi
import com.hardus.babygrow.util.data.SimulasiWithSubSimulasi
import com.hardus.babygrow.util.data.SubSimulasi
import com.hardus.babygrow.util.data.Video
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _simulasiList = MutableStateFlow<List<SimulasiWithSubSimulasi>>(emptyList())
    val simulasiList: StateFlow<List<SimulasiWithSubSimulasi>> = _simulasiList

    private val _contentList = MutableStateFlow<List<Video>>(emptyList())
    val contentList: StateFlow<List<Video>> = _contentList

    init {
        fetchSimulasiDataFromFirestore()
        fetchContentListFromFirestore()
    }
    fun fetchSimulasiDataFromFirestore() {
        viewModelScope.launch {
            try {
                val simulasiListFromFirestore = getSimulasiListFromFirestore()
                _simulasiList.value = simulasiListFromFirestore
            } catch (e: Exception) {
                e.printStackTrace()
                // Tangani kesalahan dengan benar
            }
        }
    }

    private suspend fun getSimulasiListFromFirestore(): List<SimulasiWithSubSimulasi> {
        val simulasiList = mutableListOf<SimulasiWithSubSimulasi>()
        val querySnapshot = db.collection("simulasi").get().await()
        for (document in querySnapshot.documents) {
            val simulasiId = document.id
            val simulasiData = document.toObject(Simulasi::class.java)
            val subSimulasiList = mutableListOf<SubSimulasi>()

            // Ganti pengambilan sub-simulasi dengan query yang sesuai
            val subSimulasiSnapshot = db.collection("simulasi/$simulasiId/subsimulasi").get().await()
            for (subSimulasiDocument in subSimulasiSnapshot.documents) {
                val subSimulasiData = subSimulasiDocument.toObject(SubSimulasi::class.java)
                subSimulasiList.add(subSimulasiData!!)
            }

            val simulasiWithSubSimulasi = SimulasiWithSubSimulasi(simulasiData!!, subSimulasiList)
            simulasiList.add(simulasiWithSubSimulasi)
        }
        return simulasiList
    }

    fun fetchContentListFromFirestore() {
        viewModelScope.launch {
            try {
                val contentListFromFirestore = getContentListFromFirestore()
                _contentList.value = contentListFromFirestore
                Log.d("ContentList", "Content list fetched successfully: ${contentListFromFirestore.size} items")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ContentList", "Error fetching content list: ${e.message}")
                // Tangani kesalahan dengan benar
            }
        }
    }


    private suspend fun getContentListFromFirestore(): List<Video> {
        val contentList = mutableListOf<Video>()
        val querySnapshot = db.collection("content").get().await()
        for (document in querySnapshot.documents) {
            val video = document.toObject(Video::class.java)
            video?.let { contentList.add(it) }
        }
        return contentList
    }
}