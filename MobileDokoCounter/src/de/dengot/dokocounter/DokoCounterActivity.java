package de.dengot.dokocounter;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.text.MessageFormat;

public class DokoCounterActivity extends Activity {
    private int points;
    private TextView pointView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;
        switch (orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                createLandscapeMode();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                createPortraitMode();
                break;
            default:
                createPortraitMode();
        }


        this.points = 0;
        this.pointView = (TextView) findViewById(R.id.textview_points);

        initBmpCardButton(R.id.button_king, R.drawable.king, 4);
        initBmpCardButton(R.id.button_queen, R.drawable.queen, 3);
        initBmpCardButton(R.id.button_jack, R.drawable.jack, 2);
        initBmpCardButton(R.id.button_ace, R.drawable.ace, 11);
        initBmpCardButton(R.id.button_num10, R.drawable.ten, 10);

//        initSvgCardButton(R.id.button_king, R.raw.card_club_king, 4);
//        initSvgCardButton(R.id.button_queen, R.raw.card_club_queen, 3);
//        initSvgCardButton(R.id.button_jack, R.raw.card_club_jack, 2);
//        initSvgCardButton(R.id.button_ace, R.raw.card_club_ace, 11);
//        initSvgCardButton(R.id.button_num10, R.raw.card_club_10, 10);

        Button resetButton = (Button) findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPoints();
            }
        });
    }

    private void createPortraitMode() {
        Log.v("Orientation", "Portrait");
        setContentView(R.layout.main_portrait);
    }

    private void createLandscapeMode() {
        Log.v("Orientation", "Landscape");
        setContentView(R.layout.main_landscape);
    }

    private void resetPoints() {
        this.points = 0;
        updatePointView();
    }

    private void addPoints(int value) {
        this.points += value;
        //overflow control
        if (this.points > 999 || this.points < 0){
            this.points = 0;
        }
        updatePointView();
    }

    private void updatePointView() {
        //setText(int) is reserved for string resource ids
        pointView.setText(MessageFormat.format("Punkte: {0,number,000}", this.points));
    }

    private void initSvgCardButton(int buttonId, int imageId, int cardValue) {
        ImageView cardButton = (ImageView) findViewById(buttonId);
        cardButton.setOnClickListener(new PointAddClickListener(cardValue));
        SVG svg = SVGParser.getSVGFromResource(getResources(), imageId);
        cardButton.setImageDrawable(svg.createPictureDrawable());
    }

    private void initBmpCardButton(int buttonId, int imageId, int cardValue) {
        ImageView cardButton = (ImageView) findViewById(buttonId);
        cardButton.setOnClickListener(new PointAddClickListener(cardValue));
        cardButton.setImageDrawable(getResources().getDrawable(imageId));
    }

    /*
    * Walks through the objects tree
    * When meets ImageView - sets its width and height
    */
    private void layoutImageViews(ViewGroup view, int width, int height) {
        int i;
        for (i = 0; i < view.getChildCount(); ++i) {
            View child = view.getChildAt(i);
            if (child instanceof ViewGroup) {
                layoutImageViews((ViewGroup) child, width, height);
            } else if (child instanceof ImageView) {
                ImageView img = (ImageView) child;
                img.setLayoutParams(new TableRow.LayoutParams(width, height));
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            TableLayout table = (TableLayout) findViewById(R.id.table_main);
            if (table == null) {
                return;
            }


            //TODO adjust num cols/rows per orientation
            layoutImageViews(table, table.getWidth() / 3, table.getHeight() / 2);
        }
    }

    private class PointAddClickListener implements View.OnClickListener {
        private int points;

        public PointAddClickListener(int points) {
            this.points = points;
        }

        @Override
        public void onClick(View v) {
            addPoints(this.points);
        }
    }
}
