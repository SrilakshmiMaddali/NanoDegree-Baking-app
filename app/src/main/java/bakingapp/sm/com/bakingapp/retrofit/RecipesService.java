package bakingapp.sm.com.bakingapp.retrofit;


import java.util.ArrayList;

import bakingapp.sm.com.bakingapp.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Aiman Nabeel on 04/06/2018.
 */

public interface RecipesService {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}
