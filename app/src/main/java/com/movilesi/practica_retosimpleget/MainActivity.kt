package com.movilesi.practica_retosimpleget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.movilesi.practica_retosimpleget.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue = Volley.newRequestQueue(this)
        //getPokemonList(5)

        var number: Int = 0

        binding.btnUpdatePokemon.setOnClickListener {
            number = binding.etPokemonAmount.text.toString().toInt()
            getPokemonList(number)
        }

/*        val fakePokemonData: Array<JSONObject> = arrayOf(
            JSONObject("{\"name\": \"ejemplo1\"}"),
            JSONObject("{\"name\": \"ejemplo2\"}"),
            JSONObject("{\"name\": \"ejemplo3\"}")
        )
*/
    }

    fun getPokemonList(listAmount: Int){
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${listAmount}"

        val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject>{ response ->
            Log.i("JSONRESPONSE", response.getJSONArray("results").toString())

            binding.rvPokemonCreatures.adapter = MainAdapter(response.getJSONArray("results"))
        },
            Response.ErrorListener { error ->
                Log.w("JSONRESPONSE", error.message as String)
            })

        queue.add(jsonRequest)
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}