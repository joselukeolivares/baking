package com.example.baking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

//import com.google.android.exoplayer2.ui.PlayerView;

public class videoFragment extends Fragment {
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow;
    private static long playbackPosition;

    @Override
    public void onStart() {
        super.onStart();

        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }


    }

    @Override
    public void onResume() {
        super.onResume();

        //playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);



        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
           initializePlayer();

        }




    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (Util.SDK_INT < 24) {
            releasePlayer();
        }


    }

    @Override
    public void onStop() {
        super.onStop();

        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }


    }
    public videoFragment(){

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


            outState.putInt("currentWindow",currentWindow);

            outState.putLong("playbackPosition",playbackPosition);


        Log.i("currentWindow",currentWindow+"");
        Log.i("playbackPosition",playbackPosition+"");
    }

    Context context;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.video_layout,container,false);

        if (savedInstanceState != null) {

               // outState.putInt("currentWindow",currentWindow);
               // outState.putLong("currentWindow",playbackPosition);

            currentWindow=savedInstanceState.getInt("currentWindow");
            playbackPosition=savedInstanceState.getLong("playbackPosition");
            Log.i("Exoplayer","SavedInstance!=null:"+currentWindow+":"+playbackPosition);

        }else {
            playbackPosition=0;
        }


        playerView=rootView.findViewById(R.id.tv_video);
        return rootView;




    }
    private String recipeURL;
    public void setURL(String recipeURL){
        this.recipeURL=recipeURL;
        Log.i("before",""+playbackPosition);
        playbackPosition=0;
        Log.i("after",""+playbackPosition);

    }

    public void resetVideoPlayback(){
        playbackPosition=0;
    }

    public void initializePlayer() {

        if (player == null) {
            DefaultTrackSelector trackSelector = new DefaultTrackSelector();
            trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd());
            player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

        }


        //player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);

        //Uri uri = Uri.parse(getString(R.string.media_url_mp4));
        if(recipeURL!=null && !recipeURL.equals("") ){
            Uri uri = Uri.parse(recipeURL);
            MediaSource mediaSource = buildMediaSource(uri);

            player.setPlayWhenReady(playWhenReady);
            Log.i("initializePlayer",playbackPosition+"");
            player.seekTo(currentWindow, playbackPosition);
            player.prepare(mediaSource, false, false);
        }



    }


    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }


    /*
      private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "exoplayer-codelab");
        DashMediaSource.Factory mediaSourceFactory = new DashMediaSource.Factory(dataSourceFactory);
        return mediaSourceFactory.createMediaSource(uri);
      }
    */
    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    public void  stop_and_release(){
        if(player!=null){
            releasePlayer();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FrameLayout frameLayout=rootView.findViewById(R.id.step_description_frameLayout);
        ConstraintLayout constraintLayout=rootView.findViewById(R.id.buttons_container);
        // Checking the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {


            //First Hide other objects (listview or recyclerview), better hide them using Gone.
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) playerView.getLayoutParams();

            frameLayout.setVisibility(View.INVISIBLE);
            constraintLayout.setVisibility(View.INVISIBLE);
            params.width=params.MATCH_PARENT;
            params.height=params.MATCH_PARENT;
            playerView.setLayoutParams(params);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //unhide your objects here.
            frameLayout.setVisibility(View.VISIBLE);
            constraintLayout.setVisibility(View.VISIBLE);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) playerView.getLayoutParams();
            params.width=params.MATCH_PARENT;
            params.height=300;
            playerView.setLayoutParams(params);
        }
    }


}
