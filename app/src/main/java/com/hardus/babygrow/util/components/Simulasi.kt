package com.hardus.babygrow.util.components

data class Simulasi(
    val id_simulasi: Long = 0L,
    val judul_simulasi: String = "",
    val deskripsi_simulasi: String = "",
    val gambar_simulasi: String = ""
)

data class SubSimulasi(
    val judul_video: String = "",
    val deskripsi_video: String = "",
    val url_video: String = "",
    val durasi_video: Int = 0, // Durasi video dalam detik
    val sudah_ditonton: Boolean = false // Apakah sudah ditonton atau belum
)
data class SimulasiWithSubSimulasi(
    val simulasi: Simulasi,
    val subSimulasi: List<SubSimulasi>
)
