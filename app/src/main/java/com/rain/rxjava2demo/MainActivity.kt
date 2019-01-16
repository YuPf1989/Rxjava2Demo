package com.rain.rxjava2demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rain.rxjava2demo.ui.AutoDisposeActivity
import com.rain.rxjava2demo.ui.LifeCycleActivity
import com.rain.rxjava2demo.ui.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_second.setOnClickListener(this)
        btn_lifecycle.setOnClickListener(this)
        btn_autodispose.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start_second->{startActivity(Intent(this,SecondActivity::class.java))}
            R.id.btn_lifecycle->{startActivity(Intent(this,LifeCycleActivity::class.java))}
            R.id.btn_autodispose->{startActivity(Intent(this,AutoDisposeActivity::class.java))}
        }
    }
}
