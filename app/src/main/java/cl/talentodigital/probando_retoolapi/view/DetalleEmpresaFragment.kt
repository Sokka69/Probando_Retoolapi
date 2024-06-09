package cl.talentodigital.probando_retoolapi.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.probando_retoolapi.databinding.FragmentDetalleEmpresaBinding
import cl.talentodigital.probando_retoolapi.viewmodel.EmpresasViewModel
import com.squareup.picasso.Picasso


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID_EMPRESA = "id_empresa"

/**
 * A simple [Fragment] subclass.
 * Use the [DetalleEmpresaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetalleEmpresaFragment : Fragment() {

    private var idEmpresa: Int = 0

    private var _binding: FragmentDetalleEmpresaBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idEmpresa = it.getInt(ID_EMPRESA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleEmpresaBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Configuracion ViewModel
        var viewModelEmpresa = ViewModelProvider(this).get(EmpresasViewModel::class.java)

        //Configurar Observador
        viewModelEmpresa.detalleEmpresa.observe(this) { datosEmpresa ->
            binding.txtEmpresa.text = datosEmpresa.nombre_empresa
            binding.txtUbicacionDetalle.text = datosEmpresa.ubicacion

            Picasso.get().load(datosEmpresa.logo).into(binding.imagenLogo)
        }
        viewModelEmpresa.errores.observe(this) {

        }
            //Configurar click boton
            binding.hacerAlgo.setOnClickListener {
                val url = "https://www.google.cl"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)

            }

        // Ejecucion de Funciones
        viewModelEmpresa.obtenerDetalleEmpresa(idEmpresa)

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param idEmpresa Como se llama el parametro.
         * @return A new instance of fragment DetalleEmpresaFragment.
         */

        @JvmStatic
        fun newInstance(idEmpresa: Int) =
            DetalleEmpresaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID_EMPRESA, idEmpresa)
                }
            }
    }
}