//package com.example.tugasutsilham
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var mUsername: EditText
//    private lateinit var mPassword: EditText
//    private lateinit var mBtnLogin: Button
//    private lateinit var mBtnRegister: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        mUsername = findViewById(R.id.edtUsername)
//        mPassword = findViewById(R.id.edtPassword)
//        mBtnLogin = findViewById(R.id.btnLogin)
//        mBtnRegister = findViewById(R.id.btnReg)
//
//        mBtnLogin.setOnClickListener {
//            if (mUsername.text.toString().equals("Admin") && mPassword.text.toString()
//                    .equals("123")
//            ) {
//                val intent = Intent(this, MenuActivity::class.java)
//                startActivity(intent)
//            } else {
//                Toast.makeText(this, "Username or Password salah", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        mBtnRegister.setOnClickListener {
//            // Intent untuk memulai RegisterActivity
//            val intent = Intent(this, Register::class.java)
//            startActivity(intent)
//        }
//    }
//}
package com.example.tugasutsilham

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mUsername: EditText
    private lateinit var mPassword: EditText
    private lateinit var mBtnLogin: Button
    private lateinit var mBtnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUsername = findViewById(R.id.edtUsername)
        mPassword = findViewById(R.id.edtPassword)
        mBtnLogin = findViewById(R.id.btnLogin)
        mBtnRegister = findViewById(R.id.btnReg)

        // LOGIN MENGGUNAKAN AKUN REGISTER
        mBtnLogin.setOnClickListener {

            val username = mUsername.text.toString().trim()
            val password = mPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Username dan Password harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (AccountStorage.login(this, username, password)) {

                Toast.makeText(
                    this,
                    "Login berhasil",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(
                    this,
                    "Username atau Password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // MENUJU HALAMAN REGISTER
        mBtnRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}