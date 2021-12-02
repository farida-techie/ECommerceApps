package com.malkinfo.ecommerceapps.view

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.malkinfo.ecommerceapps.R
import com.malkinfo.ecommerceapps.adapter.MyViewPagerAdapter
import com.malkinfo.ecommerceapps.uiltels.PrefManger

class SliderActivity : AppCompatActivity() {

    private var viewPager:ViewPager? = null
    private var myViewPagerAdapter:MyViewPagerAdapter? = null
    private var dotsLayout :LinearLayout? = null
    private lateinit var dots :Array<TextView?>
    private lateinit var layouts:IntArray
    private var btnSkip: Button? = null
    private var btnNext: Button? = null
    private var prefManager :PrefManger? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)
        // checking for first time launch - before calling setContentView()
        prefManager = PrefManger(this)
        if (!prefManager!!.isFirstTimeLaunch){
            launchHomeScreen()
            finish()
        }
        // Making notification bar transparent 
        if (Build.VERSION.SDK_INT >= 21){
            window.decorView.systemUiVisibility = 
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE  or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(R.layout.activity_slider)
        
        viewPager = findViewById<ViewPager>(R.id.view_pager)
        dotsLayout = findViewById(R.id.layout_dots)
        btnSkip = findViewById(R.id.btn_skip)
        btnNext = findViewById(R.id.btn_next)
        
        //layout of all welcome slider 
        // add few more layouts if you want 
        layouts = intArrayOf(
            R.layout.welcome_slider1,
            R.layout.welcome_slider2,
            R.layout.welcome_slider3,
            R.layout.welcome_slider4,
        )
        //adding bottom dots 
        addBottomDots(0)
        
        // making notification bar transparent
        changeStatusBarColor()
        
        myViewPagerAdapter = MyViewPagerAdapter(this,layouts)
        viewPager!!.adapter = myViewPagerAdapter
        
        viewPager!!.addOnPageChangeListener(viewPagerChangeListener)
        
        btnSkip!!.setOnClickListener { launchHomeScreen() }
        btnNext!!.setOnClickListener { 
            // checking for last page 
            // if last page home screen will be launched 
            val current = getItem(+1)
            
            if (current<layouts.size){
                // move to next screen 
                viewPager!!.currentItem = current
            }
            else{
                launchHomeScreen()
            }
        }
        
        
        
    }

    private fun getItem(i: Int): Int {
         return viewPager!!.currentItem +i
    }
    val viewPagerChangeListener:ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
               addBottomDots(position)
                //changing the text button text 'NEXT' / 'GOT IT'
                if (position == layouts.size -1 ){
                    //last page make text to GOT IT
                    btnNext!!.text = getString(R.string.start)
                    btnSkip!!.visibility = View.GONE
                }
                else{
                    btnNext!!.text = getString(R.string.next)
                    btnSkip!!.visibility = View.VISIBLE
                }
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

        }

    private fun changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }

    }

    private fun addBottomDots(currentPage: Int) {
          dots = arrayOfNulls(layouts.size)
        val colorActive = resources.getIntArray(R.array.array_dot_active)
        val colorInActive = resources.getIntArray(R.array.array_dot_inactive)
        dotsLayout!!.removeAllViews()
        for (i in dots.indices){
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226")
            dots[i]!!.textSize = 35f
            dots[i]!!.setTextColor(colorInActive[currentPage])
            dotsLayout!!.addView(dots[i])
        }
        if (dots.size> 0 ){
            dots[currentPage]!!.setTextColor(colorActive[currentPage])
        }
    }

    private fun launchHomeScreen() {
       prefManager!!.isFirstTimeLaunch = false
        startActivity(Intent(this, VerificationActivity::class.java))
        finish()
    }
}