package cl.talentodigital.probando_retoolapi.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.talentodigital.probando_retoolapi.databinding.ActivityMainBinding
import cl.talentodigital.probando_retoolapi.view.adapter.EmpresaAdapter
import cl.talentodigital.probando_retoolapi.viewmodel.EmpresasViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar  Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Configurar  Vista
        setContentView(binding.root)

        //Configuracion ViewModel
        var viewModelEmpresa = ViewModelProvider(this).get(EmpresasViewModel::class.java)

        //Configurar Loader
        binding.listaEmpresa.visibility = GONE
        binding.progressBar.visibility = VISIBLE

        //Vincular RecyclerView
        binding.listaEmpresa.layoutManager = LinearLayoutManager(this)
        //Configurar Adapter
        var adapterEmpresas = EmpresaAdapter(listOf())
        binding.listaEmpresa.adapter = adapterEmpresas

        //Configurar Observador
        viewModelEmpresa.listaEmpresas.observe(this) { datosEmpresa ->
            adapterEmpresas = EmpresaAdapter(datosEmpresa)
            binding.listaEmpresa.adapter = adapterEmpresas
            binding.listaEmpresa.visibility = VISIBLE
            binding.progressBar.visibility = GONE
        }

        // Ejecucion de Funciones
        viewModelEmpresa.listarEmpresas()

    }
}