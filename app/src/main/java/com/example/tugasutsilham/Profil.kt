package com.example.tugasutsilham

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Profil : AppCompatActivity() {

    private lateinit var mNPM: TextView
    private lateinit var mNama: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        // Menghubungkan TextView dengan XML
        mNPM = findViewById(R.id.textView12)
        mNama = findViewById(R.id.textView13)

        // Mengambil username akun yang sedang login
        val username = intent.getStringExtra("USERNAME") ?: ""

        // Mengambil data Nama dan NPM dari AccountStorage
        val nama = AccountStorage.getNama(this, username)
        val npm = AccountStorage.getNPM(this, username)

        // Menampilkan data pada halaman Profil
        mNPM.text = "NPM : $npm"
        mNama.text = nama
    }
}