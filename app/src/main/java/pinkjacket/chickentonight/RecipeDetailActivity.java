package pinkjacket.chickentonight;

import android.content.Intent;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeDetailActivity extends AppCompatActivity {
    public ArrayList<RecipeDetail> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        String searchId = intent.getStringExtra("searchId");
        getDetails(searchId);
    }

    private void getDetails(String searchId){
        final YumService yumService = new YumService();
        yumService.findDetails(searchId, new Callback(){

            @Override
            public void onFailure(Call call, IOException e){
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException{
                details = yumService.processDetails(response);
                Log.v("RESPONSE",  details.get(0).getSource());

//                RecipeDetailActivity.this.runOnUiThread(new Runnable(){
//                    @Override
//                    public void run(){
//                        mAdapter = new RecipeListAdapter(getApplicationContext(), recipes);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager =
//                                new LinearLayoutManager(RecipeListActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//                });
            }
        });
    }
}
