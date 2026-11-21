package com.example.tugasutsilham

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Calculator : AppCompatActivity() {
    private lateinit var mAngkaPertama: EditText
    private lateinit var mAngkaKedua: EditText
    private lateinit var mBtnPlus: Button
    private lateinit var mBtnMinus: Button
    private lateinit var mBtnKali: Button
    private lateinit var mBtnBagi: Button
    private lateinit var mHasil: TextView
    private lateinit var mHargaAsli: EditText
    private lateinit var mPersenDiskon: EditText
    private lateinit var mBtnHitungDiskon: Button
    private lateinit var mHasilDiskon: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        mAngkaPertama = findViewById(R.id.edtAngkaPertama)
        mAngkaKedua = findViewById(R.id.edtAngkaKedua)
        mBtnPlus = findViewById(R.id.btnPlus)
        mBtnMinus = findViewById(R.id.btnMinus)
        mBtnKali = findViewById(R.id.btnKali)
        mBtnBagi = findViewById(R.id.btnBagi)
        mHasil = findViewById(R.id.tvHasil)

        // Menghubungkan komponen kalkulator diskon
        mHargaAsli = findViewById(R.id.edtHargaAsli)
        mPersenDiskon = findViewById(R.id.edtPersenDiskon)
        mBtnHitungDiskon = findViewById(R.id.btnHitungDiskon)
        mHasilDiskon = findViewById(R.id.tvHasilDiskon)

        // Tombol hitung diskon
        mBtnHitungDiskon.setOnClickListener {
        hitungDiskon()
        }

        mBtnPlus.setOnClickListener { hitung("+") }
        mBtnMinus.setOnClickListener { hitung("-") }
        mBtnKali.setOnClickListener { hitung("*") }
        mBtnBagi.setOnClickListener { hitung("/") }
    }

    private fun hitung(operator: String) {
        val angka1 = mAngkaPertama.text.toString().trim().toIntOrNull() ?: 0
        val angka2 = mAngkaKedua.text.toString().trim().toIntOrNull() ?: 0

        val hasil = when (operator) {
            "+" -> angka1 + angka2
            "-" -> angka1 - angka2
            "*" -> angka1 * angka2
            "/" -> if (angka2 != 0) angka1 / angka2 else 0
            else -> 0
        }

        mHasil.text = "Hasilnya adalah: $hasil"
    }

    private fun hitungDiskon() {

        val harga = mHargaAsli.text.toString().trim().toLongOrNull()
        val persen = mPersenDiskon.text.toString()
            .trim()
            .toDoubleOrNull()

        // Validasi input
        if (harga == null || persen == null) {
            mHasilDiskon.text =
                "Masukkan harga dan persentase diskon dengan benar!"
            return
        }

        if (harga < 0) {
            mHasilDiskon.text = "Harga tidak boleh negatif!"
            return
        }

        if (persen < 0 || persen > 100) {
            mHasilDiskon.text =
                "Diskon harus antara 0% sampai 100%!"
            return
        }

        // Menghitung nominal potongan
        val totalDiskon = harga.toDouble() * persen / 100

        // Menghitung harga setelah diskon
        val hargaAkhir = harga.toDouble() - totalDiskon

        // Format Rupiah
        val formatRupiah = java.text.NumberFormat
            .getCurrencyInstance(java.util.Locale("id", "ID"))

        formatRupiah.maximumFractionDigits = 0
        formatRupiah.minimumFractionDigits = 0

        // Menampilkan hasil
        mHasilDiskon.text =
            "Harga Asli: ${formatRupiah.format(harga)}\n" +
                    "Diskon: ${persen}%\n" +
                    "Potongan Harga: ${formatRupiah.format(totalDiskon)}\n" +
                    "Total Bayar: ${formatRupiah.format(hargaAkhir)}"
    }
}
