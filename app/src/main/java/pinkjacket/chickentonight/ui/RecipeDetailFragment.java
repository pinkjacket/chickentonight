package pinkjacket.chickentonight.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pinkjacket.chickentonight.Constants;
import pinkjacket.chickentonight.R;
import pinkjacket.chickentonight.models.Recipe;

public class RecipeDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.recipeDNameTextView) TextView mRecipeDNameTextView;
    @BindView(R.id.ingredientsLTextView) TextView mIngredientsLTextView;
    @BindView(R.id.sourceUTextView) TextView mSourceUTextView;
    @BindView(R.id.saveRecipeButton) Button mSaveRecipeButton;

    private Recipe mRecipe;
    private ArrayList<Recipe> mRecipes;
    private int mPosition;


    public static RecipeDetailFragment newInstance(ArrayList<Recipe> recipes, Integer position) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(recipes));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mRecipes = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_RECIPES));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mRecipe = mRecipes.get(mPosition);
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
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference recipeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RECIPES)
                    .child(uid);

            DatabaseReference pushRef = recipeRef.push();
            String pushId = pushRef.getKey();
            mRecipe.setPushId(pushId);
            pushRef.setValue(mRecipe);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
