package android.ncdev.network.models.base

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("status") val status: String? = null,
    @SerializedName("code") val code: String? = null,
    @SerializedName("error") val error: String? = null
)
