package com.example.tp2_veterinaria

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp2_veterinaria.databinding.ActivityMainBinding
import com.example.tp2_veterinaria.models.Animal
import com.example.tp2_veterinaria.models.Veterinario
import com.example.tp2_veterinaria.models.VeterinarioActivity



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.goToRecepcion.setOnClickListener{view ->
            if(MyApplication.Veterinarios.any { veterinario -> veterinario.Disponible }){
                val intent = Intent(this, RecepcionActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Actualmente no se encuentran veterinarios disponibles.", Toast.LENGTH_LONG).show()
            }

        }

        binding.goToPedro.setOnClickListener{view ->
            val intent = Intent(this, VeterinarioActivity::class.java)
            intent.putExtra("Nombre", "Pedro")
            startActivity(intent)
        }

        binding.goToJuan.setOnClickListener{view ->
            val intent = Intent(this, VeterinarioActivity::class.java)
            intent.putExtra("Nombre", "Juan")
            startActivity(intent)
        }
    }
}
class MyApplication: Application() {
    companion object {
        val Mascotas = ArrayList<Animal>();
        var DrPedro = Veterinario("Pedro" , arrayOf<String>("Perro") , 3 , true , ArrayList<Animal>());
        var DrJuan = Veterinario("Juan" , arrayOf<String>("Perro" , "Gato" , "Conejo") , 5 , true , ArrayList<Animal>());
        val Veterinarios = arrayOf<Veterinario>(DrPedro , DrJuan)
    }
}




