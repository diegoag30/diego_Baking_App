package com.example.android.diego_baking_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.diego_baking_app.Objects.Ingredients;
import com.example.android.diego_baking_app.Objects.Recipe;
import com.example.android.diego_baking_app.Objects.Steps;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_INGREDIENT = 0;
    private static final int TYPE_STEP = 1;

    ArrayList<Ingredients> ingredients;
    ArrayList<Steps>steps;

    public DetailAdapter(ArrayList<Ingredients> ingredients,
                         ArrayList<Steps>steps){
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof VHIngredients ){
            VHIngredients VHingredients = (VHIngredients)viewHolder;
            VHingredients.ingredients_tv.setText(ingredientsList());
        }
        else if (viewHolder instanceof VHSteps){
            Steps currentStep = getStep(i-1);
            VHSteps VHsteps = (VHSteps)viewHolder;
            VHsteps.steps_tv.setText(currentStep.getDescription());
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
