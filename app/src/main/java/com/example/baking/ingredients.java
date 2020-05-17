package com.example.baking;

import android.os.Parcel;
import android.os.Parcelable;

class ingredients implements Parcelable {

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    String measure;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    double quantity;

    protected ingredients(Parcel in) {
        quantity = in.readDouble();
        ingredient = in.readString();
        measure=in.readString();
    }

    public static final Creator<ingredients> CREATOR = new Creator<ingredients>() {
        @Override
        public ingredients createFromParcel(Parcel in) {
            return new ingredients(in);
        }

        @Override
        public ingredients[] newArray(int size) {
            return new ingredients[size];
        }
    };

    public ingredients() {

    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    String ingredient;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(quantity);
        dest.writeString(ingredient);
        dest.writeString(measure);
    }
}
