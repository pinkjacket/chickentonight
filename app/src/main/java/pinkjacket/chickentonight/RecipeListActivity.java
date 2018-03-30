package pinkjacket.chickentonight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private SharedPreferences mSharedPreferences;
    private String mRecentSearch;
    public static final String TAG = RecipeListActivity.class.getSimpleName();

    public ArrayList<Recipe> recipes = new ArrayList<>();
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        getRecipes(search);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSearch = mSharedPreferences.getString(Constants.PREFERENCES_SEARCH_KEY, null);
        if (mRecentSearch != null){
            getRecipes(mRecentSearch);
        }
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
                    recipes = yumService.processResults(response);
                    Log.v("RESPONSE",  recipes.get(0).getName());

                    RecipeListActivity.this.runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                           mAdapter = new RecipeListAdapter(getApplicationContext(), recipes);
                           mRecyclerView.setAdapter(mAdapter);
                           RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(RecipeListActivity.this);
                           mRecyclerView.setLayoutManager(layoutManager);
                           mRecyclerView.setHasFixedSize(true);
                        }
                    });
                }
        });
    }
}
