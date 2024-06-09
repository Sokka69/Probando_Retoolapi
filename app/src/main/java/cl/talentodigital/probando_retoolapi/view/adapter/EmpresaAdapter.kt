package cl.talentodigital.probando_retoolapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import cl.talentodigital.probando_retoolapi.R
import cl.talentodigital.probando_retoolapi.databinding.ItemBinding
import cl.talentodigital.probando_retoolapi.model.EmpresasResponse
import cl.talentodigital.probando_retoolapi.view.DetalleEmpresaFragment
import com.squareup.picasso.Picasso

class EmpresaAdapter(
    private val listasEmpresas: List<EmpresasResponse>):
    RecyclerView.Adapter<EmpresaAdapter.EmpresaViewHolder>() {
    class EmpresaViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmpresaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listasEmpresas.size
    }

    override fun onBindViewHolder(holder: EmpresaViewHolder, position: Int) {
        val empresa = listasEmpresas[position]

        holder.binding.txtNombreEmpresa.text = empresa.nombre_empresa
        holder.binding.txtUbicacion.text = empresa.ubicacion
        holder.binding.txtFechaFundacion.text = empresa.fecha_fundacion
        // Imagen
        Picasso.get().load(empresa.logo)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.logoEmpresa)

        //Configurar el click
        holder.binding.root.setOnClickListener {
            var detalle = DetalleEmpresaFragment.newInstance(empresa.id)
            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.main, detalle)
                .addToBackStack(null).commit()
        }
    }
}