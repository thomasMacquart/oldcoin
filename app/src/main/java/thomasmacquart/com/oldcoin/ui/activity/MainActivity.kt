package thomasmacquart.com.oldcoin.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import thomasmacquart.com.oldcoin.R
import thomasmacquart.com.oldcoin.ui.CoinsAdapter
import thomasmacquart.com.oldcoin.ui.viewmodel.CoinsViewModelImpl

class MainActivity : AppCompatActivity() {

    private val coinsViewModel : CoinsViewModelImpl by viewModel()

    private lateinit var viewAdapter: CoinsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = CoinsAdapter()

        coins_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        coinsViewModel.coinsObservable.observe(this, Observer {
            viewAdapter.coinsList = it?.toMutableList() ?: mutableListOf()
            viewAdapter.notifyDataSetChanged()
        })


        coinsViewModel.loadCoins()
    }
}
