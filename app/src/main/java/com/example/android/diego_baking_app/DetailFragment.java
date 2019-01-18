package com.example.android.diego_baking_app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.diego_baking_app.Objects.Ingredients;
import com.example.android.diego_baking_app.Objects.Recipe;
import com.example.android.diego_baking_app.Objects.Steps;
import com.google.gson.Gson;

import java.util.ArrayList;


public class DetailFragment extends Fragment {

    public FragmentActivity mListener;
    public RecyclerView detail_rv;
    private LinearLayoutManager linearLM;
    private ArrayList<Steps> steps;
    private ArrayList<Ingredients> ingredients;
    private Recipe recipe;
    public static final String CURRENT_RECIPE = "CURRENT_RECIPE";

    public DetailFragment() {

    }

    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        detail_rv = rootView.findViewById(R.id.fragment_detail_rv);
        linearLM = new LinearLayoutManager(mListener);
        getStepsAndIngredients();
        setDetailUi(recipe);
        return rootView;
    }
    public void setDetailUi(Recipe passedRecipe){
        DetailAdapter dAdapter = new DetailAdapter(mListener,passedRecipe.getIngredients(),
                passedRecipe.getSteps());
        detail_rv.setLayoutManager(linearLM);
        detail_rv.setAdapter(dAdapter);
    }

    public void getStepsAndIngredients(){
        SharedPreferences sPreferences = PreferenceManager.
                getDefaultSharedPreferences(mListener.getApplicationContext());
        Gson gson = new Gson();
        String json = sPreferences.getString("RECIPE","");
        recipe = gson.fromJson(json,Recipe.class);
/*        assert getArguments() != null;
        recipe = getArguments().getParcelable(CURRENT_RECIPE);*/
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.mListener = (FragmentActivity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
