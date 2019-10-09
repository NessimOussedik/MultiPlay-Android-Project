package com.example.hp.finale;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class FragmentYoutubeVideo extends YouTubePlayerSupportFragment {

    public static String currentVideoID = "video_id";
    private YouTubePlayer activePlayer;
    public static Bundle bundle = new Bundle();




    public static FragmentYoutubeVideo newInstance(String url) {
        FragmentYoutubeVideo playerYouTubeFrag = new FragmentYoutubeVideo();



        Bundle args = new Bundle();
        args.putString("url", url);

        playerYouTubeFrag.setArguments(args);
        playerYouTubeFrag.init();

        return playerYouTubeFrag;
    }

    private void init() {
        //Le premier paramètre est la clé développeur obligatoire pour pouvoir utiliser l'API YouTube
        initialize("AIzaSyDgQlm8l3-MuteNnUP_g3iBsKvRD3ys26k", new OnInitializedListener() {

            @Override
            public void onInitializationFailure(Provider arg0, YouTubeInitializationResult arg1) {
            }

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                activePlayer = player;
                activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                if (!wasRestored) {
                    activePlayer.loadVideo(getArguments().getString("url"), 0);

                }
            }
        });
    Log.d("initVideo", "Vidéo initialisée avec succès");
    }


    public void onYouTubeVideoPaused() {
        activePlayer.pause();
    }


}