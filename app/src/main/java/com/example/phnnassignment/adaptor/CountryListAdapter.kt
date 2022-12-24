package com.example.phnnassignment.adaptor
import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phnnassignment.R
import com.example.phnnassignment.data.CountryModel
import kotlinx.android.synthetic.main.country_list_row.view.*

class CountryListAdapter(val activity: Activity): RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {
    private var countryList: List<CountryModel>? = null


    fun setCountryList(countryList: List<CountryModel>?) {
        this.countryList = countryList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_collection, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListAdapter.MyViewHolder, position: Int) {
        holder.bind(countryList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        if(countryList== null)return 0
        else return countryList?.size!!
    }

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        val textView : TextView = textView.findViewById(R.id.textView3)
        val imageView : ImageView = itemView.findViewById(R.id.imageView2);
        val imageView : ImageView = imageView.findViewById(R.id.imageView9);
        val imageView : ImageView = imageView.findViewById(R.id.imageView10);
        val imageView : ImageView = imageView.findViewById(R.id.imageView11);
        val imageView : ImageView = imageView.findViewById(R.id.imageView12);
        val imageView : ImageView = imageView.findViewById(R.id.imageView13);
        val textView : TextView = textView.findViewById(R.id.textView4);





        fun bind(data: CountryModel, activity: Activity) {
         //   tvName.text = data.name +"(" + data.alpha2Code+")"
           // tvCapital.text = "Capital: "+data.capital
            //tvRegion.text = "Region: "+data.region

            //GlideToVectorYou.justLoadImage(activity, Uri.parse(data.flag), flagImage)

        }
    }
}