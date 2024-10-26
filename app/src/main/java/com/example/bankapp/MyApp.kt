package com.example.bankapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


// root container kommer hantera alla instanser/beroenden
@HiltAndroidApp
class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()
    }

}


