package thomasmacquart.com.oldcoin.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import thomasmacquart.com.oldcoin.domain.CoinRepo
import thomasmacquart.com.oldcoin.domain.CoinRepoImpl
import thomasmacquart.com.oldcoin.ui.model.CoinsResult

class CoinsViewModelImpl(private val coinRepo: CoinRepo) : ViewModel(), CoinsViewModel {

    private val TAG = CoinsViewModelImpl::class.java.simpleName

    val coinsObservable = MutableLiveData<List<String>>()

    override fun loadCoins() {
        coinRepo.getCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onCoinsReceived, this::onError)
    }

    private fun onCoinsReceived(coinsResult: CoinsResult) {
        val list : List<String> = coinsResult.data.map { it.name }
        coinsObservable.value = list
    }

    private fun onError(error: Throwable) {

    }
}