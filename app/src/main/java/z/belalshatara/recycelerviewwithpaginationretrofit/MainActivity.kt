package z.belalshatara.recycelerviewwithpaginationretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import z.belalshatara.recycelerviewwithpaginationretrofit.API.Request
import z.belalshatara.recycelerviewwithpaginationretrofit.Adapter.RecyclerAdapter
import z.belalshatara.recycelerviewwithpaginationretrofit.Configration.URL


class MainActivity : AppCompatActivity() {


    var contact = ArrayList<z.belalshatara.recycelerviewwithpaginationretrofit.Response>()

    var req: Request? = null
    var recycleradapter: RecyclerAdapter? = null
    var notLoading = true

    lateinit var rec: RecyclerView
    lateinit var layoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rec = findViewById(R.id.rec)

        layoutManager = LinearLayoutManager(this)
        rec.layoutManager = layoutManager
        recycleradapter =
            RecyclerAdapter(contact)
        rec.adapter = recycleradapter



        firstpage()
        addScrollerListener()

    }


    private fun addScrollerListener() {
        //attaches scrollListener with RecyclerView
        rec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                if (notLoading && layoutManager.findLastCompletelyVisibleItemPosition() == contact.size - 1) {


                    notLoading = false


                    req!!.load(contact.size - 1).enqueue(object : Callback<List<z.belalshatara.recycelerviewwithpaginationretrofit.Response>> {
                        override fun onResponse(
                            call: Call<List<z.belalshatara.recycelerviewwithpaginationretrofit.Response>>,
                            response: Response<List<z.belalshatara.recycelerviewwithpaginationretrofit.Response>>
                        ) {
                            contact.removeAt(contact.size - 1)
                            recycleradapter!!.notifyItemRemoved(contact.size)


                            if (response.isSuccessful) {
                                contact.addAll(response!!.body()!!)
                                rec.adapter!!.notifyDataSetChanged()
                                notLoading = true
                                contact.add(Response("", "", "progress"))
                                recycleradapter!!.notifyItemInserted(contact.size - 1)

                                if (response.body()!!.isEmpty()) {
                                    contact.removeAt(contact.size - 1)
                                    recycleradapter!!.notifyItemRemoved(contact.size)
                                }

                            }
                        }

                        override fun onFailure(call: Call<List<z.belalshatara.recycelerviewwithpaginationretrofit.Response>>, t: Throwable) {

                            Toast.makeText(this@MainActivity, "error", Toast.LENGTH_LONG)
                        }
                    })
                }

            }
        })

    }


    fun firstpage() {
        req = URL.apiService


        req!!.params().enqueue(object : Callback<List<z.belalshatara.recycelerviewwithpaginationretrofit.Response>> {
            override fun onResponse(
                call: Call<List<z.belalshatara.recycelerviewwithpaginationretrofit.Response>>,
                response: Response<List<z.belalshatara.recycelerviewwithpaginationretrofit.Response>>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@MainActivity,
                        response.body()!![0].toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    contact.addAll(response.body()!!)
                    rec.adapter!!.notifyDataSetChanged()

                    contact.add(Response("", "", "progress"))
                    recycleradapter!!.notifyItemInserted(contact.size - 1)
                }
            }

            override fun onFailure(call: Call<List<z.belalshatara.recycelerviewwithpaginationretrofit.Response>>, t: Throwable) {

                Toast.makeText(this@MainActivity, "error", Toast.LENGTH_LONG).show()
            }
        })
    }


}




