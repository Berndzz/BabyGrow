package com.hardus.babygrow.util.objects

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hardus.babygrow.util.data.SimulasiWithSubSimulasi
import com.hardus.babygrow.util.data.Video
import kotlinx.coroutines.tasks.await
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

fun Timestamp.toDate(): Date = Date(this.seconds * 1000 + this.nanoseconds / 1000000)

fun Timestamp?.toDateA(): Date? = this?.toDate()
fun Date?.formatToStrinAg(): String {
    // Format tanggal ke string sesuai kebutuhan Anda
    return SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(this)
}


object FirestoreRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun tambahkanDataSimulasiKeFirestore(simulasiList: List<SimulasiWithSubSimulasi>) {
        try {
            for (simulasi in simulasiList) {
                // Masukkan simulasi ke dalam koleksi "simulasi"
                db.collection("simulasi").document(simulasi.simulasi.id_simulasi.toString())
                    .set(simulasi.simulasi)
                    .await() // Hanya gunakan await() untuk operasi yang mengembalikan Task

                // Masukkan sub-simulasi ke dalam koleksi "subsimulasi" di dalam dokumen simulasi
                for (subSimulasi in simulasi.subSimulasi) {
                    db.collection("simulasi").document(simulasi.simulasi.id_simulasi.toString())
                        .collection("subsimulasi").add(subSimulasi)
                        .await() // Hanya gunakan await() untuk operasi yang mengembalikan Task
                }
            }
        } catch (e: Exception) {
            // Tangani error jika terjadi
            e.printStackTrace()
        }
    }
}



fun saveVideosToFirebase(items: List<Video>) {
    val db = FirebaseFirestore.getInstance()
    val collectionRef = db.collection("content")

    // Hapus semua dokumen yang ada di koleksi "content"
    collectionRef.get().addOnSuccessListener { snapshot ->
        for (document in snapshot.documents) {
            document.reference.delete()
        }

        // Tambahkan item ke koleksi "content"
        for (video in items) {
            collectionRef.add(video)
                .addOnSuccessListener { documentReference ->
                    println("Video berhasil disimpan dengan ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Error saat menyimpan video: $e")
                }
        }
    }.addOnFailureListener { e ->
        println("Error saat menghapus dokumen yang ada: $e")
    }
}