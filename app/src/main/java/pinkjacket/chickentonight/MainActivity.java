package pinkjacket.chickentonight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.findRecipesButton) Button mFindRecipesButton;
    @BindView(R.id.searchEditText) EditText mSearchEditText;
    @BindView(R.id.titleTextView) TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindRecipesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String search = mSearchEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                intent.putExtra("search", search);
                startActivity(intent);
            }
        });
    }
}
