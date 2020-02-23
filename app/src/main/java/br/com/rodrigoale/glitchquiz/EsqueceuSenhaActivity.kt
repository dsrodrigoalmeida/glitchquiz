package br.com.rodrigoale.glitchquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class EsqueceuSenhaActivity : AppCompatActivity() {

    private val logo = "https://ps.w.org/frontend-reset-password/assets/icon-256x256.png"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueceu_senha)

        val imgViewEsqueceuSenha = findViewById<ImageView>(R.id.logoEsqueceuSenha)
        val auth = FirebaseAuth.getInstance()
        val email: EditText = findViewById(R.id.email)
        val btnRedefinirSenha: Button = findViewById(R.id.redefinirSenha)

        Picasso.get()
            .load(logo)
            .into(imgViewEsqueceuSenha)

        btnRedefinirSenha.setOnClickListener {

            val emailText: String = email.text.toString()

            auth.fetchSignInMethodsForEmail(emailText)
                .addOnSuccessListener { result ->

                    val signInMethods = result.signInMethods

                    if (signInMethods!!.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {
                        auth.sendPasswordResetEmail(emailText)
                        Toast.makeText(baseContext, "Email enviado para: $emailText", LENGTH_SHORT)
                            .show()
                        val intentMain = Intent(this, MainActivity::class.java)
                        startActivity(intentMain)

                    } else {
                        Toast.makeText(
                            baseContext,
                            "Esse email não possui cadastro!",
                            LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener {

                    Toast.makeText(
                        baseContext, "Email Inválido", LENGTH_SHORT
                    ).show()
                }
        }
    }
}
