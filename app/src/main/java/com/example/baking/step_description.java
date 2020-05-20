package com.example.baking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class step_description extends Fragment {
    TextView description_txtView;

    public step_description(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.step_description,container,false);
        description_txtView=(TextView)rootView.findViewById(R.id.step_description_in_fragment);

        return rootView;
    }

    public void setDescription(String description){
        descriptionLocal =description;
    }
    private String descriptionLocal;
    @Override
    public void onStart() {
        super.onStart();
        if(description_txtView!=null){
            description_txtView.setText(descriptionLocal);
        }

    }
}
