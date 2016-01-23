package com.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ui.R;


public class ClearableEditText extends RelativeLayout {

    EditText mEditText;
    TextView mPrefix;
    ImageButton btn_clear;


    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(attrs);


    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);


        initAttrs(attrs);

    }



    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ClearableEditText, 0, 0);
        try {

            String style = ta.getString(R.styleable.ClearableEditText_style);
            if(style != null) {
                switch (style) {
                    case "box":
                        initViews(R.layout.clearable_edit_text_box);
                        break;
                    case "form":
                        initViews(R.layout.clearable_edit_text_form);
                        break;
                }
            }else{
                initViews(R.layout.clearable_edit_text_form);
            }
            if(ta.getString(R.styleable.ClearableEditText_text )!= null){
                mEditText.setText(ta.getString(R.styleable.ClearableEditText_text));
            }
            mEditText.setHint(ta.getString(R.styleable.ClearableEditText_hint));
            mEditText.setLines(ta.getInt(R.styleable.ClearableEditText_android_lines,1));
            mEditText.setInputType(ta.getInt(R.styleable.ClearableEditText_android_inputType, InputType.TYPE_TEXT_FLAG_CAP_SENTENCES));
        } finally {
            ta.recycle();
        }
    }

    public ClearableEditText(Context context) {
        super(context);
       // initViews(R.layout.clearable_edit_text_box);
    }


    void initViews(int layoutId) {
        setSaveFromParentEnabled(false);
        LayoutInflater.from(getContext()).inflate(layoutId, this, true);
        mEditText = (EditText) findViewById(R.id.clearable_edit);
        mEditText.setSingleLine();
        mPrefix = (TextView) findViewById(R.id.prefix);
        btn_clear = (ImageButton) findViewById(R.id.clearable_button_clear);
        btn_clear.setVisibility(RelativeLayout.INVISIBLE);



        clearText();
        showHideClearButton();
    }



    public void setText(String text) {
        mEditText.setText(text);
        mEditText.setSelection(mEditText.getText().length());
    }

    public void setHint(String text) {
        mEditText.setHint(text);
    }

    public void setType(int type) {
        mEditText.setInputType(type);
    }

    public void clearText() {
        btn_clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
    }
    public void isPassword(boolean isPassword){
        if(isPassword) {
            mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public void resetText() {
        mPrefix.setText("");
        mEditText.setText("");
    }

    void showHideClearButton() {
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    btn_clear.setVisibility(RelativeLayout.VISIBLE);
                else
                    btn_clear.setVisibility(RelativeLayout.INVISIBLE);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public EditText getEditText() {
        return mEditText;
    }

    public void setPrefix(String prefix) {
        mPrefix.setText(prefix);
    }

    public Editable getText() {
        Editable text = mEditText.getText();
        return text;
    }
}