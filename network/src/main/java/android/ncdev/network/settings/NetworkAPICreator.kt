package android.ncdev.network.settings

import retrofit2.Retrofit

class NetworkAPICreator(
    private val retrofit: Retrofit
) {
    fun <T> create(
        service: Class<T>,
    ): T {
        return retrofit.create(service)
    }
}