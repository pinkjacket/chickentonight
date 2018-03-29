package pinkjacket.chickentonight;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class RecipeDetail {
    private String name;
    private ArrayList<String> ingredients = new ArrayList<>();
    private String source;

    public RecipeDetail(){}

    public RecipeDetail(String name, ArrayList<String> ingredients, String source){
        this.name = name;
        this.ingredients = ingredients;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getSource() {
        return source;
    }
}
