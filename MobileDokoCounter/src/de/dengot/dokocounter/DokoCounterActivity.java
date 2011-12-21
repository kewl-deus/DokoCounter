package de.dengot.dokocounter;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

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

//        initCardButton(R.id.button_king, R.raw.card_club_king, 4);
//        initCardButton(R.id.button_queen, R.raw.card_club_queen, 3);
//        initCardButton(R.id.button_jack, R.raw.card_club_jack, 2);
//        initCardButton(R.id.button_ace, R.raw.card_club_ace, 11);
//        initCardButton(R.id.button_num10, R.raw.card_club_10, 10);

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
        //GridView gridview = (GridView) findViewById(R.id.landscape_grid);
        //gridview.setAdapter(new CardImageAdapter(this));
    }

    private void resetPoints() {
        this.points = 0;
        updatePointView();
    }

    private void addPoints(int value) {
        this.points += value;
        updatePointView();
    }

    private void updatePointView() {
        //setText(int) is reserved for string resource ids
        pointView.setText(Integer.toString(this.points));
    }

    private void initCardButton(int buttonId, int imageId, int cardValue) {
        ImageButton cardButton = (ImageButton) findViewById(buttonId);
        cardButton.setOnClickListener(new PointAddClickListener(cardValue));

        cardButton.setBackgroundColor(Color.WHITE);

        //TODO fix after debug
        imageId = R.raw.card_club_ace;

        SVG svg = SVGParser.getSVGFromResource(getResources(), imageId);


        cardButton.setImageDrawable(svg.createPictureDrawable());
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
