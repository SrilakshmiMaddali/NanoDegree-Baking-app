package bakingapp.sm.com.bakingapp.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import bakingapp.sm.com.bakingapp.widget.ReadySetBakeWidgetProvider;

public class BakeWidgetService extends IntentService {

    public static String ACTION_BAKING_INGREDIENTS =
            "FROM_ACTIVITY_INGREDIENTS_LIST";

    public BakeWidgetService() {
        super("UpdateBakingService");
    }

    public static void startBakingIngredientsService(Context context, ArrayList<String> ingredientsList) {
        Intent intent = new Intent(context, BakeWidgetService.class);
        intent.putExtra(ACTION_BAKING_INGREDIENTS, ingredientsList);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<String> ingredientsList = intent.getExtras().getStringArrayList(ACTION_BAKING_INGREDIENTS);
                handleActionUpdateBakingIngredientsWidget(ingredientsList);
        }

    }

    private void handleActionUpdateBakingIngredientsWidget(ArrayList<String> ingredientsList) {
        Intent intent = new Intent(this, ReadySetBakeWidgetProvider.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra(ACTION_BAKING_INGREDIENTS, ingredientsList);
        sendBroadcast(intent);
    }
}
