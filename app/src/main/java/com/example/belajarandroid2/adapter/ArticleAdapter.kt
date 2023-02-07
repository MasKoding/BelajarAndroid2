package com.example.belajarandroid2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.belajarandroid2.R
import com.example.belajarandroid2.data.ArticleModel

class ArticleAdapter (
        private val articleList: ArrayList<ArticleModel>,
        private val context: Context
        ) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(){
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ) :ArticleViewHolder{
                val itemView = LayoutInflater.from(parent.context).inflate(
                    R.layout.main_recycler, parent, false
                )
                return ArticleViewHolder(itemView)
            } // buat class article view holder
            class ArticleViewHolder(itemView: View) :
                RecyclerView.ViewHolder(itemView){
                val articleImage = itemView.findViewById<ImageView>(R.id.logoImage)
                val articleName = itemView.findViewById<TextView>(R.id.textList)
            }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.articleImage.setImageResource(articleList[position].image)
        holder.articleName.text = articleList[position].title
    }

    override fun getItemCount(): Int = articleList.size
}