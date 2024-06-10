package cl.talentodigital.probando_retoolapi.model.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de la BD
 */
@Entity(tableName = "empresas")
data class EmpresaEntidad(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val id_api: Int,
    val nombre_empresa: String,
    val url_logo: String,
    val ubicacion: String,
    val fecha_fundacion: String

)