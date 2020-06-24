package z.belalshatara.recycelerviewwithpaginationretrofit

import com.google.gson.annotations.SerializedName

data class Response
    (@SerializedName("title") val title : String , @SerializedName("describtion") val des : String ,
     var category : String)

