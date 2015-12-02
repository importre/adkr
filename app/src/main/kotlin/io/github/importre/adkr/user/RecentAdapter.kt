package io.github.importre.adkr.user

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.importre.adkr.R
import io.github.importre.adkr.api.recent.Recent
import java.util.*

class RecentAdapter(val context: Context, val data: ArrayList<Recent>) :
        RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {

    override fun onBindViewHolder(holder: RecentViewHolder?, position: Int) {
        val recent = data[position]
        holder?.update(recent)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecentViewHolder? {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_recent, parent, false)
        view.setTag(R.id.image, view.findViewById(R.id.image))
        return RecentViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    inner class RecentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun update(recent: Recent) {
            val image = view.getTag(R.id.image) as ImageView
            val url = recent.images.standard.url
            Glide.with(context).load(url).into(image)
        }
    }
}

