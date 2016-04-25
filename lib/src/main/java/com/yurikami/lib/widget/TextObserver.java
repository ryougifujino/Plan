package com.yurikami.lib.widget;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by WINFIELD on 2016/4/18.
 */
public abstract class TextObserver implements TextWatcher {
    protected int mEditTextFlag;

    public TextObserver(int editTextFlag){
        this.mEditTextFlag = editTextFlag;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public abstract void onTextChanged(CharSequence s, int start, int before, int count);

    @Override
    public void afterTextChanged(Editable s) {

    }
}
