package com.exchange.hamilton.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.exchange.hamilton.data.repository.Repository
import com.exchange.hamilton.utils.PREF_NAME
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repository: Repository, app: Application) :
    AndroidViewModel(app) {

    private val sharedPreferences: SharedPreferences =
        app.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

}