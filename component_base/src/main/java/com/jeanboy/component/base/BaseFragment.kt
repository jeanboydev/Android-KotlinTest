package com.jeanboy.component.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

/**
 *
 * @author caojianbo
 * @since 2020/1/13 11:16
 */

abstract class BaseFragment : Fragment() {
    var toolbar: Toolbar? = null

    fun setupArguments(args: Bundle?) {}
    abstract fun getLayoutId(): Int
    abstract fun setupView(view: View, savedInstanceState: Bundle?)
    abstract fun setupData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupArguments(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupData()
    }
}