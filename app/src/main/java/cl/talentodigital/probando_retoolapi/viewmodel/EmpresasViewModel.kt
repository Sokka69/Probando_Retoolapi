package cl.talentodigital.probando_retoolapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.probando_retoolapi.model.EmpresasResponce
import cl.talentodigital.probando_retoolapi.model.network.ApiService
import cl.talentodigital.probando_retoolapi.model.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ViewModel trae lista datos para empresas
 */
class EmpresasViewModel : ViewModel() {
    // LiveData
    val listaEmpresas = MutableLiveData<List<EmpresasResponce>>()
    val detalleEmpresa = MutableLiveData<EmpresasResponce>()
    val error = MutableLiveData<String>()

    fun listarEmpresas()  {
            // Implementacion corrutina
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //llamar API
                val responce = RetrofitClient.retrofit.create(ApiService::class.java)
                val callApi = responce.getEmpresas()
                callApi.enqueue(object : Callback<List<EmpresasResponce>> {
                    override fun onResponse(
                        call: Call<List<EmpresasResponce>>,
                        response: Response<List<EmpresasResponce>>
                    ) {

                        if (response.isSuccessful) {
                            val data = response.body()
                            listaEmpresas.postValue(data)
                    }else {
                            error.postValue("Error en la API - ${response.errorBody().toString()}")


                        }
                    }
                    override fun onFailure(call: Call<List<EmpresasResponce>>, t: Throwable) {
                        error.postValue("Error de Falla - ${t.message}")
                    }

                })
            }catch (e: Exception) {
                e.printStackTrace()
                error.postValue("Error al obtener datos - ${e.message}")
            }
        }





        }
    }
}