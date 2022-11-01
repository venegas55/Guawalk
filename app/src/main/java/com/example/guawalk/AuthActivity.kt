package com.example.guawalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)


        //Variable para mandar eventos a firebase analytics
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración Firebase completa")
        analytics.logEvent("initScreen", bundle)

        //setup del Auth
        setup()


    }
    // Alerta de autenticacion fallida
    private fun showAlert() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
    //Logica para cambiar de actividad
    private fun showHome (email: String, provider: ProviderType) {

        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)

    }

    private fun setup() {
        title = "LogIn"
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val logInButton = findViewById<Button>(R.id.logInButton)
        val emailTB = findViewById<EditText>(R.id.emailTB)
        val passwordTB = findViewById<EditText>(R.id.passwordTB)
        //Logica para el boton registrar
        signUpButton.setOnClickListener {
            if (emailTB.text.isNotEmpty() && passwordTB.text.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailTB.text.toString(), passwordTB.text.toString()).addOnCompleteListener{

                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        logInButton.setOnClickListener {
            if (emailTB.text.isNotEmpty() && passwordTB.text.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailTB.text.toString(), passwordTB.text.toString()).addOnCompleteListener{

                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        }






    }
