package me.sadboyz.freelo.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Usuario on 22/09/2017.
 */

public class FreeloTextView extends android.support.v7.widget.AppCompatTextView{
    public FreeloTextView(Context context) {
        super(context);
        setFont();
    }

    public FreeloTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public FreeloTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    private void setFont()
    {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Pacifico-Regular.ttf");
        setTypeface(font, Typeface.NORMAL);
    }
    
}
