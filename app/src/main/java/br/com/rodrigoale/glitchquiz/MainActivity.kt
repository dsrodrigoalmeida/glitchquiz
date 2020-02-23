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

class MainActivity : AppCompatActivity() {

    private var logo = "https://www.funkidslive.com/wp-content/uploads/2016/02/gaming-quiz.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = FirebaseAuth.getInstance()
        val imgViewMain = findViewById<ImageView>(R.id.logoMain)
        val btnLogin: Button = findViewById(R.id.login)
        val btnEsqueceuSenha: Button = findViewById(R.id.esqueceuSenha)
        val btnCadastroUsuario: Button = findViewById(R.id.cadastroUsuario)

        Picasso.get()
            .load(logo)
            .into(imgViewMain)


        btnLogin.setOnClickListener {

            val email: EditText = findViewById(R.id.email)
            val emailText = email.text.toString()
            val senha: EditText = findViewById(R.id.senha)
            val senhaText = senha.text.toString()

            auth.signInWithEmailAndPassword(emailText, senhaText)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {

                        val intent = Intent(this, QuizActivity::class.java)
                        startActivity(intent)

                    } else {

                        val errorCode: String = task.exception.toString()

                        when {
                            errorCode.contains("FirebaseAuthInvalidUserException") -> {
                                Toast.makeText(
                                    baseContext,
                                    "Esse email não possui cadastro!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                            errorCode.contains("FirebaseAuthInvalidCredentialsException") -> {
                                Toast.makeText(
                                    baseContext, "Email e/ou Senha Inválida. Tente novamente!", Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                            else -> {
                                Toast.makeText(
                                    baseContext,
                                    "Falha na autenticação. Por favor tente novamente!",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                    }
                }
        }
        btnEsqueceuSenha.setOnClickListener {

            val intentEsqueceuSenha = Intent(this, EsqueceuSenhaActivity::class.java)
            startActivity(intentEsqueceuSenha)

        }

        btnCadastroUsuario.setOnClickListener {

            val intentCadastroUsuario = Intent(this, CadastroUsuarioActivity::class.java)
            startActivity(intentCadastroUsuario)

        }
    }
}