package com.example.baking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class video_activity extends AppCompatActivity {
    recipe recipeObj;
    int stepId;
    String videoUrl;
    String description;
    ImageButton previous_step;
    ImageButton next_step;
    FragmentManager fragmentManagerObj=getSupportFragmentManager();
    videoFragment videoFragmentObj=new videoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_activity);



        previous_step=(ImageButton)findViewById(R.id.prev_step_btn);

        previous_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stepId!=0){
                    stepId--;

                }
                show_hide_buttons();

            }
        });

        next_step=(ImageButton)findViewById(R.id.next_step_btn);
        next_step.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(stepId!=recipeObj.getStepsArrayList().size()-1){
                    stepId++;

                }
                show_hide_buttons();
            }
        });

        Intent intent=getIntent();

        if(savedInstanceState!=null){
            stepId=savedInstanceState.getInt("id_step");
            recipeObj=savedInstanceState.getParcelable("step");
            show_hide_buttons();
            }else if(intent!=null){
                recipeObj=intent.getParcelableExtra("recipe");

                stepId=intent.getIntExtra("step",0);
                //steps stepObj=recipeObj.getStepsArrayList().get(stepId);
                //String description=stepObj.getDescription();
                show_hide_buttons();
                //updateFragmentsContent(stepObj.getVideoURL()==null?"":stepObj.getVideoURL(),description);



            }

        }


        public void updateFragmentsContent(String videoUrl,String description){




            videoFragmentObj.setURL(videoUrl);

            if(videoUrl!=null && !videoUrl.equals("")){

                //videoFragmentObj.setURL(videoUrl);
                videoFragmentObj.resetVideoPlayback();

                fragmentManagerObj.beginTransaction()
                        .replace(R.id.tv_video_frameLyaout,videoFragmentObj)
                        .commit();

            }else{

                fragmentManagerObj.beginTransaction()
                        .remove(videoFragmentObj)
                        .commit();

            }
            if(description!=null&&!description.equals("")){
                step_description step_descriptionObj=new step_description();
                step_descriptionObj.setDescription(description);

                fragmentManagerObj.beginTransaction()
                        .replace(R.id.step_description_frameLayout,step_descriptionObj)
                        .commit();
            }


        }

        public void show_hide_buttons(){

            if(((stepId)==recipeObj.getStepsArrayList().size()-1)){
                next_step.setVisibility(View.INVISIBLE);
                previous_step.setVisibility(View.VISIBLE);
            }else if(((stepId)==0)){
                previous_step.setVisibility(View.INVISIBLE);
                next_step.setVisibility(View.VISIBLE);
                Log.i("id","ID==0 and next is visible");
            }else{
                previous_step.setVisibility(View.VISIBLE);
                next_step.setVisibility(View.VISIBLE);
            }



            /*
            Log.i(this.getClass().getName(),"id:"+stepId);
            Log.i(this.getClass().getName(),"recipe Steps:"+recipeObj.getStepsArrayList().size());
            Log.i(this.getClass().getName(),"recipe Steps:"+recipeObj.getId()+" "+recipeObj.getName());

            for(int i=0;i<recipeObj.getStepsArrayList().size();i++){
                Log.i(this.getClass().getName(),"recipe Steps:"+recipeObj.getStepsArrayList().get(i).getId());
                Log.i(this.getClass().getName(),"recipe Steps:"+recipeObj.getStepsArrayList().get(i).getDescription());
            }
            */
            videoUrl=recipeObj.getStepsArrayList().get(stepId).getVideoURL();
            description=recipeObj.getStepsArrayList().get(stepId).getDescription();

            updateFragmentsContent(videoUrl,description);

        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("id_step",stepId);
        outState.putString("url_video",videoUrl);
        outState.putString("description",description);
        outState.putParcelable("step",recipeObj);
    }
}
