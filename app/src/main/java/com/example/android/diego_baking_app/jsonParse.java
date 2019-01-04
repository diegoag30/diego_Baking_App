package com.example.android.diego_baking_app;

import com.example.android.diego_baking_app.Objects.Ingredients;
import com.example.android.diego_baking_app.Objects.Recipe;
import com.example.android.diego_baking_app.Objects.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class jsonParse {

    public static ArrayList<Recipe>RecipeParser(String jsonString){
        try {
            JSONArray initArray = new JSONArray(jsonString);
            ArrayList<Recipe>recipesList = new ArrayList<Recipe>();
            //Loop trough all the recipes
            for (int i =0; i<initArray.length(); i++){
                //Creating the Arraylist for Ingredients and Steps
                ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();
                ArrayList<Steps> steps = new ArrayList<Steps>();


                JSONObject rawData = initArray.getJSONObject(i);
                int recipeId = rawData.getInt("id");
                String recipeName = rawData.getString("name");
                String recipeImage = rawData.getString("image");
                JSONArray ingredientsArray = rawData.getJSONArray("ingredients");
                JSONArray stepsArray = rawData.getJSONArray("steps");

                //Loop trough the ingredients of each recipe
                for(int ii = 0; ii < ingredientsArray.length(); ii++ ){
                    JSONObject currentIngredient = ingredientsArray.getJSONObject(ii);

                    int recipeQuantity = currentIngredient.getInt("quantity");
                    String recipeMeasure = currentIngredient.getString("measure");
                    String ingredientName = currentIngredient.getString("ingredient");
                    Ingredients ingredientsObject = new Ingredients(recipeQuantity,recipeMeasure,ingredientName);
                    ingredients.add(ingredientsObject);
                }

                //Loop trough the steps of each recipe
                for(int iii = 0; iii<stepsArray.length();iii++){
                    JSONObject currentStep = stepsArray.getJSONObject(iii);

                    int stepId = currentStep.getInt("id");
                    String stepShortDescription = currentStep.getString("shortDescription");
                    String stepDescription = currentStep.getString("description");
                    String stepVideoUrl = currentStep.getString("videoURL");
                    String stepThumbnailUrl = currentStep.getString("thumbnailURL");
                    Steps stepsObject = new Steps(stepId,stepShortDescription,
                            stepDescription,stepVideoUrl,stepThumbnailUrl);
                    steps.add(stepsObject);
                }
                Recipe recipes = new Recipe(recipeId,recipeName,recipeImage,ingredients,steps);
                recipesList.add(recipes);
            }
            return recipesList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    };
}
