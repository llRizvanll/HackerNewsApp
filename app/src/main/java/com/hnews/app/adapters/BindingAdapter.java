package com.hnews.app.adapters;

import android.text.TextWatcher;
import android.widget.EditText;

import androidx.databinding.Bindable;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter("textChangedListener")
    public static void bindTextWatcher(EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

}
