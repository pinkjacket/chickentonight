package pinkjacket.chickentonight;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private double rating;
    private String source;
    private ArrayList<String> ingredients = new ArrayList<>();

    public Recipe(String name, double rating, String source, ArrayList<String> ingredients){
        this.name = name;
        this.rating = rating;
        this.source = source;
        this.ingredients = ingredients;
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
}