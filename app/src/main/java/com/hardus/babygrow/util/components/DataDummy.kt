package com.hardus.babygrow.util.components

import com.hardus.babygrow.util.data.Simulasi
import com.hardus.babygrow.util.data.SimulasiWithSubSimulasi
import com.hardus.babygrow.util.data.SubSimulasi

//val simulasiList = listOf(
//    SimulasiWithSubSimulasi(
//        Simulasi(
//            id_simulasi = 1,
//            judul_simulasi = "Simulasi PMK",
//            deskripsi_simulasi = "PMK atau Perawatan Metode Kanguru adalah suatu metode perawatan untuk bayi berat lahir rendah (BBLR) atau bayi prematur dengan cara melakukan kontak langsung antara kulit bayi dengan kulit ibu atau skin-to-skin contact. Pada metode ini, bayi diletakkan telentang di dada ibu, dengan kepala bayi di bagian atas dada ibu dan kaki bayi di bawah lengan ibu.",
//            gambar_simulasi = "https://images.unsplash.com/photo-1491013516836-7db643ee125a?q=80&w=1925&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//            manfaat_simulasi = "\nManfaat PMK:\n " +
//                    "• Menjaga suhu tubuh bayi\n" +
//                    "• Meningkatkan berat badan bayi\n" +
//                    "• Meningkatkan kekebalan tubuh bayi\n" +
//                    "• Mengurangi risiko infeksi pada bayi\n" +
//                    "• Mempererat hubungan antara ibu dan bayi",
//            tips_simulasi = "\nTIPS PMK :\n " +
//                    "• Lakukan PMK setidaknya 2 jam sehari, atau lebih sering jika memungkinkan.\n" +
//                    "• Lakukan PMK di ruangan yang hangat dan nyaman.\n" +
//                    "• Pastikan bayi dalam keadaan tenang dan nyaman.\n" +
//                    "• Jika bayi tampak gelisah atau menangis, segera hentikan PMK dan periksa bayi.",
//            catatan_simulasi = "\nCATATAN TAMBAHAN TERKAIT PMK :\n" +
//                    "• Jika bayi memiliki kondisi medis tertentu, konsultasikan dengan dokter sebelum melakukan PMK.\n" +
//                    "• Jika bayi menggunakan alat bantu napas atau infus, pastikan alat-alat tersebut tidak mengganggu posisi PMK.\n" +
//                    "• Jika bayi memiliki kulit yang sensitif, gunakan pakaian yang lembut dan tidak terlalu ketat.",
//        ),
//        listOf(
//            SubSimulasi(
//                judul_video = "Perawatan Metode Kangguru (PMK) Jaga Kehangatan Bayi Prematur",
//                deskripsi_video = "Deskripsi Video 1",
//                url_video = "nXgpD2xr0kk",
//                durasi_video = 200,
//                sudah_ditonton = false
//            ),
//            SubSimulasi(
//                judul_video = "PERAWATAN METODE KANGURU PADA BAYI BBLR - RSUD CIDERES",
//                deskripsi_video = "Deskripsi Video 2",
//                url_video = "yObgEFLfLdY",
//                durasi_video = 90,
//                sudah_ditonton = true
//            ),
//            SubSimulasi(
//                judul_video = "Reni Armawati_ Simulasi Perawatan Metode Kangguru (PMK)",
//                deskripsi_video = "Deskripsi Video 3",
//                url_video = "cBR92izlPnI",
//                durasi_video = 90,
//                sudah_ditonton = true
//            ),
//            SubSimulasi(
//                judul_video = "Perawatan Metode Kangguru",
//                deskripsi_video = "Deskripsi Video 4",
//                url_video = "gGB-yfPfR_Y",
//                durasi_video = 90,
//                sudah_ditonton = true
//            ),
//            SubSimulasi(
//                judul_video = "Bagaimana Metode Kangguru",
//                deskripsi_video = "Deskripsi Video 5",
//                url_video = "va0sMdK9OS8",
//                durasi_video = 90,
//                sudah_ditonton = true
//            ),
//        )
//    ),
//    SimulasiWithSubSimulasi(
//        Simulasi(
//            id_simulasi = 2,
//            judul_simulasi = "Simulasi Nutrisi",
//            deskripsi_simulasi = "Edukasi simulasi nutrisi bayi adalah kegiatan pemberian informasi dan pelatihan tentang nutrisi bayi kepada ibu atau orang tua bayi. Kegiatan ini bertujuan untuk meningkatkan pengetahuan dan keterampilan ibu atau orang tua dalam memberikan nutrisi yang tepat bagi bayi, sehingga bayi dapat tumbuh dan berkembang dengan optimal. Edukasi simulasi nutrisi bayi biasanya dilakukan oleh tenaga kesehatan, seperti dokter, bidan, atau perawat. Kegiatan ini biasanya dilakukan di rumah sakit atau klinik, tetapi juga dapat dilakukan di rumah ibu atau orang tua bayi.",
//            gambar_simulasi = "https://images.unsplash.com/photo-1566004100631-35d015d6a491?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8YmFieXxlbnwwfHwwfHx8MA%3D%3D",
//            kebutuhan_simulasi = "\nKebutuhan Nutrisi Bayi :\n" +
//                    "Bayi membutuhkan nutrisi yang cukup untuk tumbuh dan berkembang dengan optimal. Kebutuhan nutrisi bayi tergantung pada usia, berat badan, dan aktivitas bayi.\n" +
//                    "Kebutuhan nutrisi bayi meliputi:\n" +
//                    "• Karbohidrat\n" +
//                    "• Protein\n" +
//                    "• Lemak\n" +
//                    "• Vitamin\n" +
//                    "• Mineral",
//            jenis_simulasi = "\nJenis-jenis Nutrisi Bayi:\n" +
//                    "Nutrisi bayi dapat diperoleh dari berbagai sumber, seperti:\n" +
//                    "• ASI\n" +
//                    "• Susu formula\n" +
//                    "• Makanan pendamping ASI (MPASI)\n" +
//                    "ASI adalah nutrisi terbaik bagi bayi. ASI mengandung semua nutrisi yang dibutuhkan bayi, termasuk antibodi yang dapat membantu melindungi bayi dari infeksi.\n" +
//                    "Susu formula dapat diberikan kepada bayi yang tidak dapat menyusui atau yang tidak mendapatkan ASI yang cukup. Susu formula harus dipilih sesuai dengan usia dan kebutuhan bayi.\n" +
//                    "MPASI mulai diberikan kepada bayi setelah usia 6 bulan. MPASI mengandung berbagai macam nutrisi yang dibutuhkan bayi untuk tumbuh dan berkembang.",
//            cara_simulasi = "\nCara Memberikan Nutrisi Bayi :\n" +
//                    "ASI diberikan kepada bayi secara langsung dari payudara ibu. Bayi dapat menyusui kapan saja bayi menginginkannya.\n" +
//                    "Susu formula diberikan kepada bayi dengan menggunakan botol susu. Bayi dapat diberikan susu formula dengan frekuensi yang ditentukan oleh dokter atau tenaga kesehatan lainnya.\n" +
//                    "MPASI diberikan kepada bayi dengan cara menyuapi bayi menggunakan sendok. Bayi dapat diberikan MPASI dengan frekuensi 3 kali sehari.",
//            halHal_simulasi = "\nHal-hal yang Perlu Diperhatikan dalam memberikan Nutrisi Bayi:\n" +
//                    "Berikut adalah beberapa hal yang perlu diperhatikan dalam memberikan nutrisi bayi:\n" +
//                    "• Cuci tangan dengan sabun dan air mengalir sebelum dan sesudah memberikan nutrisi bayi.\n" +
//                    "• Gunakan peralatan makan yang bersih dan steril.\n" +
//                    "• Berikan nutrisi bayi dengan suhu yang sesuai.\n" +
//                    "• Jangan memaksakan bayi untuk makan jika bayi tidak mau."
//        ),
//        listOf(
//            SubSimulasi(
//                judul_video = "Stimulasi Wajib Untuk Bayi 0-6 Bulan Yang Harus Mama Lakukan | Parent University",
//                deskripsi_video = "Deskripsi Video 1",
//                url_video = "I9oflniWFBs",
//                durasi_video = 150,
//                sudah_ditonton = false
//            ),
//            SubSimulasi(
//                judul_video = "Cara Mengajarkan Bayi Mengunyah Makanan",
//                deskripsi_video = "Deskripsi Video 2",
//                url_video = "JoH0Ml2iOSM",
//                durasi_video = 180,
//                sudah_ditonton = false
//            ),
//            SubSimulasi(
//                judul_video = "Cara Asik Ajari Gizi Seimbang dengan Lagu: Lagu \"Isi Piringku\"",
//                deskripsi_video = "Deskripsi Video 3",
//                url_video = "9ywtyq_Q7cI",
//                durasi_video = 180,
//                sudah_ditonton = false
//            ),
//            SubSimulasi(
//                judul_video = "Nutrisi Penting untuk 1000 Hari Pertama Kehidupan Anak",
//                deskripsi_video = "Deskripsi Video 4",
//                url_video = "UCp145DCT6M",
//                durasi_video = 180,
//                sudah_ditonton = false
//            ),
//            SubSimulasi(
//                judul_video = "Mengapa ASI Itu Penting?",
//                deskripsi_video = "Deskripsi Video 5",
//                url_video = "0K1BpuivBXA",
//                durasi_video = 180,
//                sudah_ditonton = false
//            ),
//        )
//    ),
//    SimulasiWithSubSimulasi(
//        Simulasi(
//            id_simulasi = 3,
//            judul_simulasi = "Simulasi Pijat Bayi",
//            deskripsi_simulasi = "Pijat bayi adalah salah satu bentuk stimulasi yang dapat diberikan kepada bayi. Pijat bayi memiliki banyak manfaat, antara lain:\n" +
//                    "• Meningkatkan sirkulasi darah\n" +
//                    "• Meningkatkan pernapasan\n" +
//                    "• Meningkatkan pencernaan\n" +
//                    "• Meningkatkan kekebalan tubuh\n" +
//                    "• Memperkuat hubungan antara ibu dan bayi\n\n" +
//                    "Edukasi simulasi pijat bayi adalah kegiatan pemberian informasi dan pelatihan tentang pijat bayi kepada ibu atau orang tua bayi. Kegiatan ini bertujuan untuk meningkatkan pengetahuan dan keterampilan ibu atau orang tua dalam melakukan pijat bayi dengan benar.\n" +
//                    "Edukasi simulasi pijat bayi biasanya dilakukan oleh tenaga kesehatan, seperti dokter, bidan, atau perawat. Kegiatan ini biasanya dilakukan di rumah sakit atau klinik, tetapi juga dapat dilakukan di rumah ibu atau orang tua bayi.",
//            gambar_simulasi = "https://images.unsplash.com/photo-1546015720-b8b30df5aa27?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fGJhYnl8ZW58MHx8MHx8fDA%3D",
//            tips_simulasi = "\nTips Edukasi Simulasi Pijat Bayi :\n" +
//                    "• Gunakan bahasa yang mudah dipahami oleh ibu atau orang tua.\n" +
//                    "• Gunakan alat bantu, seperti gambar atau video, untuk mempermudah penjelasan.\n" +
//                    "• Berikan kesempatan kepada ibu atau orang tua untuk bertanya.\n" +
//                    "• Lakukan latihan pijat bayi secara bertahap, mulai dari teknik yang paling dasar.\n" +
//                    "• Berikan bimbingan dan koreksi yang diperlukan.\n" +
//                    "\n" +
//                    "Dengan edukasi simulasi pijat bayi, ibu atau orang tua dapat memperoleh pengetahuan dan keterampilan yang dibutuhkan untuk melakukan pijat bayi dengan benar. Hal ini akan membantu bayi untuk mendapatkan manfaat pijat bayi secara optimal.",
//            teknik_simulasi = "\nTeknik Pijat Bayi : (Buat Row of Card)"
//        ),
//        listOf(
//            SubSimulasi(
//                judul_video = "Cara Stimulasi Pijat Pada Bayi Bersama dr. Bonnie Arseno, SpA || RS Sari Asih Group",
//                deskripsi_video = "Deskripsi Video 1",
//                url_video = "RNVA_AdQMlU",
//                durasi_video = 200,
//                sudah_ditonton = true
//            ),
//            SubSimulasi(
//                judul_video = "Pijat Kaki Bayi untuk Relaksasi Otot JOHNSON'S® Baby Oil",
//                deskripsi_video = "Deskripsi Video 2",
//                url_video = "90B6UhpZqcE",
//                durasi_video = 110,
//                sudah_ditonton = false
//            ),
//            SubSimulasi(
//                judul_video = "Pijat Punggung Bayi Membantu Tidur Lebih Nyenyak dengan JOHNSON'S® Baby Oil",
//                deskripsi_video = "Deskripsi Video 3",
//                url_video = "n4IFPKtZA04",
//                durasi_video = 110,
//                sudah_ditonton = false
//            ),
//            SubSimulasi(
//                judul_video = "Pijat Tangan Bayi Lancarkan Pertumbuhan Motoriknya Dengan JOHNSON'S® Baby Oil",
//                deskripsi_video = "Deskripsi Video 4",
//                url_video = "udKTBSG_ilk",
//                durasi_video = 110,
//                sudah_ditonton = false
//            ),
//            SubSimulasi(
//                judul_video = "Manfaat Pijat Bayi dan Cara Melakukannya - Dr. Runi Deasiyanti, Sp. A",
//                deskripsi_video = "Deskripsi Video 5",
//                url_video = "pfgN2abUTyE",
//                durasi_video = 110,
//                sudah_ditonton = false
//            ),
//            SubSimulasi(
//                judul_video = "Tutorial Pijat Bayi Dirumah",
//                deskripsi_video = "Deskripsi Video 6",
//                url_video = "idoKmmrFsqE",
//                durasi_video = 110,
//                sudah_ditonton = false
//            ),
//        )
//    )
//)