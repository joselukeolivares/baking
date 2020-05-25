package com.example.baking;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ListView;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class steps_recipe_widget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, String recipe,String ingredients,
                                int appWidgetId) {

        CharSequence widgetText =recipe;
        Log.i("Provider",widgetText+"");
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.steps_recipe_widget);

        views.setTextViewText(R.id.appwidget_text, ingredients);
        views.setTextViewText(R.id.widgetjl,recipe);



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }

    /*
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

     */

    public static void updateListRecipe(Context context,AppWidgetManager appWidgetManager,String recipe,String ingredients,int[] appWidgetsId){

            for(int appWidgetId:appWidgetsId){
                updateAppWidget(context, appWidgetManager,recipe,ingredients, appWidgetId);
            }
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

