package pinkjacket.chickentonight.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import pinkjacket.chickentonight.Constants;
import pinkjacket.chickentonight.R;
import pinkjacket.chickentonight.adapters.FirebaseRecipeListAdapter;
import pinkjacket.chickentonight.adapters.FirebaseRecipeViewHolder;
import pinkjacket.chickentonight.models.Recipe;
import pinkjacket.chickentonight.util.OnStartDragListener;
import pinkjacket.chickentonight.util.SimpleItemTouchHelperCallback;

public class SavedRecipeListFragment extends Fragment implements OnStartDragListener {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private FirebaseRecipeListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;


    public SavedRecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_recipe_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES).child(uid).orderByChild(Constants.FIREBASE_QUERY_INDEX);
        mFirebaseAdapter = new FirebaseRecipeListAdapter(Recipe.class, R.layout.recipe_list_item_drag, FirebaseRecipeViewHolder.class, query, this, getActivity()) {


        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}
