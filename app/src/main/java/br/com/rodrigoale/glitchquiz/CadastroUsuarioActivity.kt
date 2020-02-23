package br.com.rodrigoale.glitchquiz


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class CadastroUsuarioActivity : AppCompatActivity() {

    private var logo = "https://www.agecefrio.com.br/novo-site/app/icones/associese-azul.png"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)

        val auth = FirebaseAuth.getInstance()
        val imgViewCadastroUsuario = findViewById<ImageView>(R.id.logoCadastroUsuario)
        val btnCadastrarUsuario: Button = findViewById<Button>(R.id.cadastrar)

        Picasso.get()
            .load(logo)
            .into(imgViewCadastroUsuario)


        btnCadastrarUsuario.setOnClickListener {

            val email: EditText = findViewById(R.id.email)
            val emailText = email.text.toString()
            val senha: EditText = findViewById(R.id.senha)
            val senhaText = senha.text.toString()

            auth.createUserWithEmailAndPassword(emailText, senhaText)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {

                        Toast.makeText(
                            baseContext,
                            "Cadastro realizado com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()

                        val mainIntent = Intent(this, MainActivity::class.java)
                        startActivity(mainIntent)

                    } else {

                        val exception: String = task.exception.toString()

                        when {
                            exception.contains("FirebaseAuthWeakPasswordException") -> {
                                Toast.makeText(
                                    baseContext,
                                    "A senha precisa ter no minimo 6 caracteres",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                            exception.contains("FirebaseAuthInvalidCredentialsException") -> {
                                Toast.makeText(
                                    baseContext, "Email Inválido", Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                            exception.contains("FirebaseAuthUserCollisionException") -> {
                                Toast.makeText(
                                    baseContext, "Email ja cadastrado", Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                            else -> {
                                Toast.makeText(
                                    baseContext, "Falha no cadastro. Por favor tente novamente!", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                }
        }
    }
}

