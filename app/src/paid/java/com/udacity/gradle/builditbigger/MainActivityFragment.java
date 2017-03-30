package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.jokegen.JokeTellerActivity;

import static com.udacity.gradle.jokegen.JokeTellerActivity.JOKING;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ProgressBar progressBar = null;
    public String loadedJoke = null;
    public boolean testFlag = false;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Set onClickListener for the button
        Button button = (Button) root.getRootView().findViewById(R.id.tell_joke_btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
            }
        });

        progressBar = (ProgressBar) root.getRootView().findViewById(R.id.api_loader);
        progressBar.setVisibility(View.GONE);

        return root;
    }

    public void getJoke(){
        new JokeGCEAsyncTask().execute(this);
    }

    public void launchDisplayJokeActivity() {
        if (!testFlag) {
            Context context = getActivity();
            Intent intent = new Intent(context, JokeTellerActivity.class);
            intent.putExtra(JOKING, loadedJoke);

            context.startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
    }
}
