package thomasmacquart.com.oldcoin.domain

import io.reactivex.Single
import thomasmacquart.com.oldcoin.service.CoinsService
import thomasmacquart.com.oldcoin.ui.model.CoinsResult

class CoinRepoImpl(private val service: CoinsService) : CoinRepo {


    override fun getCoins(): Single<CoinsResult> {
        return service.getCoins()
    }
}