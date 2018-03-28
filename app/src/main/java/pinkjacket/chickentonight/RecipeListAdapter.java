package pinkjacket.chickentonight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recipeNameTextView) TextView mNameTextView;
        @BindView(R.id.sourceTextView) TextView mSourceTextView;
        @BindView(R.id.ingredientsTextView) TextView mIngredientsTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;
        @BindView(R.id.idTextView) TextView mIdTextView;

        private Context mContext;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRecipe(Recipe recipe){
            mNameTextView.setText(recipe.getName());
            mSourceTextView.setText(recipe.getSource());
            mIngredientsTextView.setText("Top Ingredient: " + recipe.getIngredients().get(0));
            mRatingTextView.setText("Rating: " + recipe.getRating() + "/5");
            mIdTextView.setText("ID: " + recipe.getId());
        }
    }
}