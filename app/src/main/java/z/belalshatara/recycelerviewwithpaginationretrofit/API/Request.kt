package z.belalshatara.recycelerviewwithpaginationretrofit.API

import retrofit2.Call
import retrofit2.http.*
import z.belalshatara.recycelerviewwithpaginationretrofit.Response


interface Request {
    @GET("first_page")
    fun params() : Call <List<Response>>


    @GET("show_more")
    fun load(@Query("index") index: Int) : Call<List<Response>>
}