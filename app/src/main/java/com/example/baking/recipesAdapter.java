package com.example.baking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recipesAdapter extends RecyclerView.Adapter<recipesAdapter.VH> {
    Context context;
    ArrayList<recipe> recipesArrayList=new ArrayList<>();
    OnClickAdapter onClickAdapter;

    public recipesAdapter(OnClickAdapter onClickAdapter1){

        this.onClickAdapter=onClickAdapter1;
    }



    public interface OnClickAdapter{
        void onClick(recipe recipeObj);
    }

    @NonNull
    @Override
    public recipesAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        int layout=R.layout.recipe;

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(layout,parent,false);
        Log.i(this.getClass().getName(),"creatingViewHolder");
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recipesAdapter.VH holder, int position) {
        recipe recipeObj=recipesArrayList.get(position);
        String name=recipeObj.getName();

        holder.name.setText(name);

        if(recipeObj.getUrlImage()!=null && !recipeObj.getUrlImage().trim().equals("") && recipeObj.getUrlImage().length()!=0 && !recipeObj.getUrlImage().isEmpty()){
            try{
                Picasso.with(context).load(recipeObj.getUrlImage()).into(holder.poster);
                Log.i("pathURL",recipeObj.getUrlImage());
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }

        }


    }

    @Override
    public int getItemCount() {
        if(recipesArrayList!=null){
            return recipesArrayList.size();
        }else{
            return 0;
        }

    }

    public void updateRecipList(ArrayList<recipe> list){
        recipesArrayList=list;
    }

    public class VH  extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView poster;
        public VH(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.recipe_txtView);
            itemView.setOnClickListener(this);
            poster=itemView.findViewById(R.id.imageView);

        }

        @Override
        public void onClick(View v) {
            recipe  recipeObj=recipesArrayList.get(getAdapterPosition());
            onClickAdapter.onClick(recipeObj);
        }
    }


}
