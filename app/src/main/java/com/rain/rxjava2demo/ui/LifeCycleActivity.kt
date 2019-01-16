package com.rain.rxjava2demo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.rain.rxjava2demo.R
import com.rain.rxjava2demo.loge
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_second.*
import java.util.concurrent.TimeUnit

/**
 * Author:rain
 * Date:2019/1/16 10:31
 * Description:
 */
class LifeCycleActivity:RxAppCompatActivity(), View.OnClickListener {
   val TAG = this.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn_start_count.setOnClickListener(this)
    }

    @SuppressLint("AutoDispose")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start_count -> {
                Observable.interval(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                        .subscribe {
                            tv_count.text = it.toString()
                            loge(TAG, "count:$it")
                        }
            }
        }
    }
}