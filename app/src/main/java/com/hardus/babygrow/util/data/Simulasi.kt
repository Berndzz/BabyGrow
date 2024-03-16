package com.hardus.babygrow.util.data

data class Simulasi(
    val id_simulasi: Long = 0L,
    val judul_simulasi: String = "",
    val deskripsi_simulasi: String = "",
    val gambar_simulasi: String = "",
    val manfaat_simulasi:String? = null,
    val tips_simulasi:String? = null,
    val kebutuhan_simulasi:String? = null,
    val cara_simulasi:String? = null,
    val jenis_simulasi:String? = null,
    val halHal_simulasi:String? = null,
    val teknik_simulasi:String? = null,
    val catatan_simulasi:String? = null,
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
