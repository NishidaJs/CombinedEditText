package com.zooloo.combinededittext;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nishida Kai on 15/11/2017.
 */

public class CombinedEditText extends LinearLayout {

    private Context mContext = null;

    private LinearLayout layout = null;
    private LinearLayout contentView = null;
    private TextView label = null;
    private EditText input = null;
    private TextView warn = null;
    private TypedArray appearance = null;

    private String labelText;
    private int labelTextSize;
    private ColorStateList labelTextColor;
    private float labelWeight;
    private String inputText;
    private String inputHint;
    private int inputTextSize;
    private int inputType;
    private float contentWeight;
    private String warningText;
    private int warningTextSize;
    private ColorStateList warningTextColor;
    private int warningVisibilityState;

    public CombinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mContext = context;
        initialize(attrs);

    }

    private void initialize(AttributeSet attrs) {
        TypedArray custom = mContext.obtainStyledAttributes(attrs, R.styleable.CombinedEditText);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        layout = (LinearLayout) inflater.inflate(R.layout.combined_text_view, this, true);

        label = layout.findViewById(R.id.label);
        contentView = layout.findViewById(R.id.content);
        input = layout.findViewById(R.id.input);
        warn = layout.findViewById(R.id.warn);

        labelText = custom.getString(R.styleable.CombinedEditText_labelText);
        labelText = labelText == null ? "" : labelText;
        labelTextSize = custom.getDimensionPixelSize(R.styleable.CombinedEditText_labelTextSize, 16);
        labelTextColor = custom.getColorStateList(R.styleable.CombinedEditText_labelTextColor);
        labelWeight = custom.getFloat(R.styleable.CombinedEditText_labelWeight, 1);

        inputText = custom.getString(R.styleable.CombinedEditText_inputText);
        inputText = inputText == null ? "" : inputText;
        inputHint = custom.getString(R.styleable.CombinedEditText_inputHint);
        inputHint = inputHint == null ? "" : inputHint;
        inputTextSize = custom.getDimensionPixelSize(R.styleable.CombinedEditText_inputTextSize, 16);
        inputType = custom.getInt(R.styleable.CombinedEditText_android_inputType, EditorInfo.TYPE_TEXT_VARIATION_NORMAL);
        contentWeight = custom.getFloat(R.styleable.CombinedEditText_contentWeight, 1);

        warningText = custom.getString(R.styleable.CombinedEditText_warningText);
        warningText = warningText == null ? "" : warningText;
        warningTextSize = custom.getDimensionPixelSize(R.styleable.CombinedEditText_warningTextSize, 10);
        warningTextColor = custom.getColorStateList(R.styleable.CombinedEditText_warningTextColor);
        warningVisibilityState = custom.getInt(R.styleable.CombinedEditText_warningVisibility, INVISIBLE);

        layout.setWeightSum(labelWeight + contentWeight);
        LinearLayout.LayoutParams labelLp = (LinearLayout.LayoutParams)label.getLayoutParams();
        labelLp.width = 0;
        labelLp.weight = labelWeight;
        label.setLayoutParams(labelLp);
        LinearLayout.LayoutParams contentLp = (LinearLayout.LayoutParams)contentView.getLayoutParams();
        contentLp.width = 0;
        contentLp.weight = contentWeight;
        contentView.setLayoutParams(contentLp);

        label.setText(labelText);
        label.setTextSize(labelTextSize);
        if(labelTextColor != null) {
            label.setTextColor(labelTextColor);
        }
        input.setText(inputText);
        input.setHint(inputHint);
        input.setTextSize(inputTextSize);
        input.setInputType(inputType);
        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    warn.setVisibility(INVISIBLE);
                }
            }
        });

        warn.setText(warningText);
        warn.setTextSize(warningTextSize);
        if(warningTextColor == null) {
            warn.setTextColor(Color.RED);
        } else {
            warn.setTextColor(warningTextColor);
        }
        warn.setVisibility(warningVisibilityState);

        custom.recycle();
    }

    /**
    * Overriding this setter, so the listener will set on the input field instead of 
    * the whole view.
    */
    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        input.setOnFocusChangeListener(l);
    }
    /**
    * Overriding this setter, so the listener will set on the input field instead of 
    * the whole view.
    */
    @Override
    public void setOnClickListener(OnClickListener l) {
        input.setOnClickListener(l);
    }
    /**
    * Overriding this setter, so the listener will set on the input field instead of 
    * the whole view.
    */
    @Override
    public void setOnTouchListener(OnTouchListener l) {
        input.setOnTouchListener(l);
    }

    public void addTextWatcher(TextWatcher watcher) {
        input.addTextChangedListener(watcher);
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public int getLabelTextSize() {
        return labelTextSize;
    }

    public void setLabelTextSize(int labelTextSize) {
        this.labelTextSize = labelTextSize;
    }

    public ColorStateList getLabelTextColor() {
        return labelTextColor;
    }

    public void setLabelTextColor(ColorStateList labelTextColor) {
        this.labelTextColor = labelTextColor;
    }

    public float getLabelWeight() {
        return labelWeight;
    }

    public void setLabelWeight(int labelWeight) {
        this.labelWeight = labelWeight;
    }
    public void setLabelWeight(float labelWeight) {
        this.labelWeight = labelWeight;
    }

    public String getInputText() {
        return input.getText().toString();
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getInputHint() {
        return inputHint;
    }

    public void setInputHint(String inputHint) {
        this.inputHint = inputHint;
    }

    public int getInputTextSize() {
        return inputTextSize;
    }

    public void setInputTextSize(int inputTextSize) {
        this.inputTextSize = inputTextSize;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public float getContentWeight() {
        return contentWeight;
    }

    /**
    * Maybe removed
    */
    public void setContentWeight(int contentWeight) {
        this.contentWeight = contentWeight;
    }
    
    /**
    * Maybe removed
    */
    public void setContentWeight(float contentWeight) {
        this.contentWeight = contentWeight;
    }

    public String getWarningText() {
        return warningText;
    }

    public void setWarningText(String warningText) {
        this.warningText = warningText;
        warn.setText(this.warningText);
    }

    public int getWarningTextSize() {
        return warningTextSize;
    }

    public void setWarningTextSize(int warningTextSize) {
        this.warningTextSize = warningTextSize;
    }

    public ColorStateList getWarningTextColor() {
        return warningTextColor;
    }

    public void setWarningTextColor(ColorStateList warningTextColor) {
        this.warningTextColor = warningTextColor;
        warn.setTextColor(this.warningTextColor);
    }

    public int getWarningVisibilityState() {
        return warningVisibilityState;
    }

    public void setWarningVisibilityState(int warningVisibilityState) {
        this.warningVisibilityState = warningVisibilityState;
        warn.setVisibility(this.warningVisibilityState);
    }
}
