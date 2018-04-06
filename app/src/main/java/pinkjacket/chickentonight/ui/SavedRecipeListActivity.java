package pinkjacket.chickentonight.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import pinkjacket.chickentonight.Constants;
import pinkjacket.chickentonight.adapters.FirebaseRecipeListAdapter;
import pinkjacket.chickentonight.adapters.FirebaseRecipeViewHolder;
import pinkjacket.chickentonight.R;
import pinkjacket.chickentonight.models.Recipe;
import pinkjacket.chickentonight.util.OnStartDragListener;
import pinkjacket.chickentonight.util.SimpleItemTouchHelperCallback;

public class SavedRecipeListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saved_recipe_list);
    }

}
