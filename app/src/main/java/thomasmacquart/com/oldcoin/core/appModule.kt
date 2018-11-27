package thomasmacquart.com.oldcoin.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import thomasmacquart.com.oldcoin.domain.CoinRepo
import thomasmacquart.com.oldcoin.domain.CoinRepoImpl
import thomasmacquart.com.oldcoin.service.CoinsService
import thomasmacquart.com.oldcoin.ui.viewmodel.CoinsViewModelImpl
import java.util.concurrent.TimeUnit

private const val  BASE_URL = "https://api.coinmarketcap.com/"

val appModule = module {
    // provided web components
    single { createOkHttpClient() }

    single { createWebService<CoinsService>(get(), BASE_URL) }

    factory { CoinRepoImpl(get()) as CoinRepo }

    viewModel { CoinsViewModelImpl(get()) }
}


fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}
