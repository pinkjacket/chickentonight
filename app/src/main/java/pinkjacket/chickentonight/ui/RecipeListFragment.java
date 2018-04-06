package pinkjacket.chickentonight.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import pinkjacket.chickentonight.Constants;
import pinkjacket.chickentonight.R;
import pinkjacket.chickentonight.adapters.RecipeListAdapter;
import pinkjacket.chickentonight.models.Recipe;
import pinkjacket.chickentonight.services.YumService;

public class RecipeListFragment extends Fragment {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private RecipeListAdapter mAdapter;
    public ArrayList<Recipe> mRecipes = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentSearch;
    public static final String TAG = RecipeListActivity.class.getSimpleName();


    public RecipeListFragment() {
        // Required empty public constructor
    }

    public void getRecipes(String search) {
        final YumService yumService = new YumService();
        yumService.findRecipes(search, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mRecipes = yumService.processResults(response);
                Log.v("RESPONSE", mRecipes.get(0).getName());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new RecipeListAdapter(getActivity(), mRecipes);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }
        });
    }

    private void addToSharedPreferences(String search){
        mEditor.putString(Constants.PREFERENCES_SEARCH_KEY, search).apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);

        mRecentSearch = mSharedPreferences.getString(Constants.PREFERENCES_SEARCH_KEY, null);

        if (mRecentSearch != null){
            getRecipes(mRecentSearch);
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();
    }

}
