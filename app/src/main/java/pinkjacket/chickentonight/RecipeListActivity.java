package pinkjacket.chickentonight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeListActivity extends AppCompatActivity {
    public static final String TAG = RecipeListActivity.class.getSimpleName();

    public ArrayList<Recipe> recipes = new ArrayList<>();
    @BindView(R.id.recipeListView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

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

                    RecipeListActivity.this.runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            String[] recipeNames = new String[recipes.size()];
                            for(int i = 0; i<recipeNames.length; i++){
                                recipeNames[i] = recipes.get(i).getName();
                            }

                            ArrayAdapter adapter = new ArrayAdapter(RecipeListActivity.this,
                                    android.R.layout.simple_list_item_1, recipeNames);
                            mListView.setAdapter(adapter);
                        }
                    });
                }
        });
    }
}
