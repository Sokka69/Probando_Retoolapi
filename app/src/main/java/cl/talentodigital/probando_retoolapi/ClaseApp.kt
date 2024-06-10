package cl.talentodigital.probando_retoolapi

import android.app.Application
import androidx.room.Room
import cl.talentodigital.probando_retoolapi.model.bd.AppDataBase

class ClaseApp : Application () {

    companion object {
        lateinit var database: AppDataBase
    }
        override fun onCreate() {
            super.onCreate()
            database = Room.databaseBuilder(
            applicationContext, AppDataBase::class.java, "bd_empresas"
            ).build()
    }
}