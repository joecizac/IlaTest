package com.jozze.ila.util

import android.text.Editable
import android.text.TextWatcher

class TextChangeListener(val performAction: (String) -> Unit) : TextWatcher {
    override fun afterTextChanged(editable: Editable?) {
        performAction(editable.toString())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}