package bakingapp.sm.com.bakingapp.userInterface;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import bakingapp.sm.com.bakingapp.adapter.RecipesAdapter;
import bakingapp.sm.com.bakingapp.R;
import bakingapp.sm.com.bakingapp.Recipe;
import bakingapp.sm.com.bakingapp.SimpleIdlingResource;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.RecipeListItemClickListener{

    public static String ALL_RECIPES="All_Recipes";
    static String SELECTED_RECIPES="Selected_Recipes";
    static String SELECTED_STEPS="Selected_Steps";
    static String SELECTED_INDEX="Selected_Index";
    public static final String SHARED_PREFS_KEY = "SHARED_PREFS_KEY";
    @Nullable
    private SimpleIdlingResource mIdlingResource;
    boolean recipeTwoPane;

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting up Toolbar
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Baking World");
    }

    @Override
    public void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRecipeListItemClick(Recipe selectedItemIndex) {
        if (recipeTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            View view = findViewById(R.id.imageView);
            if (view.getVisibility() == View.INVISIBLE) {
                findViewById(R.id.imageView).setVisibility(View.VISIBLE);
            }
        } else {

            Bundle recipeBundleSelected = new Bundle();
            ArrayList<Recipe> recipeSelected = new ArrayList<>();
            recipeSelected.add(selectedItemIndex);
            recipeBundleSelected.putParcelableArrayList(SELECTED_RECIPES, recipeSelected);

            final Intent intent = new Intent(this, RecipeDetailActivity.class);
            intent.putExtras(recipeBundleSelected);
            startActivity(intent);

        }
    }
}
