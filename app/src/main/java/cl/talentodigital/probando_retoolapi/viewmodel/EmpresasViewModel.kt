package cl.talentodigital.probando_retoolapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.probando_retoolapi.ClaseApp.Companion.database
import cl.talentodigital.probando_retoolapi.model.EmpresasDetalleResponse
import cl.talentodigital.probando_retoolapi.model.EmpresasResponse
import cl.talentodigital.probando_retoolapi.model.bd.EmpresaEntidad
import cl.talentodigital.probando_retoolapi.model.network.ApiService
import cl.talentodigital.probando_retoolapi.model.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ViewModel trae lista datos para empresas
 */
class EmpresasViewModel : ViewModel() {
    // LiveData
    val listaEmpresas = MutableLiveData<List<EmpresaEntidad>>()
    val detalleEmpresa = MutableLiveData<EmpresasDetalleResponse>()
    val errores = MutableLiveData<String>()

    fun listarEmpresas() {

        // Implementacion corrutina
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //llamar API
                val responce = RetrofitClient.retrofit.create(ApiService::class.java)
                val callApi = responce.obtenerEmpresas()
                callApi.enqueue(object : Callback<List<EmpresasResponse>> {
                    override fun onResponse(
                        call: Call<List<EmpresasResponse>>,
                        response: Response<List<EmpresasResponse>>
                    ) {

                        if (response.isSuccessful) {
                            val data = response.body()

                            //Lista empresas que se envian a BD local
                            val listaEmpresaMapeada = ArrayList<EmpresaEntidad>()

                            if (data != null) {
                                for (empresa in data) {
                                    val empresaAguardar = EmpresaEntidad(
                                        id_api = empresa.id,
                                        nombre_empresa = empresa.nombre_empresa,
                                        url_logo = empresa.logo,
                                        ubicacion = empresa.ubicacion,
                                        fecha_fundacion = empresa.fecha_fundacion
                                    )
                                    listaEmpresaMapeada.add(empresaAguardar)
                                }
                                GlobalScope.launch {
                                    database.empresaDao().deleteData()
                                    database.empresaDao().insertData(listaEmpresaMapeada)
                                    listaEmpresas.postValue(database.empresaDao().getData()
                                    )
                                }
                            }

                        } else {
                            errores.postValue(
                                "Error en la API - ${
                                    response.errorBody().toString()
                                }"
                            )

                        }
                    }
                    //Metodo que se va a ejecuar si hay algun error
                    override fun onFailure(call: Call<List<EmpresasResponse>>, t: Throwable) {
                        errores.postValue("Error de Falla - ${t.message}")
                    }

                })
            } catch (e: Exception) {
                e.printStackTrace()
                errores.postValue("Error al obtener datos - ${e.message}")
            }
        }
    }

    // Funcion llamada API detalle empresa
    fun obtenerDetalleEmpresa(idEmpresa: Int) {
        // Implementacion corrutina
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //llamar API
                val retroInstancia = RetrofitClient.retrofit.create(ApiService::class.java)
                val callApi = retroInstancia.detalleEmpresa(idEmpresa)
                callApi.enqueue(object : Callback<EmpresasDetalleResponse> {

                    override fun onResponse(
                        call: Call<EmpresasDetalleResponse>,
                        response: Response<EmpresasDetalleResponse>
                    ) {
                        if (response.isSuccessful) {
                            val respuesta = response.body()
                            detalleEmpresa.postValue(respuesta)
                        } else {
                            errores.postValue(
                                "Error en la API - ${
                                    response.errorBody().toString()
                                }"
                            )
                        }
                    }

                    override fun onFailure(call: Call<EmpresasDetalleResponse>, t: Throwable) {
                        errores.postValue("Error de Falla - ${t.message}")
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
                errores.postValue("Error al obtener datos - ${e.message}")
            }
        }
    }
}