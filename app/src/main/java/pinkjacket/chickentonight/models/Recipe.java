package pinkjacket.chickentonight.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Recipe {
    private String name;
    private double rating;
    private String source;
    private List<String> ingredients = new ArrayList<>();
    private String id;
    private String pushId;
    String index;

    public Recipe() {}

    public Recipe(String name, double rating, String source, ArrayList<String> ingredients, String id){
        this.name = name;
        this.rating = rating;
        this.source = source;
        this.ingredients = ingredients;
        this.id = id;
        this.index = "not_specified";
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getId() {
        return id;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
