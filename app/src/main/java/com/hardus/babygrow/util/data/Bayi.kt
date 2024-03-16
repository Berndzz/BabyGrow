package com.hardus.babygrow.util.data

import com.google.firebase.Timestamp

data class Bayi(
    val nama: String,
    val tanggalLahir: Timestamp? = null,
    val beratBadan: String,
    val catatanTambahan: String? = null,
){
    constructor() : this("", null, "", null)
}