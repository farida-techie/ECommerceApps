package com.malkinfo.ecommerceapps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter

class MyViewPagerAdapter(var c:Context,var layouts:IntArray):PagerAdapter()
{
    private  var layoutInflater :LayoutInflater? = null
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = c.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(layouts[position],container,false)
        container.addView(view)
        return view
    }


    override fun getCount(): Int {
        return  layouts.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view =  `object` as View
        container.removeView(view)
    }
}