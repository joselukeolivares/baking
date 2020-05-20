package com.example.baking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.example.baking.ingredients;


public class MainActivity extends AppCompatActivity implements recipesAdapter.OnClickAdapter {
    GridLayoutManager layoutManage;
    recipesAdapter recipesAdapterObj;
    static int  recipeIndex;

    public static ArrayList<recipe> recipes = new ArrayList<>();

    public interface VolleyCallBack{
        void succesVolley(JSONArray response);
    }

    public static recipe recipesToFragment(){
        return recipes.get(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //request_simple();

        int columns = 1;
        if(findViewById(R.id.flag_tablet)!=null){
            columns=3;
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recipes_list);

        layoutManage = new GridLayoutManager(this, columns);


        recipesAdapterObj = new recipesAdapter(this);
        //recipesAdapterObj.updateRecipList(recipeArrayList);

        recyclerView.setLayoutManager(layoutManage);
        recyclerView.setAdapter(recipesAdapterObj);


        getJson.getData(this, new VolleyCallBack() {
            @Override
            public void succesVolley(JSONArray response) {
                Log.i(this.getClass().getName(), response.toString());
                buildRecipesFromJSON(response);
            }
        });


        if (savedInstanceState == null) {
            FragmentManager fragmentManagerObj = getSupportFragmentManager();
        }


    }

    public void buildRecipesFromJSON(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            try {
                if (response.get(i) != null) {
                    recipe mRecipe = new recipe();
                    JSONObject recipe = response.getJSONObject(i);
                    int id = recipe.getInt("id");
                    String name = recipe.getString("name");
                    int servings = recipe.getInt("servings");
                    String imgURL = recipe.getString("image");

                    mRecipe.setId(id);
                    mRecipe.setName(name);
                    mRecipe.setServing(servings);
                    mRecipe.setUrlImage(imgURL);
                    JSONArray ingredientsList = recipe.getJSONArray("ingredients");
                    for (int j = 0; j < ingredientsList.length(); j++) {
                        if (ingredientsList.get(j) != null) {
                            ingredients ingredientsObj = new ingredients();
                            JSONObject ingredientJSON = ingredientsList.getJSONObject(j);
                            Double quantity = ingredientJSON.getDouble("quantity");
                            String measure = ingredientJSON.getString("measure");
                            String ingredientName = ingredientJSON.getString("ingredient");

                            ingredientsObj.setIngredient(ingredientName);
                            ingredientsObj.setQuantity(quantity);
                            ingredientsObj.setMeasure(measure);

                            mRecipe.ingredientsArrayList.add(ingredientsObj);

                        }
                    }
                    JSONArray stepsList = recipe.getJSONArray("steps");
                    for (int k = 0; k < stepsList.length(); k++) {
                        if (stepsList.getJSONObject(k) != null) {
                            JSONObject stepJSON = stepsList.getJSONObject(k);
                            steps step = new steps();
                            int idStep = stepJSON.getInt("id");
                            step.setId(idStep);
                            String shortDescription = stepJSON.getString("shortDescription");
                            step.setShortDescription(shortDescription);
                            String description = stepJSON.getString("description");
                            if(description!=null){
                                step.setDescription(description);
                            }
                            String videoUrl = stepJSON.getString("videoURL");
                            if(videoUrl!=null){
                                step.setVideoURL(videoUrl);
                            }

                            mRecipe.stepsArrayList.add(step);
                        }

                    }
                    recipes.add(mRecipe);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            recipesAdapterObj.updateRecipList(recipes);
            Log.i(this.getClass().getName(), "updated");
            recipesAdapterObj.notifyDataSetChanged();

        }
    }


    @Override
    public void onClick(recipe recipeObj) {
        Log.i(this.getClass().getName(), recipeObj.getName());
        recipeIndex=recipeObj.id;
        Log.i(this.getClass().getName(), "Clicked: "+recipeIndex);
        Intent intent=new Intent(this,steps_recipe.class);
        intent.putExtra("recipe",recipes.get(recipeIndex-1));
        startActivity(intent);
    }


}