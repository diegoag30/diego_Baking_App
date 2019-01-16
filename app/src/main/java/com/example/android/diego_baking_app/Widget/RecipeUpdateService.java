package com.example.android.diego_baking_app.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.android.diego_baking_app.Objects.Ingredients;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RecipeUpdateService extends IntentService {
    public static final String UPDATE_RECIPES_LIST =
            "com.example.android.diego_baking_app.update_recipes_list";
    public static final String INGREDIENTS_ARRAY = "INGREDIENTS_ARRAY";
    private ArrayList<String> ingredientsList;


    public RecipeUpdateService() {
        super("RecipeUpdateService");
    }

    public static void startActionUpdateRecipesList(Context context){
        Intent intent = new Intent(context,RecipeUpdateService.class);
        intent.setAction(UPDATE_RECIPES_LIST);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        updateList(intent);
    }

    private void updateList(Intent intent) {
        if (intent!= null && intent.getAction().equals(UPDATE_RECIPES_LIST)){
            //Collecting the list of ingredients
            Bundle bundle = intent.getExtras();
            assert bundle != null;
            ingredientsList = bundle.getStringArrayList(INGREDIENTS_ARRAY);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[]widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipesWidgetProvider.class));
            RecipesWidgetProvider.updateAppWidget(this,appWidgetManager,widgetIds,ingredientsList);
        }
    }
}
