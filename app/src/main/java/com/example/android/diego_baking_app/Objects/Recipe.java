package com.example.android.diego_baking_app.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
    private int id;
    private String recipeName;
    private String cardImage;
    private ArrayList<Ingredients> ingredients;
    private ArrayList<Steps> steps;

    public Recipe( int id,String recipeName, String cardImage,
                   ArrayList<Ingredients>ingredients, ArrayList<Steps>steps){
        this.id = id;
        this.recipeName = recipeName;
        this.cardImage = cardImage;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(recipeName);
        parcel.writeString(cardImage);
        parcel.writeTypedList(ingredients);
        parcel.writeTypedList(steps);
    }

    protected Recipe(Parcel parcelItem){
        this.id = parcelItem.readInt();
        this.recipeName = parcelItem.readString();
        this.cardImage = parcelItem.readString();
        parcelItem.readTypedList(this.ingredients,Ingredients.CREATOR);
        parcelItem.readTypedList(this.steps,Steps.CREATOR);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
