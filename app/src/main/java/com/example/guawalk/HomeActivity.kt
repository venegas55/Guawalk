package com.example.guawalk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup
        val bundle: Bundle? = intent.extras
        val email:String? = bundle?.getString("email")
        val provider:String? = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun goDice() {
        val goDiceIntent = Intent(this,DiceActivity::class.java)
        startActivity(goDiceIntent)
    }

    private fun setup(email: String, provider: String) {
        // pasar valores de email y de proveedor mediante bundle...
        title = "home"
        val emailLabel = findViewById<TextView>(R.id.emailTV)
        val providerLabel = findViewById<TextView>(R.id.providerTV)
        val logOutButton = findViewById<Button>(R.id.logOutButton)
        emailLabel.text = email
        providerLabel.text = provider

        logOutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()

            }
        //pasar a la actividad de los dados
        val botonDados = findViewById<Button>(R.id.botonDados)
        botonDados.setOnClickListener(){
            goDice()


        }



    }
}