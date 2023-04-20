
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rateconverter.R
import com.example.rateconverter.models.Rate
import com.squareup.picasso.Picasso


class RateAdapter(private val mData: List<Rate>) :
    RecyclerView.Adapter<RateAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        holder.textView.text = item.name
        holder.textView2.text = item.code
        holder.textView3.text = item.price.toString()
        Picasso.get()
            .load("https://rates.self-access.org/flags/"+item.flag)
            .placeholder(R.drawable.default_flag)
            .resize(150, 80)
            .centerCrop()
            .into(holder.flag);

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var textView2: TextView
        var textView3: TextView
        var flag: ImageView

        init {
            textView = itemView.findViewById(R.id.name)
            textView2 = itemView.findViewById(R.id.code)
            textView3 = itemView.findViewById(R.id.amout)
            flag = itemView.findViewById(R.id.imageView3)

        }
    }
}