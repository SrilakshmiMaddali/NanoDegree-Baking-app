package bakingapp.sm.com.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bakingapp.sm.com.bakingapp.R;
import bakingapp.sm.com.bakingapp.Recipe;
import bakingapp.sm.com.bakingapp.Step;

public class RecipesDetailAdapter extends RecyclerView.Adapter<RecipesDetailAdapter.RecipeDetailViewHolder> {

    final private RecipeStepClickListener mListener;
    List<Step> stepList;
    private String recipeName;

    //Interface for RecipeDetailActivity for clicking on Recipe Step Detail
    public interface RecipeStepClickListener {
        void onRecipeStepDetailItemClick(List<Step> stepsOut, int itemSelectedIndex, String recipeName);
    }

    public RecipesDetailAdapter(RecipeStepClickListener stepOnClickListener) {
        mListener = stepOnClickListener;
    }

    @Override
    public RecipeDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int listItemLayout = R.layout.recipe_detail_card_items;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(listItemLayout, parent, false);
        RecipeDetailViewHolder viewHolder = new RecipeDetailViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeDetailViewHolder holder, int position) {
        holder.steptDescripCard.setText(stepList.get(position).getId()
                + ". " + stepList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return stepList !=null ? stepList.size():0 ;
    }

    public void recipeStepData (List<Recipe> recipeList, Context context) {
        stepList = recipeList.get(0).getSteps();
        recipeName = recipeList.get(0).getName();
        notifyDataSetChanged();
    }

    class RecipeDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView steptDescripCard;

        public RecipeDetailViewHolder(View itemView) {
            super(itemView);
            steptDescripCard = (TextView) itemView.findViewById(R.id.stepDescriptionCard);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPposition = getAdapterPosition();
            mListener.onRecipeStepDetailItemClick(stepList, clickedPposition, recipeName);

        }
    }
}
