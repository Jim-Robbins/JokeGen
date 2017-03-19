package com.udacity.gradle.jokegen;

import java.util.Random;

public class JokeGenerator {
    private static final String[] jokes = {
            "Where do sheep go to get their hair cut?|To the baa-baa shop",
            "What is a pirate’s favorite letter?|Aaarrr!",
            "Why are leopards so bad at playing hide and seek?|Because they’re always spotted!",
            "Why did the boy take a ladder to school?|Because he though it was a HIGH school!",
            "What do you get when you cross a dog and a rose?|Collie Flower",
            "Why did the banana go to the doctor?|Because he wasn’t peeling very well!",
            "What type of jam can’t be eaten?|A traffic jam!",
            "Why can’t you trust atoms?|They make up everything!",
            "What has two hands but no arms?|A clock!",
            "Why did the scarecrow win an award?|Because he was the best in his field!"
    };

    public static String tellJoke() {
        int randomJokeIndex = new Random().nextInt(jokes.length);
        return jokes[randomJokeIndex];
    }
}
