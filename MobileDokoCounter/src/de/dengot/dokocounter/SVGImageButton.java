package de.dengot.dokocounter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class SVGImageButton extends ImageButton {

    private int svgResourceId;
    
    public SVGImageButton(Context context, int svgResourceId) {
        super(context);
        this.svgResourceId = svgResourceId;
        drawSVG();
    }

    public SVGImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        drawSVG();
    }

    public SVGImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
        drawSVG();
    }

    private void init(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SVGImageButton);
        Log.i("SVGImageButton_android_src", a.getString(R.styleable.SVGImageButton_android_src));

        // Getting a file name
        CharSequence cs = a.getText(R.styleable.SVGImageButton_android_src);
        String file = cs.toString();
        
        this.svgResourceId = a.getResourceId(R.styleable.SVGImageButton_android_src, -1);

        //Don't forget this
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void setScaleType (ImageView.ScaleType scaleType) {
        super.setScaleType(scaleType);
        drawSVG();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        drawSVG();
    }

    private void drawSVG(){
        if (svgResourceId <= 0){
            return;
        }

        int vWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int vHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        Drawable drawable = super.getDrawable();
        if (drawable != null && drawable.getBounds().width() == vWidth && drawable.getBounds().height() == vHeight){
            return;
        }

        Log.d("SVGImageButton", "drawing svg(w=" + vWidth + ", h=" + vHeight + ")");

        // Let SVG scale itself!
        super.setImageDrawable(null);

        SVG svg = SVGParser.getScaledSVGFromResource(getResources(), svgResourceId, vWidth, vHeight);
        super.setImageDrawable(svg.createPictureDrawable());
    }
}
