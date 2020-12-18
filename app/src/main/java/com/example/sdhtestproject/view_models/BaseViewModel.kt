package com.example.sdhtestproject.view_models

import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter

open class BaseViewModel : BaseObservable() {

    @BindingAdapter("android:text")
    fun setTextResource(textView: TextView, text: CharSequence) {
        textView.text = text
    }

}