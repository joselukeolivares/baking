package com.example.baking;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class steps_recipe_service extends IntentService {

    public steps_recipe_service() {
        super("List_Ingredients");
    }

    public static void updatingService(Context context,String recipeName,String ingredients){

        Intent intent=new Intent(context,steps_recipe_service.class);
        intent.putExtra("recipe",recipeName);
        intent.putExtra("ingredients",ingredients);
        Log.i("updatingService",ingredients);
        intent.setAction("ACTIONMAN");
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


            String recipe=intent.getStringExtra("recipe");
        String ingredients=intent.getStringExtra("ingredients");
            AppWidgetManager appWidgetManagerObj=AppWidgetManager.getInstance(this);
        Log.i("onHandleIntent",recipe);
            //steps_recipe_widget.updateAppWidget(this,appWidgetManagerObj,ingredients,1);
        int[] appWidgetsId=appWidgetManagerObj.getAppWidgetIds(new ComponentName(this,steps_recipe_widget.class));
            steps_recipe_widget.updateListRecipe(this,appWidgetManagerObj,recipe,ingredients,appWidgetsId);



    }
}
