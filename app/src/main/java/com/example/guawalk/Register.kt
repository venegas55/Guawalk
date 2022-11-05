package com.example.guawalk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    val sexSpinner = arrayOf<String>("Hombre","Mujer","Sin Especificar")
    val spinner: Spinner = findViewById(R.id.regSpinSex)

    //TODO fill spinner with array



}