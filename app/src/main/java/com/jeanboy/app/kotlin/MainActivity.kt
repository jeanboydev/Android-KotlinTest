package com.jeanboy.app.kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.jeanboy.component.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setupView(savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun toUpdate(view: View) {
        findViewById<TextView>(R.id.tv_test).text = "哈哈"
    }
}
