package com.example.android.diego_baking_app.Objects;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.diego_baking_app.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class ExoPlayerFragment extends Fragment {
    FragmentActivity listener;
    PlayerView exoPlayerView;
    SimpleExoPlayer player;
    long exoPlayerPos;
    boolean isReady;
    public final String PLAYER_POSITION ="PLAYER_POSITION";
    public final String IS_READY ="IS_READY";
    public String videoLink;
    private TextView defaultTv;



    public ExoPlayerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ExoPlayerFragment newInstance(String param1, String param2) {
        ExoPlayerFragment fragment = new ExoPlayerFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState!= null){
            exoPlayerPos = savedInstanceState.getLong(PLAYER_POSITION);
            isReady = savedInstanceState.getBoolean(IS_READY);
        }
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_exo_player, container, false);
        exoPlayerView = rootView.findViewById(R.id.exoplayer_view);
        if (getArguments() != null) {
            videoLink = getArguments().getString("VIDEO_LINK");
            if(videoLink != null && videoLink.isEmpty()){
                defaultTv = rootView.findViewById(R.id.default_tv);
                exoPlayerView.setVisibility(View.GONE);
                defaultTv.setVisibility(View.VISIBLE);
            }else {
                playVideo(videoLink);
            }
        }
        return rootView;
    }
    public void playVideo(String videoLink){
        TrackSelector trackSelector = new DefaultTrackSelector();
        player = ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector);
        exoPlayerView.setPlayer(player);

        //Handling the media source
        String user = Util.getUserAgent(getActivity(),"diego_Baking_App");
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),user);
        Uri videoUri = Uri.parse(videoLink);
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        player.release();
    }


    @Override
    public void onPause() {
        super.onPause();
        if (player!= null){
            exoPlayerPos = player.getCurrentPosition();
            isReady = player.getPlayWhenReady();
            player.stop();
            player.release();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player!= null){
            player.setPlayWhenReady(isReady);
            player.seekTo(exoPlayerPos);
        }else {
            playVideo(videoLink);
            player.setPlayWhenReady(isReady);
            player.seekTo(exoPlayerPos);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PLAYER_POSITION, exoPlayerPos);
        outState.putBoolean(IS_READY,isReady);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
/*    public interface ExoFragmentInterface {
        long getPlayerPosition(Player mPlayer);
    }*/
}
