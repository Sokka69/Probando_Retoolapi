package cl.talentodigital.probando_retoolapi.model.bd

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Configura Basa Datos
 */
@Database(entities = [EmpresaEntidad::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun empresaDao(): EmpresasDao


}