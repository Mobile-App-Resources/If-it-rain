package root.if_it_rains.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import root.if_it_rains.Manager.TTSManager;
import root.if_it_rains.Model.PoemModel;
import root.if_it_rains.R;
import root.if_it_rains.Util.BaseActivity;

/**
 * Created by root1 on 2017. 9. 1..
 */

public class PoemActivity extends BaseActivity {

    TTSManager ttsManager;
    ImageButton speakButton;
    TextView titleText, contentText, writerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem);
        Intent intent = getIntent();
        final PoemModel poemModel = (PoemModel) intent.getSerializableExtra("data");

        titleText = (TextView)findViewById(R.id.titleText);
        writerText = (TextView)findViewById(R.id.writerText);
        contentText = (TextView)findViewById(R.id.contentText);
        speakButton = (ImageButton)findViewById(R.id.speakButton);

        titleText.setText(poemModel.getTitle());
        contentText.setText(poemModel.getContent());
        writerText.setText(poemModel.getWriter());

        contentText.setMovementMethod(new ScrollingMovementMethod());

        ttsManager = new TTSManager(this);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsManager.readTTS(poemModel.getContent());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ttsManager.shutDownTTS();
    }
}
