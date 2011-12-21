package de.dengot.dokocounter;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CardImageAdapter extends BaseAdapter {

    private int[] cardImages = new int[]{
            R.raw.card_club_king,
            R.raw.card_club_queen,
            R.raw.card_club_jack,
            R.raw.card_club_ace,
            R.raw.card_club_10
    };

    private int[] buttonIds = new int[]{
            R.id.button_king,
            R.id.button_queen,
            R.id.button_jack,
            R.id.button_ace,
            R.id.button_num10
    };

    private Context context;

    public CardImageAdapter(Context c) {
        this.context = c;
    }

    
    @Override
    public int getCount() {
        return cardImages.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton imageButton;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageButton = new ImageButton(this.context);
            imageButton.setId(buttonIds[position]);
            //imageButton.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageButton.setAdjustViewBounds(true);
            imageButton.setBackgroundColor(Color.BLACK);
            imageButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            //imageButton.setPadding(8, 8, 8, 8);
        } else {
            imageButton = (ImageButton) convertView;
        }

        imageButton.setImageResource(cardImages[position]);
        return imageButton;

    }
}
