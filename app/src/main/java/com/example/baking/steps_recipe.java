package com.example.baking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class steps_recipe extends AppCompatActivity implements MasterFragmentList.onRecipeClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_recipe);

        if(findViewById(R.id.tv_video_frameLyaout)!=null){
            videoFragment videoFragmentObj=new videoFragment();

            FragmentManager fragmentManagerObj=getSupportFragmentManager();

            fragmentManagerObj.beginTransaction()
                    .add(R.id.tv_video_frameLyaout,videoFragmentObj)
                    .commit();

        }


    }

    @Override
    public void onRecipeSelected(int position) {

    }
}
