package thomasmacquart.com.oldcoin.domain

import io.reactivex.Single
import thomasmacquart.com.oldcoin.ui.model.CoinsResult

interface CoinRepo {
    fun getCoins() : Single<CoinsResult>
}