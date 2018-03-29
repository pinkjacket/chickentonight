package pinkjacket.chickentonight;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.recipeDNameTextView) TextView mRecipeDNameTextView;
    @BindView(R.id.ingredientsLTextView) TextView mIngredientsLTextView;
    @BindView(R.id.sourceUTextView) TextView mSourceUTextView;
    @BindView(R.id.saveRecipeButton) Button mSaveRecipeButton;

    private RecipeDetail mRecipeDetail;


    public static RecipeDetailFragment newInstance(RecipeDetail recipeDetail) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipeDetail", Parcels.wrap(recipeDetail));
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mRecipeDetail = Parcels.unwrap(getArguments().getParcelable("recipeDetail"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        mRecipeDNameTextView.setText(mRecipeDetail.getName());
        mIngredientsLTextView.setText(TextUtils.join(",", mRecipeDetail.getIngredients()));
        mSourceUTextView.setText(mRecipeDetail.getSource());

        mSourceUTextView.setOnClickListener(this);
        mSaveRecipeButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v){

    }

}
