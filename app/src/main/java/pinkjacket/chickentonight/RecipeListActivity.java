package pinkjacket.chickentonight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeListActivity extends AppCompatActivity {
    public static final String TAG = RecipeListActivity.class.getSimpleName();

    public ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        getRecipes(search);
    }
    private void getRecipes(String search){
        final YumService yumService = new YumService();
        yumService.findRecipes(search, new Callback(){

            @Override
            public void onFailure(Call call, IOException e){
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException{
//                    String jsonData = response.body().string();
//                    Log.v(TAG, jsonData);
                    recipes = yumService.processResults(response);
                    Log.v("RESPONSE",  recipes.get(0).getName());
                }
        });
    }
}
