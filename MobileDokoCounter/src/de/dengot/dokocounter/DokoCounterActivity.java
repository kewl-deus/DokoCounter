package de.dengot.dokocounter;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DokoCounterActivity extends Activity
{
    private int points;
    private TextView pointView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;
        switch (orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                Log.v("Orientation", "Landscape");
                setContentView(R.layout.main_landscape);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                Log.v("Orientation", "Portrait");
                setContentView(R.layout.main_portrait);
                break;
            default:
                setContentView(R.layout.main_portrait);
        }



        this.points = 0;
        this.pointView = (TextView) findViewById(R.id.textview_punkte);

        registerPointAddListener(R.id.button_koenig, 4);
        registerPointAddListener(R.id.button_dame, 3);
        registerPointAddListener(R.id.button_bube, 2);
        registerPointAddListener(R.id.button_ass, 11);
        registerPointAddListener(R.id.button_karte10, 10);

        Button resetButton = (Button) findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetPoints();
            }
        });
    }

    private void resetPoints(){
        this.points = 0;
        updatePointView();
    }

    private void addPoints(int value){
        this.points += value;
        updatePointView();
    }

    private void updatePointView() {
        //setText(int) is reserved for string resource ids
        pointView.setText(Integer.toString(this.points));
    }
    
    private void registerPointAddListener(final int buttonId, final int cardValue){
        Button cardButton = (Button) findViewById(buttonId);
        cardButton.setOnClickListener(new PointAddClickListener(cardValue));
    }


    
    private class PointAddClickListener implements View.OnClickListener {
        private int points;
        
        public PointAddClickListener(int points){
            this.points = points;
        }
        
        @Override
        public void onClick(View v) {
            addPoints(this.points);
        }
    }
}
