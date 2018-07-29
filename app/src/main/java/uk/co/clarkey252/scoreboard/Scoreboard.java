package uk.co.clarkey252.scoreboard;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.WindowManager.*;

public class Scoreboard extends Activity{

    int numberOfPlayers = 2;
    int[] playerColors = new int[2];
    LinearLayout wholePage;
    TextView[] playerScores;
    String initialScore = "0";
    int increment = 1;
    int screenWidth;
    int screenHeight;

    float BUTTON_FONT_SIZE = 32f;
    float SCORE_FONT_SIZE = 72f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scoreboardlayout);

        getDisplayMetrics();

        getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);

        wholePage = (LinearLayout) findViewById(R.id.scoreborad_whole_page);
        playerScores = new TextView[numberOfPlayers];
        setPlayerColors();
        for (int i = 0; i< numberOfPlayers; i++) {
            RelativeLayout r = new RelativeLayout(getApplicationContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(screenWidth, screenHeight / numberOfPlayers);
            r.setLayoutParams(lp);
            r.setGravity(Gravity.CENTER);
            r.setTag("Player" + i + "_layout");
            r.setBackgroundColor(playerColors[i]);
            wholePage.addView(r);
            addPlayerLayout(r,i);
            if (i!=(numberOfPlayers-1)){
                View v = new View(getApplicationContext());
                v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,10));
                v.setBackgroundColor(Color.BLACK);
                wholePage.addView(v);
            }
        }

        ImageButton settingsButton = (ImageButton) findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(settingsClick);

    }

    private void setPlayerColors() {
        for (int i=0;i< numberOfPlayers;i++){
            //TODO update this when colours sorted
        }
        //playerColors[0]= Color.argb(0xFF,0xFF,0xFF,0xC0);
        //playerColors[1]= Color.argb(0xFF,0xFF,0xFF,0x40);
        playerColors[0]= Color.argb(0xFF,0xF0,0x34,0x34);
        playerColors[1]= Color.argb(0xFF,0x34,0x34,0xf0);

    }

    private void addPlayerLayout(RelativeLayout layout, int player) {
        LinearLayout ll = new LinearLayout(getApplicationContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(lp);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        Button negative = new Button(getApplicationContext());
        formatScoreButton(negative);
        negative.setText("-");
        negative.setTag("p"+player+"_negative");
        negative.setOnClickListener(nOCL);

        Button positive = new Button(getApplicationContext());
        formatScoreButton(positive);
        positive.setText("+");
        positive.setTag("p"+player+"_positive");
        positive.setOnClickListener(pOCL);

        TextView score = new TextView(getApplicationContext());
        score.setText(initialScore);
        score.setTextColor(Color.BLACK);
        score.setTextSize(SCORE_FONT_SIZE);
        score.setTag("p"+player+"_score");
        score.setPadding(32,32,32,32);
        playerScores[player] = score;

        ll.addView(negative);
        ll.addView(score);
        ll.addView(positive);
        layout.addView(ll);

    }

    private void formatScoreButton(Button b) {
        b.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        b.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        b.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        b.setPadding(0,0,0,0);
        b.setGravity(Gravity.CENTER);
        b.setTextSize(BUTTON_FONT_SIZE);
    }

    public void getDisplayMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    View.OnClickListener pOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = v.getTag().toString();
            int playerNo = Integer.valueOf(tag.substring(tag.indexOf("p")+1,tag.indexOf("_")));
            int playerScore = Integer.valueOf(playerScores[playerNo].getText().toString());
            playerScore += increment;
            playerScores[playerNo].setText(String.valueOf(playerScore));
        }
    };

    View.OnClickListener nOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = v.getTag().toString();
            int playerNo = Integer.valueOf(tag.substring(tag.indexOf("p")+1,tag.indexOf("_")));
            int playerScore = Integer.valueOf(playerScores[playerNo].getText().toString());
            playerScore -= increment;
            playerScores[playerNo].setText(String.valueOf(playerScore));
        }
    };

    View.OnClickListener settingsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PopupMenu p = new PopupMenu(getApplicationContext(),v,Gravity.CENTER);
            p.getMenuInflater().inflate(R.menu.settings,p.getMenu());
            p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(getApplicationContext(),"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            p.show();
        }
    };
}
