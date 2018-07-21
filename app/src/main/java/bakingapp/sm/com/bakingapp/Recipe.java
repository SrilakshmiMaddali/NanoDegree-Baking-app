package bakingapp.sm.com.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    private Integer id;
    private String name;
    private Integer servings;
    private String image;
    private List<Ingredient> ingredients = null;
    private List<RecipeStep> recipeSteps = null;

    protected Recipe(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        servings = in.readByte() == 0x00 ? null : in.readInt();
        image = in.readString();
        if (in.readByte() == 0x01) {
            ingredients = new ArrayList<>();
            in.readList(ingredients, Ingredient.class.getClassLoader());
        } else {
            ingredients = null;
        }
        if (in.readByte() == 0x01) {
            recipeSteps = new ArrayList<>();
            in.readList(recipeSteps, RecipeStep.class.getClassLoader());
        } else {
            recipeSteps = null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {return ingredients;}

    //public void setIngredients(List<Ingredient> ingredients) {this.ingredients = ingredients;}

    public List<RecipeStep> getRecipeSteps() {return recipeSteps;}

    //public void setSteps(List<RecipeStep> recipeSteps) {this.recipeSteps = recipeSteps;}

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        if (servings == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeInt(servings);
        }
        parcel.writeString(image);
        if (ingredients == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeList(ingredients);
        }
        if (recipeSteps == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeList(recipeSteps);
        }

    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    //Inner class for Recipe List results
    public static class RecipeResult {
        private List<Recipe> results;

        public List<Recipe> getResults() {
            return results;
        }
    }
}
