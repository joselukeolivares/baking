package com.example.baking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class steps_adapter extends RecyclerView.Adapter<steps_adapter.stepVH> {


    ArrayList<steps> stepsForRecipe=new ArrayList<>();
    onClickFrom_stepsAdapter onClickFrom_stepsAdapterObj;

    public interface onClickFrom_stepsAdapter{
        void onClikckFromAdapter_Step(steps step);
    }

    steps_adapter(onClickFrom_stepsAdapter onclickInterface){
        onClickFrom_stepsAdapterObj=onclickInterface;
    }

    @NonNull
    @Override
    public stepVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layout=R.layout.step_rv;

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(layout,parent,false);

        return new stepVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull stepVH holder, int position) {
        steps stepObj=stepsForRecipe.get(position);
        int id=stepObj.getId();
        String shortDesc=stepObj.getShortDescription()!=null?stepObj.getShortDescription():"unavailable";

        Log.i(this.getClass().getName(),id+":"+shortDesc);

        try{
            holder.stepTextView.setText(shortDesc);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {

        if(stepsForRecipe!=null){
            return stepsForRecipe.size();
        }else{
            return 0;
        }
    }

    class stepVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stepTextView;

        public stepVH(@NonNull View itemView) {
            super(itemView);
            stepTextView=itemView.findViewById(R.id.step_on_rv);
            stepTextView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            steps stepsObj=stepsForRecipe.get(getAdapterPosition());
            onClickFrom_stepsAdapterObj.onClikckFromAdapter_Step(stepsObj);

        }
    }

    public void update_dataStepsAdapter(ArrayList<steps> newData){
        stepsForRecipe=newData;
        Log.i(this.getClass().getName(),newData.size()+"newData Size");
        Log.i(this.getClass().getName(),stepsForRecipe.size()+"steps Size");
    }
}
