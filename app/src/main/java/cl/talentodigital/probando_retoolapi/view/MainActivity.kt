package cl.talentodigital.probando_retoolapi.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.talentodigital.probando_retoolapi.R
import cl.talentodigital.probando_retoolapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configurar  Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Configurar  Vista
        setContentView(binding.root)

    }
}