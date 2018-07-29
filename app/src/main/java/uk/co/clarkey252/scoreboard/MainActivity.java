package uk.co.clarkey252.scoreboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Button newButton = (Button) findViewById(R.id.new_scoreboard_button);
        newButton.setOnClickListener(newOCL);
    }

    View.OnClickListener newOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent i = new Intent(MainActivity.this,Scoreboard.class);
            startActivity(i);
        }
    };
}
