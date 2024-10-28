package com.example.bankapp.API.security

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SharedPreferencesUtil {


    fun getJwtToken(context: Context): String? {

        // sparar token i shared prefs (encrypted)
         val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build();
            val sharedPrefs = EncryptedSharedPreferences.create(
            context,
            "MyPrefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

            return sharedPrefs.getString("token",null)
        }


    fun cleanPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }


}
