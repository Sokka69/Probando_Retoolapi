package cl.talentodigital.probando_retoolapi.model.network

import cl.talentodigital.probando_retoolapi.model.EmpresasDetalleResponse
import cl.talentodigital.probando_retoolapi.model.EmpresasResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //Listado de empresas
    @GET("empresas")
    fun obtenerEmpresas(): Call<List<EmpresasResponse>>

    @GET("empresas/{id_empresa}")
    fun detalleEmpresa(@Path("id_empresa") empresa: Int): Call<EmpresasDetalleResponse>

}