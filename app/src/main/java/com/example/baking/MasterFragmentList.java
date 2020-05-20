package com.example.baking;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MasterFragmentList  extends Fragment implements steps_adapter.onClickFrom_stepsAdapter {
    onRecipeClickListener mCallback;
    ArrayList<recipe> recipes=new ArrayList<>();
    Context foreignContext;
    steps_adapter stepAdapter;

    @Override
    public void onClikckFromAdapter_Step(steps step) {
        //Log.i(this.getClass().getName(),step.getShortDescription());
        //Log.i(this.getClass().getName(),step.getVideoURL()!=null?step.getVideoURL():"Not videoURL");
        updating_step_info(step);
    }

    public void updating_step_info(steps step){
        mCallback.onRecipeSelected(step);
    }


    public interface onRecipeClickListener{
        void onRecipeSelected(steps stepObj);
    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            mCallback=(onRecipeClickListener) context;
            foreignContext=context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement onRecipeListeners");
        }
    }
    ArrayList<recipe> recipeArrayList;
    public MasterFragmentList(){

    }

    GridLayoutManager layoutManager;
    steps_adapter steps_recipeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        layoutManager=new GridLayoutManager(inflater.getContext(),1);
        steps_recipeAdapter=new steps_adapter(this);


        final View rootView=inflater.inflate(R.layout.recipes_recycler_view,container,false);


        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.steps_recyclerView);
        if(recyclerView==null){
            Log.i(this.getClass().getName(),"Es nulo");
        }else{
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(steps_recipeAdapter);
            updateRecipe_MasterFragment();

        }








        return rootView;
    }

    public void updateRecipe_MasterFragment(){
        try{
            recipe recipeObj=steps_recipe.recipesToFragment();
            Log.i(this.getClass().getName(),recipeObj.id+"");
            steps_recipeAdapter.update_dataStepsAdapter(recipeObj.getStepsArrayList());
            steps_recipeAdapter.notifyDataSetChanged();


        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
