package com.hardus.babygrow.main_content.home.laporanBayi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.SetOptions
import com.hardus.babygrow.util.data.Bayi
import com.hardus.babygrow.util.objects.FirestoreAuth
import com.hardus.babygrow.util.objects.FirestoreService
import com.hardus.babygrow.util.objects.toTimestamp
import java.time.LocalDate

class ViewModelLaporanBayi : ViewModel() {
    private val _bayi = mutableStateOf<Bayi?>(null)
    val personalData: State<Bayi?> = _bayi

    // MutableState untuk menandai status loading
    private val _loadingState = mutableStateOf(false)
    val loadingState: State<Boolean> = _loadingState


    init {
        // Inisialisasi data ketika ViewModel dibuat
        loadLaporanBayiForCurrentUser()
    }


    // Fungsi untuk menandai status loading saat data dimuat
    private fun setLoadingState(loading: Boolean) {
        _loadingState.value = loading
    }

    private val _fullNameResponse = mutableStateOf("")
    val onFullNameResponse: String
        get() = _fullNameResponse.value

    fun onFullNameChange(newName: String) {
        _fullNameResponse.value = newName
    }

    private val _dateOfBirth = mutableStateOf<LocalDate>(LocalDate.now())
    val onDateOfBirth: LocalDate
        get() = _dateOfBirth.value

    fun onDateOfBirthChanged(newDate: LocalDate) {
        _dateOfBirth.value = newDate
    }

    private val _weightBabyResponse = mutableStateOf("")
    val onWeightBabyResponse: String
        get() = _weightBabyResponse.value

    fun onWeightChange(newName: String) {
        _weightBabyResponse.value = newName
    }

    private val _optionalData = mutableStateOf("")
    val onOptionalData: String
        get() = _optionalData.value

    fun onOptionalDataChanged(newMotto: String) {
        _optionalData.value = newMotto
    }

    fun onDonePressed(onFormComplete: () -> Unit) {
        saveLaporanBabytoFirestore { success ->
            if (success) {
                onFormComplete() // Panggilan ini akan menavigasi pengguna keluar dari form atau menampilkan pesan sukses
            } else {
                // Tangani kasus ketika penyimpanan gagal, misalnya dengan menampilkan pesan error
            }
        }
    }

    private fun saveLaporanBabytoFirestore(onComplete: (Boolean) -> Unit) {
        val firestore = FirestoreService.db
        val userId = FirestoreAuth.db.currentUser?.uid
        if (userId == null) {
            onComplete(false)
            return
        }
        val babyDocRef = firestore.collection("baby").document(userId)

        val bayiData = Bayi(
            nama = _fullNameResponse.value,
            tanggalLahir = _dateOfBirth.value.toTimestamp(), // Konversi LocalDate ke Timestamp
            beratBadan = _weightBabyResponse.value,
            catatanTambahan = _optionalData.value
        )

        // Set status loading menjadi true saat data sedang dimuat
        setLoadingState(true)

        babyDocRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                // Jika dokumen bayi sudah ada, perbarui data dengan set dan merge
                babyDocRef.set(bayiData, SetOptions.merge())
                    .addOnSuccessListener {
                        onComplete(true) // Sukses meng-update data bayi
                    }
                    .addOnFailureListener {
                        onComplete(false) // Gagal meng-update data bayi
                    }
            } else {
                // Jika dokumen bayi belum ada, buat dokumen baru dengan set
                babyDocRef.set(bayiData)
                    .addOnSuccessListener {
                        onComplete(true) // Sukses menambahkan data bayi baru
                    }
                    .addOnFailureListener {
                        onComplete(false) // Gagal menambahkan data bayi baru
                    }
            }
        }.addOnFailureListener {
            onComplete(false) // Gagal melakukan pemeriksaan dokumen bayi
        }.addOnCompleteListener {
            // Set status loading kembali menjadi false setelah operasi selesai
            setLoadingState(false)
        }
    }

    private fun loadLaporanBayiForCurrentUser() {
        val userId = FirestoreAuth.db.currentUser?.uid // ID dari pengguna yang sedang login
        val firestore = FirestoreService.db

        // Menggunakan userId untuk mengambil data spesifik pengguna
        firestore.collection("baby")
            .document(userId!!)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                _bayi.value =
                    documentSnapshot.toObject(Bayi::class.java) // Convert Firestore document to PersonalData object
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }
}