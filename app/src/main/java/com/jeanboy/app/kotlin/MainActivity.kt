package com.jeanboy.app.kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.jeanboy.component.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setupView(savedInstanceState: Bundle?) {

    }

    override fun setupData() {

    }

    fun toUpdate(view: View) {
        findViewById<TextView>(R.id.tv_test).text = "哈哈"
    }

    fun toTestActivity(view: View?) {
        ARouter.getInstance().build("/test/activity").navigation()
    }
}
