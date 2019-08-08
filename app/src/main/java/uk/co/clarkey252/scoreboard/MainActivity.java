package uk.co.clarkey252.scoreboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        findViewById(R.id.new_scoreboard_button).setOnClickListener(this);
        findViewById(R.id.resume_scoreboard).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Log.v("DEBUG", String.valueOf(v.getId()));
        Log.v("DEBUG", String.valueOf(R.id.new_scoreboard_button));

        Intent i;
        switch (v.getId()) {
            case R.id.resume_scoreboard:
                i = new Intent(MainActivity.this, Scoreboard.class);
                i.putExtra("resume", true);
                startActivity(i);
                break;
            case R.id.new_scoreboard_button:
                i = new Intent(MainActivity.this, Scoreboard.class);
                startActivity(i);
                i.putExtra("resume", false);
                break;
        }
    }
}
