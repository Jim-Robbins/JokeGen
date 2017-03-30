package com.udacity.gradle.jokegen;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.JokeGCEAsyncTask;
import com.udacity.gradle.builditbigger.MainActivityFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by jim on 3/28/17.
 */

@RunWith(AndroidJUnit4.class)
public class JokeGCEAsyncTaskTest {
    @Test
    public void testDoInBackground() throws Exception {
        MainActivityFragment fragment = new MainActivityFragment();
        try {
            JokeGCEAsyncTask jokeGCEAsyncTask = new JokeGCEAsyncTask();
            jokeGCEAsyncTask.execute(fragment);
            String result = jokeGCEAsyncTask.get(30, TimeUnit.SECONDS);

            assertNotNull(result);
            assertTrue(result.length() > 0);
        } catch (Exception e){
            Log.e("JokeGCEAsyncTaskTest", "testDoInBackground: Timed out");
        }
    }
}
