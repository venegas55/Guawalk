package com.example.guawalk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setup()


    }




    //funciones y logica del boton regresar
    private fun goBack(){
        val goBackIntent = Intent(this, AuthActivity::class.java)
        startActivity(goBackIntent)
    }

    private fun setup () {
        // funcion para regresar a auth
        val botonRegresar = findViewById<Button>(R.id.botonRegresar)
        botonRegresar.setOnClickListener {
            goBack()
        }
        // función para registrar
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val botonRegistrar = findViewById<Button>(R.id.botonRegistrar)
        botonRegistrar.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                ).addOnCompleteListener {

                    if (it.isSuccessful) {
                        goBack()
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Falla en el registro", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }









}