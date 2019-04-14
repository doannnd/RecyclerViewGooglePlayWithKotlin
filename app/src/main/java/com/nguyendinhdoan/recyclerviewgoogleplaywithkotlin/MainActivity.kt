package com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin.adapter.MyDataAdapter
import com.nguyendinhdoan.recyclerviewgoogleplaywithkotlin.model.MyData
import dmax.dialog.SpotsDialog

class MainActivity : AppCompatActivity() {

    private lateinit var myDataRecyclerView: RecyclerView
    private lateinit var db: DatabaseReference
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupUI()
    }

    private fun initViews() {
        myDataRecyclerView = findViewById(R.id.google_play_recycler_view)
        dialog = SpotsDialog.Builder().setContext(this).build()
    }

    private fun setupUI() {
        dialog.show()
        setupFirebase()
    }

    private fun setupFirebase() {
        db = FirebaseDatabase.getInstance().getReference("MyData")
        db.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
               val myDataList = ArrayList<MyData>()
                for (my in p0.children) {
                    val myData = my.getValue(MyData::class.java)
                    myDataList.add(myData!!)
                }
                setupRecyclerView(myDataList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
                Log.d("error", error.message)
                dialog.dismiss()
            }

        })
    }

    private fun setupRecyclerView(myDataList: ArrayList<MyData>) {
        myDataRecyclerView.setHasFixedSize(true)
        myDataRecyclerView.layoutManager = LinearLayoutManager(this)
        val myDataAdapter = MyDataAdapter(this, myDataList)
        myDataRecyclerView.adapter = myDataAdapter
        dialog.dismiss()
    }
}
