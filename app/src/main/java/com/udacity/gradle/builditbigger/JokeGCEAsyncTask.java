package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.jokegen.backend.jokeApi.JokeApi;

import java.io.IOException;


/**
 * Created by jim on 3/19/17.
 */

public class JokeGCEAsyncTask extends AsyncTask<MainActivityFragment, Void, String> {
    private static JokeApi jokeApiService = null;
    private Context mContext;

    private String emulatorRootUrl = "http://10.0.2.2:8080/_ah/api/"; // 10.0.2.2 is localhost's IP address in Android emulator
    private String liveRootUrl = "https://jokegeneratorapp.appspot.com/_ah/api/";
    private MainActivityFragment mainActivityFragment;

    @Override
    protected String doInBackground(MainActivityFragment... params) {
        mainActivityFragment = params[0];
        mContext = mainActivityFragment.getActivity();


        if (jokeApiService == null) {
            JokeApi.Builder jokeBuilder = new JokeApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null)
                    .setRootUrl(liveRootUrl)
                    .setApplicationName("JokeGeneratorAPI")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            jokeApiService = jokeBuilder.build();
        }

        try {
            return jokeApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            Log.e("API ERROR", e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {

        mainActivityFragment.loadedJoke = result;
        mainActivityFragment.launchDisplayJokeActivity();

//        // Create Intent to launch JokeFactory Activity
//        Intent intent = new Intent(mContext, JokeTellerActivity.class);
//        // Put the string in the envelope
//        intent.putExtra(JokeTellerActivity.JOKING, result);
//        mContext.startActivity(intent);
    }
}
