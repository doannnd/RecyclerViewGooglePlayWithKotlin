package com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin.R
import com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin.model.Item
import com.squareup.picasso.Picasso

class ItemAdapter(
    private val context: Context,
    private val itemList: List<Item>?
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_recycler_view, p0, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
        itemViewHolder.itemTitleTextView.text = itemList!![position].name
        Picasso.get().load(itemList[position].image).into(itemViewHolder.itemImageView)

        itemViewHolder.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int) {
                Toast.makeText(context, "Name: " + itemList[position].name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var itemTitleTextView: TextView = view.findViewById(R.id.item_title_text_view)
        var itemImageView: ImageView = view.findViewById(R.id.item_image_view)

        private lateinit var onItemClickListener: OnItemClickListener

        fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
            this.onItemClickListener = onItemClickListener
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClickListener(v!!, adapterPosition)
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int)
    }
}