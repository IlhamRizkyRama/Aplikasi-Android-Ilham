package com.example.tugasutsilham

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MenuActivity : AppCompatActivity() {

    private lateinit var mCvCalculator: CardView
    private lateinit var mCvProfil: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        mCvCalculator = findViewById(R.id.cvCalculator)
        mCvProfil = findViewById(R.id.cvProfil)

        // Mengambil username dari halaman Login
        val username = intent.getStringExtra("USERNAME") ?: ""

        mCvCalculator.setOnClickListener {
            val intentCalculator = Intent(this, Calculator::class.java)
            startActivity(intentCalculator)
        }

        mCvProfil.setOnClickListener {
            val intentProfil = Intent(this, Profil::class.java)
            intentProfil.putExtra("USERNAME", username)
            startActivity(intentProfil)
        }
    }
}
