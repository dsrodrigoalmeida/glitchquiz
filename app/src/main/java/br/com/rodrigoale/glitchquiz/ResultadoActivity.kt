package br.com.rodrigoale.glitchquiz


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

class ResultadoActivity : AppCompatActivity() {

    private val gabarito = arrayListOf(
        "Dark Souls", "Nintendo", "Greenhill Zone", "Link",
        "Minecraft", "White Wolf", "Final Fantasy XV", "Blizzard", "Fifa", "Wii 2"
    )

    private var score = 0

    private var logo = "https://c-sf.smule.com/rs-s77/arr/2e/c1/3b174578-8d6a-41b3-b137-67beab4d7a01.jpg"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val textView: TextView = findViewById(R.id.textView)
        val imgViewResultado = findViewById<ImageView>(R.id.imagemResultado)
        val respostas: ArrayList<String> = intent.getStringArrayListExtra("respostas")
        val qtdPerguntas = intent.getIntExtra("qtdPerguntas",0)
        val btnReiniciarJogo : Button = findViewById(R.id.reiniciarJogo)

        Picasso.get()
            .load(logo)
            .into(imgViewResultado)


        for (indice in 0 until qtdPerguntas) {

            if (respostas[indice] == gabarito[indice]) {
                this.score += 10

            }
        }

        when {

            score <= 30 ->
                textView.text =
                    "Seu score foi $score. Jogue novamente pra tentar conseguir um score mais alto"

            score <= 70 -> textView.text =
                "Seu score de $score foi bom. Jogue novamente pra tentar obter um score maior."

            score <= 90 -> textView.text =
                "Seu score de $score foi excelente. Se quiser alcançar tentar conseguir uma pontuação perfeita, jogue novamente"

            else -> textView.text =
                "Seu score de $score demonstra que é um grande expert em jogos. Parabens!"

        }

        btnReiniciarJogo.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)

        }
    }
}
