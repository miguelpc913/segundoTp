package com.example.tp2_veterinaria.models

class Veterinario(val Nombre : String ,
                  val TiposDeAnimales : Array<String> ,
                  var Capacidad : Int ,
                  var Disponible : Boolean ,
                  val Pacientes : ArrayList<Animal> = ArrayList<Animal>())