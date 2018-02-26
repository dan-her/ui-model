package d.company.tts;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextToSpeech mouth = new TextToSpeech(this, this);
        final String spoken = "thirty-seven";
        mouth.setLanguage(Locale.US);

        Button speaker = findViewById(R.id.speakbutton);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mouth.speak(spoken, TextToSpeech.QUEUE_ADD, null);
            }
        });
    }

    @Override
    public void onInit(int i) {

    }
}
