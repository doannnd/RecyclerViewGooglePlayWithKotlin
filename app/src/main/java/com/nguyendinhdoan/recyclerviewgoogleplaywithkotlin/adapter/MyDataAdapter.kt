package com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin.R
import com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin.model.MyData

class MyDataAdapter(
    private val context: Context,
    private val myDataList: List<MyData>?
) : RecyclerView.Adapter<MyDataAdapter.MyDataViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyDataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_my_data_recycler_view, viewGroup, false)
        return MyDataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myDataList?.size ?: 0
    }

    override fun onBindViewHolder(myDataViewHolder: MyDataViewHolder, position: Int) {
        myDataViewHolder.headerTitleTextView.text = myDataList!![position].headerTitle
        val itemList = myDataList[position].listItem

        myDataViewHolder.itemListRecyclerView.setHasFixedSize(true)
        myDataViewHolder.itemListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val itemAdapter = ItemAdapter(context, itemList)
        myDataViewHolder.itemListRecyclerView.adapter = itemAdapter
        myDataViewHolder.itemListRecyclerView.isNestedScrollingEnabled = false

        myDataViewHolder.moreButton.setOnClickListener {
            Toast.makeText(context, "Button more: " + myDataList[position].headerTitle, Toast.LENGTH_SHORT).show()
        }

    }


    class MyDataViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var headerTitleTextView: TextView = view.findViewById(R.id.header_title_text_view)
        var moreButton: Button = view.findViewById(R.id.more_button)
        var itemListRecyclerView: RecyclerView = view.findViewById(R.id.item_list_recycler_view)
    }
}