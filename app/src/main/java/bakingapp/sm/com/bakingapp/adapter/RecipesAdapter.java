package bakingapp.sm.com.bakingapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bakingapp.sm.com.bakingapp.R;
import bakingapp.sm.com.bakingapp.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeCardHolder> {

    private ArrayList<Recipe> mRecipeList;
    private Context mContext;
    final private RecipeListItemClickListener mListener;

    public interface RecipeListItemClickListener {
        void onRecipeListItemClick(Recipe selectedItemIndex);
    }

    public RecipesAdapter(RecipeListItemClickListener rOnClickListener) {
        mListener = rOnClickListener;
    }

    public void setRecipeList(ArrayList<Recipe> recipesIn, Context context) {
        mRecipeList = recipesIn;
        mContext =context;
        notifyDataSetChanged();
    }

    @Override
    public RecipesAdapter.RecipeCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.recipe_card_items;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent,  false);
        RecipesAdapter.RecipeCardHolder viewHolder = new RecipesAdapter.RecipeCardHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.RecipeCardHolder holder, int position) {

        holder.textView.setText(mRecipeList.get(position).getName());
        String imageUrl= mRecipeList.get(position).getImage();

        if (imageUrl!="") {
            Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
            Picasso.with(mContext).load(builtUri).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return (mRecipeList == null) ? 0 : mRecipeList.size();
    }


    class RecipeCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public ImageView imageView;
        public TextView textView;

        public RecipeCardHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedRecipe = getAdapterPosition();
            mListener.onRecipeListItemClick(mRecipeList.get(clickedRecipe));
        }
    }
}
