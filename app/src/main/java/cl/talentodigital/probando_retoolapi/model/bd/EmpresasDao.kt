package cl.talentodigital.probando_retoolapi.model.bd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * DAO Implementacion con los metodos a usar
 */
@Dao
interface EmpresasDao {



    // Metodo Insertar data
    @Insert
    fun insertData(empresa : List<EmpresaEntidad>)

    // Metodo para traer data
    @Query("SELECT * FROM empresas")
    fun getData() : List<EmpresaEntidad>

    // Metodo borrar data
    @Query("DELETE FROM empresas")
    fun deleteData()




}