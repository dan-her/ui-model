package lakelandtrashigator.loopapplab;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defines UI elements in the code, media players aren't UI but they make sounds play.
        final Button submit = findViewById(R.id.button2);
        final EditText in = findViewById(R.id.interestingTxt);
        final MediaPlayer songplay = MediaPlayer.create(this, R.raw.rustycage);
        final ImageView cash = findViewById(R.id.imageView);
        final MediaPlayer ween = MediaPlayer.create(this, R.raw.oceanman);
        cash.setVisibility(View.INVISIBLE);
        //code starts here
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (in.getText() != null){
                    if (in.getText().toString().equals("close")){
                        //closes the app
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                    if (in.getText().toString().equals("Johnny Cash")){
                        //it's a secret
                        cash.setVisibility(View.VISIBLE);
                        songplay.start();

                    }
                    if (in.getText().toString().equals("Ocean Man")){
                        //plays Ocean Man, of course
                        ween.start();
                    }

                }
            }
        });
    }
}
