package thomasmacquart.com.oldcoin

import android.app.Application
import org.koin.android.ext.android.startKoin
import thomasmacquart.com.oldcoin.core.appModule

class OldCoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}