package com.jeanboy.component.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @author caojianbo
 * @since 2020/1/13 10:59
 */

abstract class BaseActivity : AppCompatActivity() {

    fun setupArguments(args: Bundle?) {}
    abstract fun getLayoutId(): Int
    abstract fun setupView(savedInstanceState: Bundle?)
    abstract fun setupData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setupArguments(intent.extras)
        setupView(savedInstanceState)
        setupData()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setupArguments(intent?.extras)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}