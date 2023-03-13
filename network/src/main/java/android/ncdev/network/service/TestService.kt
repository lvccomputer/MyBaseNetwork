package android.ncdev.network.service

import android.ncdev.network.models.girld.GirlResponse
import retrofit2.http.GET

interface TestService {
    @GET("girlJson/master/girl.json")
    suspend fun getGirl(): GirlResponse
}