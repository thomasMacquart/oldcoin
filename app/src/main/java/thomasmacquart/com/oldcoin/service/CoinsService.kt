package thomasmacquart.com.oldcoin.service

import io.reactivex.Single
import retrofit2.http.GET
import thomasmacquart.com.oldcoin.ui.model.CoinsResult

interface CoinsService {
    @GET("v2/listings")
    fun getCoins() : Single<CoinsResult>
}