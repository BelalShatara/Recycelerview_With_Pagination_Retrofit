package z.belalshatara.recycelerviewwithpaginationretrofit.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import z.belalshatara.recycelerviewwithpaginationretrofit.R
import z.belalshatara.recycelerviewwithpaginationretrofit.Response


class RecyclerAdapter(recyclerAdapter: ArrayList<Response>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var recyclerAdapterr : ArrayList<Response>? = recyclerAdapter

    private val type_data = 0
    private val type_progrss = 1


    override fun getItemCount(): Int {
        return if (recyclerAdapterr == null) 0 else recyclerAdapterr!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        return if (viewType == type_data ) {
            DataHolder(inflater.inflate(R.layout.row, parent, false))
        } else {
            ProgressHolder(inflater.inflate(R.layout.progressbar, parent, false))
        }
    }




    override fun getItemViewType(position: Int): Int {
        return if (recyclerAdapterr!![position].category == "progress") {
             type_progrss
        } else {
            type_data
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is DataHolder) {


            holder.textTitle.text =    recyclerAdapterr!![position].title
            holder.textSubtitle.text = recyclerAdapterr!![position].des
        }
    }


    internal inner class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textTitle: TextView = itemView.findViewById(R.id.tit)
        var textSubtitle: TextView = itemView.findViewById(R.id.dess)

        init {
            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "Clicked Item: " + recyclerAdapterr!![adapterPosition].des,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    internal inner class ProgressHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}