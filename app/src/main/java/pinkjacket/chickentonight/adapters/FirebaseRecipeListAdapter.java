package pinkjacket.chickentonight.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import pinkjacket.chickentonight.models.Recipe;
import pinkjacket.chickentonight.util.ItemTouchHelperAdapter;
import pinkjacket.chickentonight.util.OnStartDragListener;

public class FirebaseRecipeListAdapter extends FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder> implements ItemTouchHelperAdapter{
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseRecipeListAdapter(Class<Recipe> modelClass, int modelLayout,
                                     Class<FirebaseRecipeViewHolder> viewHolderClass,
                                     Query ref, OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(final FirebaseRecipeViewHolder viewHolder, Recipe model, int position){
        viewHolder.bindRecipe(model);
        viewHolder.mDragIcon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        return false;
    }

    @Override
    public void onItemDismiss(int position){

    }

}
