package com.example.android.diego_baking_app.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.diego_baking_app.R;

import java.util.ArrayList;

public class UpdateIngredientsService extends RemoteViewsService {

    public IngredientsFactory onGetViewFactory(Intent intent)
    {
        return new IngredientsFactory(this.getApplicationContext());
    }
}
class IngredientsFactory implements RemoteViewsService.RemoteViewsFactory{
    private Context context;
    private ArrayList<String>ingredientsArrayList;

    public IngredientsFactory(Context context){
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        ingredientsArrayList = RecipesWidgetProvider.ingredientsList;

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if(ingredientsArrayList == null)
        return 0;
        return ingredientsArrayList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_listview_item);
        views.setTextViewText(R.id.listview_widget_item,ingredientsArrayList.get(i));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}