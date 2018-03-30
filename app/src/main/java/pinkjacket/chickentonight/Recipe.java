package pinkjacket.chickentonight;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Recipe {
    private String name;
    private double rating;
    private String source;
    private ArrayList<String> ingredients = new ArrayList<>();
    private String id;

    public Recipe() {}

    public Recipe(String name, double rating, String source, ArrayList<String> ingredients, String id){
        this.name = name;
        this.rating = rating;
        this.source = source;
        this.ingredients = ingredients;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getSource() {
        return source;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getId() {
        return id;
    }
}
