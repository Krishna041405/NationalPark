package com.example.nationalparks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
class MainActivity : AppCompatActivity() {

    private lateinit var lab3RecyclerView: RecyclerView
    private lateinit var lab3Adapter: ParkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lab3RecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        lab3RecyclerView.layoutManager = LinearLayoutManager(this)

        lab3Adapter = ParkAdapter(mutableListOf())
        lab3RecyclerView.adapter = lab3Adapter
        loadParksFromAPI()
    }
    private fun loadParksFromAPI() {

        val lab3Url = "https://developer.nps.gov/api/v1/parks?limit=20&api_key=4doAG2Fdek3zSVFrpgeE2p8pevBdKBbDLqgnjXxE"
        val lab3Client = AsyncHttpClient()
        lab3Client.get(lab3Url, object : JsonHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>?,
                response: JSONObject
            ) {

                val lab3ParksArray = response.getJSONArray("data")
                val lab3ParksList = mutableListOf<Park>()

                for (i in 0 until lab3ParksArray.length()) {

                    val lab3ParkObject = lab3ParksArray.getJSONObject(i)

                    val lab3Name = lab3ParkObject.getString("fullName")
                    val lab3Description = lab3ParkObject.getString("description")

                    var lab3ImageUrl: String? = null
                    val lab3ImagesArray = lab3ParkObject.getJSONArray("images")

                    if (lab3ImagesArray.length() > 0) {
                        lab3ImageUrl = lab3ImagesArray
                            .getJSONObject(0)
                            .getString("url")
                    }

                    lab3ParksList.add(Park(lab3Name, lab3Description, lab3ImageUrl))
                }

                lab3Adapter.updateData(lab3ParksList)
            }
        })
    }
}

