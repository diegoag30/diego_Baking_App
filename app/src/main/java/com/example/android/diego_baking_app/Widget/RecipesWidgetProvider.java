package com.example.android.diego_baking_app.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.diego_baking_app.MainActivity;
import com.example.android.diego_baking_app.R;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class RecipesWidgetProvider extends AppWidgetProvider {

    public static ArrayList<String>ingredientsList;

    public RecipesWidgetProvider(){

    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetIds[], ArrayList<String>ingredients) {
        ingredientsList = ingredients;

        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, UpdateIngredientsService.class);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_gridview);
            views.setRemoteAdapter(R.id.widget_lv,intent);
            ComponentName componentName = new ComponentName(context,RecipesWidgetProvider.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.widget_lv);
            appWidgetManager.updateAppWidget(componentName,views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
            super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

