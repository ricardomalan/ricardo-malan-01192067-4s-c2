package com.example.provakotlindogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun comprarDog(view: View) {
        val apiDogs = ConexaoApiDog.criar()

        val etIdDogX: EditText = findViewById(R.id.et_id_dogX)
        val idX = etIdDogX.text.toString().toInt()

        val etIdDogY: EditText = findViewById(R.id.et_id_dogY)
        val idY = etIdDogY.text.toString().toInt()

        var mensagemDogXNotFound: String = "";
        var racaDogX: String = "";
        var precoDogX: Int = 0;

        var mensagemDogYNotFound: String = "";
        var racaDogY: String = "";
        var precoDogY: Int = 0;

        apiDogs.get(idX).enqueue(object : Callback<Dogs> {
            override fun onResponse(call: Call<Dogs>, response: Response<Dogs>) {
                val dogs = response.body()
                if (dogs != null) {
                    if (dogs.equals("Not found")) {
                        mensagemDogXNotFound = "(não encontrado)";
                    } else {
                        racaDogX = dogs.raca
                        precoDogX = dogs.precoMedio
                    }
                }
            }

            override fun onFailure(call: Call<Dogs>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })

        apiDogs.get(idY).enqueue(object : Callback<Dogs> {
            override fun onResponse(call: Call<Dogs>, response: Response<Dogs>) {
                val dogs = response.body()
                if (dogs != null) {
                    if (dogs.equals("Not found")) {
                        mensagemDogYNotFound = "(não encontrado)";
                    } else {
                        racaDogY = dogs.raca
                        precoDogY = dogs.precoMedio
                    }
                }
            }

            override fun onFailure(call: Call<Dogs>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })


        if (mensagemDogXNotFound === "" && mensagemDogYNotFound === "") {
            val resultTela = Intent(applicationContext, ResultActivity::class.java)

            resultTela.putExtra("racaDogX", racaDogX)
            resultTela.putExtra("racaDogY", racaDogY)
            resultTela.putExtra("precoDogX", precoDogX)
            resultTela.putExtra("precoDogY", precoDogY)


            startActivity(resultTela)
        } else if (mensagemDogXNotFound != "" && mensagemDogYNotFound === "") {
            val resultTela = Intent(applicationContext, ResultActivity::class.java)

            resultTela.putExtra("mensagemDogX", mensagemDogXNotFound)
            resultTela.putExtra("racaDogY", racaDogY)
            resultTela.putExtra("precoDogY", precoDogY)


            startActivity(resultTela)
        } else if (mensagemDogXNotFound === "" && mensagemDogYNotFound != "") {
            val resultTela = Intent(applicationContext, ResultActivity::class.java)

            resultTela.putExtra("mensagemDogY", mensagemDogYNotFound)
            resultTela.putExtra("racaDogX", racaDogX)
            resultTela.putExtra("precoDogX", precoDogX)


            startActivity(resultTela)
        } else {
            val resultTela = Intent(applicationContext, ResultActivity::class.java)

            resultTela.putExtra("mensagemDogX", mensagemDogXNotFound)
            resultTela.putExtra("mensagemDogY", mensagemDogYNotFound)


            startActivity(resultTela)
        }

    }
}