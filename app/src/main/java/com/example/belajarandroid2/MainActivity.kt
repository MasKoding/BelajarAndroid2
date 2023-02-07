package com.example.belajarandroid2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarandroid2.adapter.ArticleAdapter
import com.example.belajarandroid2.data.ArticleModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    //inisialiasi variabel awal
    lateinit var articleRv: RecyclerView
    lateinit var articleRvAdapter: ArticleAdapter
    lateinit var articleList: ArrayList<ArticleModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        articleRv = findViewById(R.id.rv_list)
        articleList = ArrayList()
       //buat objek article adapter
        articleRvAdapter = ArticleAdapter(articleList,this)

        articleRv.adapter = articleRvAdapter
        articleList.add(ArticleModel( R.mipmap.ic_launcher,"Android Developer"))

        articleList.add(ArticleModel( R.mipmap.ic_kotlin,"Kotlin Developer"))


        articleRvAdapter.notifyDataSetChanged()
//  untuk memunculkan event move dan swipe

        val buttonAdd:Button = findViewById(R.id.btnAdd)
        val inputTask:EditText = findViewById(R.id.inputTask)
        buttonAdd.setOnClickListener {
             articleList.add(ArticleModel(R.mipmap.ic_kotlin,inputTask.text.toString()))
             articleRvAdapter.notifyDataSetChanged()
        }

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedArticle:ArticleModel = articleList.get(
                    viewHolder.adapterPosition)

                val position =viewHolder.adapterPosition
                articleList.removeAt(viewHolder.adapterPosition)

                articleRvAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                //popup
                Snackbar.make(articleRv,"Deleted "+deletedArticle.title,
                    Snackbar.LENGTH_SHORT)
                    .setAction("Undo",
                        View.OnClickListener {
                            articleList.add(position,deletedArticle)
                            articleRvAdapter.notifyItemInserted(position)
                        }).show()
            }
        }).attachToRecyclerView(articleRv)

    }
}