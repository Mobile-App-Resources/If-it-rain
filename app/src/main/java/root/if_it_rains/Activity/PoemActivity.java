package root.if_it_rains.Activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import root.if_it_rains.Manager.TTSManager;
import root.if_it_rains.R;
import root.if_it_rains.Util.BaseActivity;

/**
 * Created by root1 on 2017. 9. 1..
 */

public class PoemActivity extends BaseActivity {

    TTSManager ttsManager;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem);
        button = (Button)findViewById(R.id.button);
        ttsManager = new TTSManager(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsManager.tts.speak("내를 건너서 숲으로\n" +
                        "고개를 넘어서 마을로\n" +
                        "\n" +
                        "어제도 가고 오늘도 갈\n" +
                        "나의 새로운 길\n" +
                        "민들레가 피고 까치가 날고\n" +
                        "아저씨가 지나고 바람이 일고\n" +
                        "\n" +
                        "나의 길은 언제나 새로운 길\n" +
                        "오늘도.....내일도.....\n" +
                        "내를 건너서 숲으로\n" +
                        "고개를 넘어서 마을로", TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ttsManager.shutDownTTS();
    }
}
