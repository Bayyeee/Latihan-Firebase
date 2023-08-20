package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.firebase.databinding.ActivityRegistrasiBinding

import com.google.firebase.auth.FirebaseAuth

class RegistrasiActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var binding: ActivityRegistrasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.Login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        binding.btnR.setOnClickListener {
            val nama = binding.nama.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (nama.isEmpty()){
                binding.nama.error = "Nama tidak boleh kosong"
                binding.nama.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()){
                binding.email.error = "Email harus terisi"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.email.error = "Email tidak sesuai"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.password.error = "Password tidak boleh kosong"
                binding.password.requestFocus()
                return@setOnClickListener
            }

        // validasi password
            if (password.length < 8){
                binding.password.error = "Password Minimal 8 karakter"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email,password)
        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}