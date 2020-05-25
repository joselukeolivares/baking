package com.example.baking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class steps_recipe extends AppCompatActivity implements MasterFragmentList.onRecipeClickListener{
boolean tabletDevice=false;
String className=this.getClass().getName();
static recipe  actual_recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intentGet=getIntent();

        if(intentGet!=null){
            actual_recipe= (recipe) intentGet.getParcelableExtra("recipe");
            setContentView(R.layout.activity_steps_recipe);
            //videoURL=recipeObj.getStepsArrayList().get(0).videoURL;
            try{

                String ingredientsWidget="";

                for (int i=0;i<actual_recipe.getIngredientsArrayList().size();i++){

                    ingredients ingredient=actual_recipe.getIngredientsArrayList().get(i);
                    Log.i("recipe_widget",ingredient.getIngredient());
                    ingredientsWidget+=ingredient.getIngredient()+"";

                }
                /*
                TextView textView=(TextView)findViewById(R.id.appwidget_text);
                textView.setMovementMethod(new ScrollingMovementMethod());

                 */
                steps_recipe_service.updatingService(this,actual_recipe.getName(),ingredientsWidget);
            }catch (Exception e){
                e.printStackTrace();
            }

            if(findViewById(R.id.tv_video_frameLyaout)!=null){
                tabletDevice=true;
                adminFragments(actual_recipe.getStepsArrayList().get(0));
            }

        }




    }

    public static recipe recipesToFragment(){
        return actual_recipe;
    }

    private void step_detail(steps stepObj) {
        Intent intent=new Intent(this,video_activity.class);
        intent.putExtra("recipe",actual_recipe);
        intent.putExtra("step",stepObj.getId());
        startActivity(intent);
    }

    @Override
    public void onRecipeSelected(steps stepFromMaster) {
        //step_detail();
        Log.i(className,"step:"+stepFromMaster.getId());
        Log.i(className,stepFromMaster.getVideoURL()!=null?stepFromMaster.getVideoURL():"No hay video");
        if(tabletDevice){
            adminFragments(stepFromMaster);
        }else{
            step_detail(stepFromMaster);
        }

    }
    FragmentManager fragmentManagerObj=getSupportFragmentManager();
    videoFragment videoFragmentObj=new videoFragment();
    public void adminFragments(steps stepObj){
        String videoURL=stepObj.getVideoURL();
        String description=stepObj.getDescription();


        Log.i(className,"recipe videoURL:"+videoURL);
        if(videoURL!=null && !videoURL.equals("")){

            videoFragmentObj.setURL(videoURL);
            fragmentManagerObj.beginTransaction()
                    .replace(R.id.tv_video_frameLyaout,videoFragmentObj)
                    .commit();

        }else{
            fragmentManagerObj.beginTransaction()
                    .remove(videoFragmentObj)
                    .commit();
        }
        if(description!=null && !description.equals("")){
            step_description step_descriptionObj=new step_description();
            step_descriptionObj.setDescription(description);
            fragmentManagerObj.beginTransaction()
                    .replace(R.id.step_description_frameLayout,step_descriptionObj)
                    .commit();
        }


    }


}
