//package com.example.tugasutsilham
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//
//class Register : AppCompatActivity() {
//    private lateinit var mRegister: Button
//    private lateinit var mNama: EditText
//    private lateinit var mNPM: EditText
//    private lateinit var mViewNama: TextView
//    private lateinit var mViewNPM: TextView
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//        mRegister = findViewById(R.id.btnRegister)
//        mNama = findViewById(R.id.edtNama)
//        mNPM = findViewById(R.id.edtNPM)
//        mViewNama = findViewById(R.id.textViewNama)
//        mViewNPM = findViewById(R.id.textViewNPM)
//
//        mRegister.setOnClickListener{
//            val Nama = mNama.text.toString().trim()
//            val NPM = mNPM.text.toString().trim()
//
//            mViewNama.text = "Nama: $Nama"
//            mViewNPM.text = "NPM: $NPM"
//        }
//    }
//}
package com.example.tugasutsilham

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {

    private lateinit var mRegister: Button
    private lateinit var mNama: EditText
    private lateinit var mNPM: EditText
    private lateinit var mUsername: EditText
    private lateinit var mPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Menghubungkan komponen XML
        mRegister = findViewById(R.id.btnRegister)
        mNama = findViewById(R.id.edtNama)
        mNPM = findViewById(R.id.edtNPM)
        mUsername = findViewById(R.id.edtUsername2)
        mPassword = findViewById(R.id.edtPassword2)

        // Tombol Register
        mRegister.setOnClickListener {

            val nama = mNama.text.toString().trim()
            val npm = mNPM.text.toString().trim()
            val username = mUsername.text.toString().trim()
            val password = mPassword.text.toString()

            // Validasi data
            if (
                nama.isEmpty() ||
                npm.isEmpty() ||
                username.isEmpty() ||
                password.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Semua data wajib diisi!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Validasi password
            if (password.length < 8) {
                Toast.makeText(
                    this,
                    "Password minimal 8 karakter!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Menyimpan akun
            val berhasil = AccountStorage.register(
                this,
                nama,
                npm,
                username,
                password
            )

            if (berhasil) {

                Toast.makeText(
                    this,
                    "Registrasi berhasil! Silakan Login.",
                    Toast.LENGTH_LONG
                ).show()

                // Kembali ke halaman Login
                finish()

            } else {

                Toast.makeText(
                    this,
                    "Username sudah digunakan!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}