package com.example.android.diego_baking_app.Objects;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.diego_baking_app.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExoPlayerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExoPlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExoPlayerFragment extends Fragment {
    FragmentActivity listener;
    PlayerView exoPlayerView;


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

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_exo_player, container, false);
        exoPlayerView = rootView.findViewById(R.id.exoplayer_view);
        if (getArguments() != null) {
            String videoLink = getArguments().getString("VIDEO_LINK");
            playVideo(videoLink,listener);
        }
        return rootView;
    }
    public void playVideo(String videoLink, Context context){
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(context,trackSelector);
        exoPlayerView.setPlayer(player);

        //Handling the media source
        String user = Util.getUserAgent(context,"diego_Baking_App");
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,user);
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(videoLink));
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        exoPlayerView.getPlayer().release();
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
