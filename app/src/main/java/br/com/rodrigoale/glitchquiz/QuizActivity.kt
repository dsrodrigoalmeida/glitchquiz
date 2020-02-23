package br.com.rodrigoale.glitchquiz


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_quiz.*

@Suppress(
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)

class QuizActivity : AppCompatActivity() {

    private val qtdPerguntas = 10

    private val imgPerguntas = arrayListOf(
        "https://secure.static.tumblr.com/899b832161b8465d4c6bb8d704e3079a/ep3tyma/BBqni62m4/tumblr_static_tumblr_static__640.jpg",
        "https://aff5fa4925746bf9c161-fb36f18ca122a30f6899af8eef8fa39b.ssl.cf5.rackcdn.com/images/Profile_Mario.aead314d435d8e52d9a4e92a6f799c4eee08081e.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSzV3vrWQQTjp7Iwi0p6qaXsvTsZ43aHiLuswk2P4KAfx-f5Zio",
        "https://b.thumbs.redditmedia.com/0RYDdWR5WwytxsZyf4g99NgjIcFJnrV0mqTjMNaK1JE.png",
        "https://pbs.twimg.com/profile_images/378800000842955596/29008effba83e38bdc27ce6454cb11fd.png",
        "https://www.edominations.com/public/upload/citizen/39216.jpg",
        "https://cdn.iconscout.com/icon/premium/png-256-thumb/playstation-4-1546294-1316703.png",
        "https://pm1.narvii.com/5849/4ab5db52662ee128f101425cbd869d5528266d44_128.jpg",
        "https://is4-ssl.mzstatic.com/image/thumb/Purple62/v4/93/28/b2/9328b22b-84e4-d479-6054-fcb956f9ef9c/source/256x256bb.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTZRZ419kz5mVK3yj5u9wtMaARrPxb-slTDTcARAfuKXHty7qWv"
    )

    private var respostas = arrayListOf<String>()

    private val perguntas = arrayListOf(
        "Em que jogo o cavaleiro Artorias the Abysswalker aparece?",
        "Qual empresa criou o personagem Mario?",
        "Qual o nome da primeira zona do jogo Sonic the Hedgehog lançado em 1991?",
        "Qual o nome do personagem principal do jogo The Legend of Zelda?",
        "Qual o nome do jogo que aparece na imagem acima?",
        "O personagem Geralt of Rivia do jogo The Witcher é também conhecido como:",
        "Qual desses jogos NÃO é um exclusivo do Playstation 4",
        "O jogo Diablo 3 pertence a qual empresa?",
        "Qual o nome do jogo de futebol que foi criada pela empresa EA?",
        "Qual desses consoles NÃO foi feito pela Nintendo?"
    )

    private val opcoes = arrayListOf(
        "Dark Souls", "The Witcher", "Lord of the Ring: The Game", "Dragon Quest",
        "Sega", "Sony", "Nintendo", "Microsoft",
        "Chemical Plant", "Greenhill Zone", "Forest Temple", "Underground Jungle",
        "Zelda", "Cloud", "Elf", "Link",
        "Starbound", "Terraria", "Minecraft", "Block Pixel",
        "Abomination of Rivia", "White Wolf", "Butcher of Draviklan", "Old Knight",
        "God of War", "Bloodborne", "Final Fantasy XV", "The Last of Us",
        "Sony", "Crate Entertainment", "THQ Nordic", "Blizzard",
        "Fifa", "International Super Star Soccer", "Winning Eleven", "Pro Evolution Soccer",
        "Game Boy", "Wii 2", "Virtual Boy", "Nintendo 64"
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val imgViewQuiz = findViewById<ImageView>(R.id.imagemPergunta)
        var contador = intent.getIntExtra("contador", 1)
        var contadorOpcoes = intent.getIntExtra("contadorOpcoes", 0)
        val imgPergunta = imgPerguntas[contador - 1]
        val btnResponder: Button = findViewById(R.id.responder)
        val pergunta: TextView = findViewById(R.id.perguntaLabel)
        val opcao1: RadioButton = findViewById(R.id.opcao1)
        val opcao2: RadioButton = findViewById(R.id.opcao2)
        val opcao3: RadioButton = findViewById(R.id.opcao3)
        val opcao4: RadioButton = findViewById(R.id.opcao4)

        Picasso.get()
            .load(imgPergunta)
            .into(imgViewQuiz)


        pergunta.text = perguntas[contador - 1]
        opcao1.text = opcoes[contadorOpcoes]
        opcao2.text = opcoes[contadorOpcoes + 1]
        opcao3.text = opcoes[contadorOpcoes + 2]
        opcao4.text = opcoes[contadorOpcoes + 3]


        btnResponder.setOnClickListener {

            contadorOpcoes += 4

            val id: Int = opcoesRadioGroup.checkedRadioButtonId
            val opcaoEscolhida: RadioButton = findViewById(id)

            if (contador > 1) {

                respostas = intent.getStringArrayListExtra("respostas")

            }

            respostas.add(opcaoEscolhida.text.toString())


            if (contador < qtdPerguntas) {

                contador++

                val intentQuiz = Intent(this, QuizActivity::class.java)
                intentQuiz.putExtra("respostas", respostas)
                intentQuiz.putExtra("contador", contador)
                intentQuiz.putExtra("contadorOpcoes", contadorOpcoes)
                startActivity(intentQuiz)

            } else {

                val intentResultado = Intent(this, ResultadoActivity::class.java)
                intentResultado.putExtra("respostas", respostas)
                intentResultado.putExtra("qtdPerguntas", qtdPerguntas)
                startActivity(intentResultado)

            }
        }
    }
}



