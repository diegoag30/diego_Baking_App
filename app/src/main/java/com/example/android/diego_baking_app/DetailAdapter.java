package com.example.android.diego_baking_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.diego_baking_app.Objects.Ingredients;
import com.example.android.diego_baking_app.Objects.Steps;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_INGREDIENT = 0;
    private static final int TYPE_STEP = 1;
    public static final String STEP_INSTRUCTION = "STEP_INSTRUCTION";
    public static final String VIDEO_LINK = "VIDEO_LINK";
    private Context context;

    ArrayList<Ingredients> ingredients;
    ArrayList<Steps>steps;

    public DetailAdapter(Context context,ArrayList<Ingredients> ingredients,
                         ArrayList<Steps>steps){
        this.context = context;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == TYPE_INGREDIENT){
            View view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.detail_ingredients,viewGroup,false);
            return new VHIngredients(view);

        }else if(i == TYPE_STEP){
            View view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.detail_steps,viewGroup,false);
            return new VHSteps(view);
        }
        throw new RuntimeException("There is no match type for: "+ i +".");
    }

    private Steps getStep(int i){
        return steps.get(i);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof VHIngredients ){
            final VHIngredients VHingredients = (VHIngredients)viewHolder;
            VHingredients.ingredients_tv.setText(ingredientsList());
            VHingredients.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(view.getContext(),"You clicked Ingredients",Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
        else if (viewHolder instanceof VHSteps){
            final Steps currentStep = getStep(i-1);
            VHSteps VHsteps = (VHSteps)viewHolder;
            VHsteps.steps_tv.setText(currentStep.getShortDescription());
            VHsteps.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(view.getContext(),"You clicked Steps",Toast.LENGTH_SHORT);
                    toast.show();
                    Bundle bundle = new Bundle();
                    bundle.putString(STEP_INSTRUCTION,currentStep.getDescription());
                    bundle.putString(VIDEO_LINK,currentStep.getVideoUrl());
                    Intent intent = new Intent(view.getContext(),StepDetail.class);
                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);

/*                    StepFragment stepFragment = new StepFragment();
                    FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                    manager.beginTransaction().
                            replace(R.id.frame_container,stepFragment)
                            .commit();*/
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(isPositionIngredients(position))
            return TYPE_INGREDIENT;
        return TYPE_STEP;
    }


    private boolean isPositionIngredients(int position)
    {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return steps.size()+1;
    }

    class VHIngredients extends RecyclerView.ViewHolder{
        TextView ingredients_tv;

        public VHIngredients(@NonNull View itemView) {
            super(itemView);
            this.ingredients_tv = (TextView)itemView.findViewById(R.id.ingredients_tv);
        }
    }

    class VHSteps extends RecyclerView.ViewHolder{
        TextView steps_tv;

        public VHSteps(@NonNull View itemView) {
            super(itemView);
            this.steps_tv = (TextView)itemView.findViewById(R.id.steps_tv);
        }
    }

    public String ingredientsList(){
        String ingredientsLS = "The ingredients you need for this Recipe is: \n";
        for (int i=0;i<ingredients.size();i++){
            String quantity = String.valueOf(ingredients.get(i).getQuantity());
            String measure = ingredients.get(i).getMeasure();
            String ingredient = ingredients.get(i).getIngredient();
            ingredientsLS = ingredientsLS + "- "+ quantity + " " + measure + " of " + ingredient +" ." + "\n";
        }
        return ingredientsLS;
    }
}
