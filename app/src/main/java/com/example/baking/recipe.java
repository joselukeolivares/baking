package com.example.baking;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class recipe implements Parcelable {
    int id;
    String name;

    protected recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredientsArrayList = in.createTypedArrayList(ingredients.CREATOR);
        stepsArrayList = in.createTypedArrayList(steps.CREATOR);
        serving = in.readInt();
        urlImage = in.readString();
    }

    public static final Creator<recipe> CREATOR = new Creator<recipe>() {
        @Override
        public recipe createFromParcel(Parcel in) {
            return new recipe(in);
        }

        @Override
        public recipe[] newArray(int size) {
            return new recipe[size];
        }
    };

    public recipe() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ingredients> getIngredientsArrayList() {
        return ingredientsArrayList;
    }

    public void setIngredientsArrayList(ArrayList<ingredients> ingredientsArrayList) {
        this.ingredientsArrayList = ingredientsArrayList;
    }

    public ArrayList<steps> getStepsArrayList() {
        return stepsArrayList;
    }

    public void setStepsArrayList(ArrayList<steps> stepsArrayList) {
        this.stepsArrayList = stepsArrayList;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }



    ArrayList<ingredients> ingredientsArrayList=new ArrayList<>();
    ArrayList<steps> stepsArrayList=new ArrayList<>();
    int serving;
    String urlImage;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(ingredientsArrayList);
        dest.writeTypedList(stepsArrayList);
        dest.writeInt(serving);
        dest.writeString(urlImage);
    }
}

/*
[
{
"id":1,
"name":"Nutella Pie",
"ingredients":[
    {"quantity":2,"measure":"CUP","ingredient":"Graham Cracker crumbs"},
    {"quantity":6,"measure":"TBLSP","ingredient":"unsalted butter, melted"},
    {"quantity":0.5,"measure":"CUP","ingredient":"granulated sugar"},
    {"quantity":1.5,"measure":"TSP","ingredient":"salt"},
    {"quantity":5,"measure":"TBLSP","ingredient":"vanilla"},
    {"quantity":1,"measure":"K","ingredient":"Nutella or other chocolate-hazelnut spread"},
    {"quantity":500,"measure":"G","ingredient":"Mascapone Cheese(room temperature)"},
    {"quantity":1,"measure":"CUP","ingredient":"heavy cream(cold)"},
    {"quantity":4,"measure":"OZ","ingredient":"cream cheese(softened)"}],
 "steps":[
    {"id":0,"shortDescription":"Recipe Introduction","description":"Recipe Introduction","videoURL":"https:\/\/d17h27t6h515a5.cloudfront.net\/topher\/2017\/April\/58ffd974_-intro-creampie\/-intro-creampie.mp4","thumbnailURL":""},
    {"id":1,"shortDescription":"Starting prep","description":"1. Preheat the oven to 350°F. Butter a 9\" deep dish pie pan.","videoURL":"","thumbnailURL":""},
    {"id":2,"shortDescription":"Prep the cookie crust.","description":"2. Whisk the graham cracker crumbs, 50 grams (1\/4 cup) of sugar, and 1\/2 teaspoon of salt together in a medium bowl. Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients and stir together until evenly mixed.","videoURL":"https:\/\/d17h27t6h515a5.cloudfront.net\/topher\/2017\/April\/58ffd9a6_2-mix-sugar-crackers-creampie\/2-mix-sugar-crackers-creampie.mp4","thumbnailURL":""},
    {"id":3,"shortDescription":"Press the crust into baking form.","description":"3. Press the cookie crumb mixture into the prepared pie pan and bake for 12 minutes. Let crust cool to room temperature.","videoURL":"https:\/\/d17h27t6h515a5.cloudfront.net\/topher\/2017\/April\/58ffd9cb_4-press-crumbs-in-pie-plate-creampie\/4-press-crumbs-in-pie-plate-creampie.mp4","thumbnailURL":""},
    {"id":4,"shortDescription":"Start filling prep","description":"4. Beat together the nutella, mascarpone, 1 teaspoon of salt, and 1 tablespoon of vanilla on medium speed in a stand mixer or high speed with a hand mixer until fluffy.","videoURL":"https:\/\/d17h27t6h515a5.cloudfront.net\/topher\/2017\/April\/58ffd97a_1-mix-marscapone-nutella-creampie\/1-mix-marscapone-nutella-creampie.mp4","thumbnailURL":""},
    {"id":5,"shortDescription":"Finish filling prep","description":"5. Beat the cream cheese and 50 grams (1\/4 cup) of sugar on medium speed in a stand mixer or high speed with a hand mixer for 3 minutes. Decrease the speed to medium-low and gradually add in the cold cream. Add in 2 teaspoons of vanilla and beat until stiff peaks form.","videoURL":"","thumbnailURL":"https:\/\/d17h27t6h515a5.cloudfront.net\/topher\/2017\/April\/58ffda20_7-add-cream-mix-creampie\/7-add-cream-mix-creampie.mp4"},
    {"id":6,"shortDescription":"Finishing Steps","description":"6. Pour the filling into the prepared crust and smooth the top. Spread the whipped cream over the filling. Refrigerate the pie for at least 2 hours. Then it's ready to serve!","videoURL":"https:\/\/d17h27t6h515a5.cloudfront.net\/topher\/2017\/April\/58ffda45_9-add-mixed-nutella-to-crust-creampie\/9-add-mixed-nutella-to-crust-creampie.mp4","thumbnailURL":""}],
    "servings":8,
    "image":""},
 {
"id":2,
"name":"Brownies",
"ingredients":[
    {"quantity":350,"measure":"G","ingredient":"Bittersweet chocolate (60-70% cacao)"},
    {"quantity":226,"measure":"G","ingredient":"unsalted butter"},
    {"quantity":300,"measure":"G","ingredient":"granulated sugar"},
    {"quantity":100,"measure":"G","ingredient":"light brown sugar"},
    {"quantity":5,"measure":"UNIT","ingredient":"large eggs"},
    {"quantity":1,"measure":"TBLSP","ingredient":"vanilla extract"},
    {"quantity":140,"measure":"G","ingredient":"all purpose flour"},
    {"quantity":40,"measure":"G","ingredient":"cocoa powder"},
    {"quantity":1.5,"measure":"TSP","ingredient":"salt"},
    {"quantity":350,"measure":"G","ingredient":"semisweet chocolate chips"}],
"steps":[{"id":0,"shortDescription":"Recipe Introduction","description":"Recipe Introduction","videoUR

 */