package cl.talentodigital.probando_retoolapi.model

data class EmpresasDetalleResponse(

    val id : Int,
    val logo : String,
    val ubicacion : String,
    val nombre_empresa : String,
    val fecha_fundacion : String
)
