package com.example.tp2_veterinaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.tp2_veterinaria.databinding.RecepcionBinding
import com.example.tp2_veterinaria.models.Animal

class RecepcionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        val binding = RecepcionBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.subirMascota.setOnClickListener { view ->
            uploadMascota(binding);
        }
    }

    private fun uploadMascota(binding: RecepcionBinding) {
        val nombre: String = binding.nombreAnimal.text.toString();
        val tipoId: Int = binding.radioGroupTipo.checkedRadioButtonId;
        val tipoButton: RadioButton = findViewById(tipoId);
        val tipo: String = tipoButton.text.toString();
        val raza: String = binding.razaAnimal.text.toString();
        val edad: Int = binding.edadAnimal.text.toString().toInt();
        val causa: String = binding.Causa.text.toString();
        val mascota = Animal(nombre, tipo, raza, 2, causa);
        updateGlobalState(mascota)
    }

    private fun updateGlobalState(mascota: Animal) {
        var veterinariosDisponibles: Boolean = false;
        for (veterinario in MyApplication.Veterinarios) {
            if (veterinario.Disponible && veterinario.TiposDeAnimales.indexOf(mascota.Tipo) > -1 && veterinariosDisponibles === false) {
                MyApplication.Mascotas.add(mascota);
                veterinario.Pacientes.add(mascota);
                veterinario.Capacidad--;
                veterinario.Disponible = veterinario.Capacidad > 0
                veterinariosDisponibles = true;
                Toast.makeText(
                    this,
                    "${mascota.Nombre} ha sido agendado con Dr. ${veterinario.Nombre}",
                    Toast.LENGTH_LONG
                ).show();
            }
        }
        if (!veterinariosDisponibles) {
            Toast.makeText(
                this,
                "En estos momentos no hay veterinarios disponibles para atender a ${mascota.Nombre} :(",
                Toast.LENGTH_LONG
            ).show();
        }
    }
}