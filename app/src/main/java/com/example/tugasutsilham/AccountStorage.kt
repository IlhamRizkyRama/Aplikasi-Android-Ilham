package com.example.tugasutsilham

import android.content.Context
import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object AccountStorage {

    private const val PREF_NAME = "DataAkun"
    private const val ITERATIONS = 120000
    private const val KEY_LENGTH = 256

    private fun preferences(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private fun usernameKey(username: String): String {
        val encoded = Base64.encodeToString(
            username.trim().lowercase().toByteArray(Charsets.UTF_8),
            Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
        )
        return encoded
    }

    private fun hashPassword(
        password: String,
        salt: ByteArray
    ): ByteArray {

        val spec = PBEKeySpec(
            password.toCharArray(),
            salt,
            ITERATIONS,
            KEY_LENGTH
        )

        return try {
            SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(spec)
                .encoded
        } finally {
            spec.clearPassword()
        }
    }

    fun register(
        context: Context,
        nama: String,
        npm: String,
        username: String,
        password: String
    ): Boolean {

        if (
            nama.isBlank() ||
            npm.isBlank() ||
            username.isBlank() ||
            password.isEmpty()
        ) {
            return false
        }

        val prefs = preferences(context)
        val key = usernameKey(username)

        // Periksa apakah username sudah digunakan
        if (prefs.contains("hash_$key")) {
            return false
        }

        // Membuat salt acak
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)

        // Menghasilkan hash password
        val hash = hashPassword(password, salt)

        // Menyimpan data akun
        prefs.edit()
            .putString("nama_$key", nama.trim())
            .putString("npm_$key", npm.trim())
            .putString("username_$key", username.trim())
            .putString(
                "salt_$key",
                Base64.encodeToString(salt, Base64.NO_WRAP)
            )
            .putString(
                "hash_$key",
                Base64.encodeToString(hash, Base64.NO_WRAP)
            )
            .apply()

        return true
    }

    fun login(
        context: Context,
        username: String,
        password: String
    ): Boolean {

        if (username.isBlank() || password.isEmpty()) {
            return false
        }

        val prefs = preferences(context)
        val key = usernameKey(username)

        val saltString =
            prefs.getString("salt_$key", null) ?: return false

        val hashString =
            prefs.getString("hash_$key", null) ?: return false

        val salt = Base64.decode(saltString, Base64.NO_WRAP)
        val savedHash = Base64.decode(hashString, Base64.NO_WRAP)

        val enteredHash = hashPassword(password, salt)

        return MessageDigest.isEqual(savedHash, enteredHash)
    }

    fun getNama(context: Context, username: String): String {
        val key = usernameKey(username)

        return preferences(context)
            .getString("nama_$key", "") ?: ""
    }

    fun getNPM(context: Context, username: String): String {
        val key = usernameKey(username)

        return preferences(context)
            .getString("npm_$key", "") ?: ""
    }
}