package com.nithi.nyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nithi.nyapp.databinding.SingleNewsItemBinding
import com.nithi.nyapp.model.NytResponse

class NewsAdapter(val click:(com.nithi.nyapp.model.Result)->Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    private var list=ArrayList<com.nithi.nyapp.model.Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(SingleNewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(click,list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding:SingleNewsItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(click: (com.nithi.nyapp.model.Result) -> Unit, result: com.nithi.nyapp.model.Result) {
            binding.heading.text=result.title
            binding.date.text=result.published_date
            val imageUrl=result.media[0].media[2].url
            binding.image.load(imageUrl)

            binding.itemContraint.setOnClickListener {
                click(result)
            }
        }

    }

    fun bindData(data:List<com.nithi.nyapp.model.Result>){
        list=data as ArrayList<com.nithi.nyapp.model.Result>
        notifyDataSetChanged()
    }
}