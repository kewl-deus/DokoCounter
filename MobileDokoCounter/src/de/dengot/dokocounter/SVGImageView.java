package de.dengot.dokocounter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class SVGImageView extends ImageView {

    private int svgResourceId;
    
    public SVGImageView(Context context, int svgResourceId) {
        super(context);
        this.svgResourceId = svgResourceId;
        drawSVG();
    }

    public SVGImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        drawSVG();
    }

    public SVGImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
        drawSVG();
    }

    private void init(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SVGImageView);
        Log.v("SVGImageView_android_src", a.getString(R.styleable.SVGImageView_android_src));

        // Getting a file name
        CharSequence cs = a.getText(R.styleable.SVGImageView_android_src);
        String file = cs.toString();
        
        this.svgResourceId = a.getResourceId(R.styleable.SVGImageView_android_src, -1);

        //Don't forget this
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v("SVGImageView", "onDraw");
        super.onDraw(canvas);
    }

    @Override
    public void setScaleType (ImageView.ScaleType scaleType) {
        Log.v("SVGImageView", "setScaleType");
        super.setScaleType(scaleType);
        drawSVG();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.v("SVGImageView", "onSizeChanged");
        drawSVG();
    }

    private void drawSVG(){
        if (svgResourceId <= 0){
            return;
        }

        int vWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int vHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        if (vWidth <= 0 || vHeight <= 0){
            return;
        }
        
        Drawable drawable = super.getDrawable();
        if (drawable != null && drawable.getBounds().width() == vWidth && drawable.getBounds().height() == vHeight){
            return;
        }

        Log.v("SVGImageView", "drawing svg(w=" + vWidth + ", h=" + vHeight + ")");

        // Let SVG scale itself!
        super.setImageDrawable(null);

        SVG svg = SVGParser.getSVGFromResource(getResources(), svgResourceId);

        super.setImageDrawable(svg.createPictureDrawable());
    }
}
