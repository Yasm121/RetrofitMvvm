package com.example.phnnassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.phnnassignment.adaptor.CountryListAdapter
import com.example.phnnassignment.data.CountryModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter
    lateinit var recyclerAdapter:CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setUpTransFormer()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)
            }

        })
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)

    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,2000)
    }
    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }
    private fun setUpTransFormer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.8f + r * 0.14f
        }


        viewPager2.setPageTransformer(transformer)

    }
    private fun init(){
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.image1)
        imageList.add(R.drawable.image2)
        imageList.add(R.drawable.image3)
        imageList.add(R.drawable.image4)

        adapter = ImageAdapter(imageList,viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
    private fun initRecyclerView(){
        countryListRecyclerview.layoutManager = LinearLayoutManager(this)
        countryListRecyclerview.adapter =CountryListAdapter(this)
        countryListRecyclerView.adapter = recyclerAdapter
    }
    private fun initViewModel(){
        val viewModel= ViewModelProvider(this.get(MainActivity::class.java))
        viewModel.getLiveDataObeserver().observer(this, Observer {
            List<CountryModel>!
            if (it != null) {
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"Error in getting list",Toast.LENGTH_SHORT).show()

            }
        })
        viewModel.makeAPTCall()
        }
    }
