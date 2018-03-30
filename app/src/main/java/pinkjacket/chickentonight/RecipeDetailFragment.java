package pinkjacket.chickentonight;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.recipeDNameTextView) TextView mRecipeDNameTextView;
    @BindView(R.id.ingredientsLTextView) TextView mIngredientsLTextView;
    @BindView(R.id.sourceUTextView) TextView mSourceUTextView;
    @BindView(R.id.saveRecipeButton) Button mSaveRecipeButton;

    private Recipe mRecipe;


    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", Parcels.wrap(recipe));
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mRecipe = Parcels.unwrap(getArguments().getParcelable("recipe"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        mRecipeDNameTextView.setText(mRecipe.getName());
        mIngredientsLTextView.setText(TextUtils.join(",", mRecipe.getIngredients()));
        mSourceUTextView.setText(mRecipe.getSource());

        mSourceUTextView.setOnClickListener(this);
        mSaveRecipeButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v){
        if(v == mSaveRecipeButton){
            DatabaseReference recipeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RECIPES);
            recipeRef.push().setValue(mRecipe);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
