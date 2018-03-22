package pinkjacket.chickentonight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YumService {
    public static void findRecipes(String search, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YUMMLY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YUMMLY_BASE_SEARCH_TERM, search);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("X-Yummly-App-ID", Constants.YUMMLY_ID)
                .header("X-Yummly-App-Key", Constants.YUMMLY_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Recipe> processResults(Response response){
        ArrayList<Recipe> recipes = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONObject yumJSON = new JSONObject(jsonData);
            JSONArray matchesJSON = yumJSON.getJSONArray("matches");
            for (int i = 0; i < matchesJSON.length(); i++){
                JSONObject recipeJSON = matchesJSON.getJSONObject(i);
                String name = recipeJSON.getString("recipeName");
                double rating = recipeJSON.optDouble("rating", 3);
                String source = recipeJSON.optString("sourceDisplayName", "No source available");

                ArrayList<String> ingredients = new ArrayList<>();
                JSONArray ingredientsJSON = recipeJSON.getJSONArray("ingredients");
                for (int y = 0; y < ingredientsJSON.length(); y++){
                    ingredients.add(ingredientsJSON.get(y).toString());
                }

                Recipe recipe = new Recipe(name, rating, source, ingredients);
                recipes.add(recipe);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return recipes;
    }
}
