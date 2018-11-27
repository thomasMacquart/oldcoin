package thomasmacquart.com.oldcoin.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.coin_item.view.*
import thomasmacquart.com.oldcoin.R

class CoinsAdapter : RecyclerView.Adapter<CoinsViewHolder>() {

    var coinsList : MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CoinsViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.coin_item, null, false)
        return CoinsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coinsList.size
    }

    override fun onBindViewHolder(viewHolder: CoinsViewHolder, position: Int) {
        val item = coinsList.get(position)
        viewHolder.bind(item)
    }


}

class CoinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val name : TextView = itemView.coin_name

    fun bind(item: String) {
        name.text = item
    }
}