package com.rain.rxjava2demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.rain.rxjava2demo.R
import com.rain.rxjava2demo.loge
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_second.*
import java.util.concurrent.TimeUnit

/**
 * Author:rain
 * Date:2019/1/15 15:46
 * Description:
 */
class SecondActivity : AppCompatActivity(), View.OnClickListener {
    val TAG = this.javaClass.simpleName
    private var d: Disposable? = null
    private lateinit var ds: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        ds = CompositeDisposable()
        btn_start_count.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start_count -> {
                d = Observable.interval(1, TimeUnit.SECONDS)
                        .onTerminateDetach()// 当执行了d.dispose()方法后将解除上下游的引用
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            tv_count.text = it.toString()
                            loge(TAG, "count:$it")
                        }
                ds.add(d!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 这样处理发现依然存在内存泄露
        ds.clear()
    }
}