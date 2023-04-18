package com.example.firstmvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.firstmvi.databinding.ActivityMainBinding
import com.example.firstmvi.ui.adapter.MainAdapter
import com.example.firstmvi.ui.intent.MainIntent
import com.example.firstmvi.ui.viewmodel.MainViewModel
import com.example.firstmvi.ui.viewstate.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private var _mainbinding: ActivityMainBinding? = null
    private val mainbinding: ActivityMainBinding
        get() = _mainbinding!!

    private val viewmodel: MainViewModel by viewModels()
    private val mainAdapter = MainAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbinding.root)
        initView()
        observeModel()
        lifecycleScope.launch {
            viewmodel.mainintent.send(MainIntent.GetUsers)
        }
    }

    private fun observeModel() {

        lifecycleScope.launch {
            viewmodel.state.collect {

                when (it) {
                    is MainViewState.Loading -> {
                        mainbinding.progress.visibility = View.VISIBLE
                    }
                    is MainViewState.Success -> {
                        mainbinding.progress.visibility = View.GONE
                        mainAdapter.addItems(it.data)
                    }
                    is MainViewState.Error -> {
                        mainbinding.progress.visibility = View.GONE
                    }
                    MainViewState.Idle -> {}
                }

            }
        }

    }

    private fun initView() {
        mainbinding.rvpost.adapter = mainAdapter
    }
}