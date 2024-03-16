package com.hardus.babygrow.util.objects

import android.annotation.SuppressLint
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

object FirestoreService {
    @SuppressLint("StaticFieldLeak")
    val db = FirebaseFirestore.getInstance()
}

object FirestoreAuth {
    val db = FirebaseAuth.getInstance()
}

fun LocalDate.toTimestamp(): Timestamp {
    val instant = this.atStartOfDay(ZoneId.systemDefault()).toInstant()
    return Timestamp(Date.from(instant))
}

fun Timestamp?.toDateA(): Date? = this?.toDate()
fun Date?.formatToStrinAg(): String {
    // Format tanggal ke string sesuai kebutuhan Anda
    return SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(this)
}
