package cl.talentodigital.probando_retoolapi.model.network

import cl.talentodigital.probando_retoolapi.model.EmpresasResponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //Listado de empresas
    @GET("empresas")
    fun obtenerEmpresas(): Call<List<EmpresasResponce>>

    @GET("empresas/{id_empresa}")
    fun detalleEmpresa(@Path("id_empresa") empresa: Int): Call<EmpresasResponce>

}