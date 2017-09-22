package me.sadboyz.freelo.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Usuario on 22/09/2017.
 */

public class SadboyzTextView extends android.support.v7.widget.AppCompatTextView {
    public SadboyzTextView(Context context) {
        super(context);
        this.setFont();
    }

    public SadboyzTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setFont();
    }

    public SadboyzTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setFont();
    }

    private void setFont()
    {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/PoiretOne.ttf");
        setTypeface(font, Typeface.NORMAL);
    }
}
