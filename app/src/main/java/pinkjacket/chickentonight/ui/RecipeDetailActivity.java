package pinkjacket.chickentonight.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pinkjacket.chickentonight.R;
import pinkjacket.chickentonight.models.Recipe;
import pinkjacket.chickentonight.adapters.RecipePagerAdapter;

public class RecipeDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private RecipePagerAdapter adapterViewPager;
    ArrayList<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        mRecipes = Parcels.unwrap(getIntent().getParcelableExtra("recipes"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new RecipePagerAdapter(getSupportFragmentManager(), mRecipes);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
