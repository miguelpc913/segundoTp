package com.example.tp2_veterinaria.models

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp2_veterinaria.MyApplication
import com.example.tp2_veterinaria.databinding.ActivityVeterinarioBinding

class VeterinarioActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVeterinarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val nombre = extras.getString("Nombre");
            if(!nombre.isNullOrEmpty()) {
                inicializarVista(binding, nombre!!)
            }
        }
    }
    private fun inicializarVista( binding : ActivityVeterinarioBinding , nombre : String){
        var veterinario : Veterinario? = MyApplication.Veterinarios.find { veterinario -> veterinario.Nombre == nombre }
        binding.nombreDeVeterinario.text = veterinario?.Nombre;
        if (veterinario != null) {
            if(veterinario.Pacientes.size > 0) {
                binding.numeroDeMascotasParaAtender.text = "Necesitar atender ${veterinario.Pacientes.size} mas paciente/s por hoy."
                var mascota : Animal = veterinario!!.Pacientes[0]
                binding.nombreDeMascotaEnVeterinario.text = "Nombre : ${mascota.Nombre}";
                binding.causaDeMascotaEnVeterinario.text = "Causa de visita : ${mascota.Causa}";
                binding.edadDeMascotaEnVeterinario.text = "Edad : ${mascota.Edad.toString()}";
                binding.tipoDeMascotaEnVeterinario.text = "Tipo : ${mascota.Tipo}";
            }
        }
    }
}