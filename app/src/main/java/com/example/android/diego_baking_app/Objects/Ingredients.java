package com.example.android.diego_baking_app.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {
    private int quantity;
    private String measure;
    private String ingredient;

    public Ingredients(int quantity, String measure, String ingredient){
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    //Quantity getter and setter
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Measure getter and setter
    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    //Ingredient getter and setter
    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    //Parcelable methods and overrides
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }

    protected Ingredients(Parcel parcelItem){
        this.quantity = parcelItem.readInt();
        this.measure = parcelItem.readString();
        this.ingredient = parcelItem.readString();
    }

    public static final Parcelable.Creator<Ingredients>CREATOR = new Parcelable.Creator<Ingredients>(){

        @Override
        public Ingredients createFromParcel(Parcel parcel){
            return new Ingredients(parcel);
        }

        @Override
        public Ingredients[] newArray(int i) {
            return new Ingredients[i];
        }
    };
}
