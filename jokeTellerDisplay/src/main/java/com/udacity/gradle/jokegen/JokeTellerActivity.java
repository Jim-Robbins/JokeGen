package com.udacity.gradle.jokegen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class JokeTellerActivity extends AppCompatActivity {
    public static final String JOKING = "JokeGen.Joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_teller);

        Intent jokeIntent = getIntent();
        String jokeExtra = jokeIntent.getStringExtra(JOKING);

        TextView jokeText = (TextView) findViewById(R.id.joke_text);


        if (TextUtils.isEmpty(jokeExtra)) {
            jokeText.setText(R.string.no_joke_for_you);
        } else {
            String[] joke = jokeExtra.split("\\|");
            jokeText.setText(joke[0]);
            animatePunchline(joke[1]);
        }
    }

    private void animatePunchline(String punchline) {
        TextView punchlineText = (TextView) findViewById(R.id.punchline_text);

        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setDuration(1000);
        animation1.setStartOffset(2000);
        punchlineText.setAlpha(1f);
        punchlineText.setText(punchline);
        punchlineText.startAnimation(animation1);
    }
}
