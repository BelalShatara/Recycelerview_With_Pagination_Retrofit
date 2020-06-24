package z.belalshatara.recycelerviewwithpaginationretrofit.Configration

import z.belalshatara.recycelerviewwithpaginationretrofit.API.Request

object URL {
    private const val BASE_URL = "http:192.168.1.10:3000"

    val apiService: Request = RetrofitConfige.getClient(
        BASE_URL
    )!!.create(Request::class.java)
}