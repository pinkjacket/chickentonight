package pinkjacket.chickentonight.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import pinkjacket.chickentonight.Constants;
import pinkjacket.chickentonight.R;
import pinkjacket.chickentonight.models.Recipe;
import pinkjacket.chickentonight.ui.RecipeDetailActivity;

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder{

    View mView;
    Context mContext;
    public ImageView mDragIcon;

    public FirebaseRecipeViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindRecipe(Recipe recipe){
        mDragIcon = (ImageView) mView.findViewById(R.id.dragIcon);
        TextView nameTextView = (TextView) mView.findViewById(R.id.recipeNameTextView);
        TextView ingredientsTextView = (TextView) mView.findViewById(R.id.ingredientsTextView);
        TextView sourceTextView = (TextView) mView.findViewById(R.id.sourceTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        nameTextView.setText(recipe.getName());
        ingredientsTextView.setText(TextUtils.join(",", recipe.getIngredients()));
        sourceTextView.setText(recipe.getSource());
        ratingTextView.setText("Rating: " + recipe.getRating() + "/5");
    }

}
