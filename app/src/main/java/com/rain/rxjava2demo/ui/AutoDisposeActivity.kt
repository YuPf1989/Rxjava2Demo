package com.rain.rxjava2demo.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.rain.rxjava2demo.R
import com.rain.rxjava2demo.loge
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.lifecycle.autoDisposable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_second.*
import java.util.concurrent.TimeUnit

/**
 * Author:rain
 * Date:2019/1/16 12:06
 * Description:
 */
class AutoDisposeActivity:AppCompatActivity(), View.OnClickListener {
    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn_start_count.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start_count -> {
                Observable.interval(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .autoDisposable(AndroidLifecycleScopeProvider.from(this,Lifecycle.Event.ON_DESTROY))
                        .subscribe {
                            tv_count.text = it.toString()
                            loge(TAG, "count:$it")
                        }
            }
        }
    }
}