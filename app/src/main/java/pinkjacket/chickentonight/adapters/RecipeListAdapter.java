package pinkjacket.chickentonight.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pinkjacket.chickentonight.Constants;
import pinkjacket.chickentonight.R;
import pinkjacket.chickentonight.models.Recipe;
import pinkjacket.chickentonight.ui.RecipeDetailActivity;
import pinkjacket.chickentonight.ui.RecipeDetailFragment;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>{
    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private Context mContext;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes){
        mContext = context;
        mRecipes = recipes;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position){
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount(){
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.recipeNameTextView) TextView mNameTextView;
        @BindView(R.id.sourceTextView) TextView mSourceTextView;
        @BindView(R.id.ingredientsTextView) TextView mIngredientsTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;
        private int mOrientation;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {

                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }

        private void createDetailFragment(int position){
            RecipeDetailFragment detailFragment = RecipeDetailFragment.newInstance(mRecipes, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.recipeDetailContainer, detailFragment);
            ft.commit();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {

                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(mRecipes));
                mContext.startActivity(intent);
            }
        }


        public void bindRecipe(Recipe recipe){
            mNameTextView.setText(recipe.getName());
            mSourceTextView.setText(recipe.getSource());
            mIngredientsTextView.setText("Top Ingredient: " + recipe.getIngredients().get(0));
            mRatingTextView.setText("Rating: " + recipe.getRating() + "/5");
        }
    }
}