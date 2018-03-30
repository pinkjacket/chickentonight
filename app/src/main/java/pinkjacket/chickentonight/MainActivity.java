package pinkjacket.chickentonight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.findRecipesButton) Button mFindRecipesButton;
    @BindView(R.id.searchEditText) EditText mSearchEditText;
    @BindView(R.id.titleTextView) TextView mTitleTextView;
    @BindView(R.id.gitButton) Button mGitButton;
    @BindView(R.id.listButton) Button mListButton;
    private DatabaseReference mSearchedTermReference;
    private ValueEventListener mSearchedTermReferenceListener;

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedTermReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_TERM);

        mSearchedTermReferenceListener = mSearchedTermReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot searchSnapshot : dataSnapshot.getChildren()){
                String search = searchSnapshot.getValue().toString();
                Log.d("Searches updated", "search: " + search);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
        mFindRecipesButton.setOnClickListener(this);
        mGitButton.setOnClickListener(this);
        mListButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == mFindRecipesButton){
            String search = mSearchEditText.getText().toString();
            saveSearchToFirebase(search);
//            if(!(search).equals("")){
//                addToSharedPreferences(search);
//            }
            Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
            intent.putExtra("search", search);
            startActivity(intent);
        }
        if (v == mGitButton){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/pinkjacket"));
            startActivity(webIntent);
        }
        if(v == mListButton){
            Intent intent = new Intent(MainActivity.this, SavedRecipeListActivity.class);
            startActivity(intent);
        }
    }

    public void saveSearchToFirebase(String search){
        mSearchedTermReference.push().setValue(search);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mSearchedTermReference.removeEventListener(mSearchedTermReferenceListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

//    private void addToSharedPreferences(String search){
//        mEditor.putString(Constants.PREFERENCES_SEARCH_KEY, search).apply();
//    }
}
