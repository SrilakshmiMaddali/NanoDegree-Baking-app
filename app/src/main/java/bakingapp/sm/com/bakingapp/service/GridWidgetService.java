package bakingapp.sm.com.bakingapp.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import bakingapp.sm.com.bakingapp.R;
import bakingapp.sm.com.bakingapp.userInterface.MainActivity;

public class GridWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        Context mContext = null;
        ArrayList<String> ingredientsArrayList = new ArrayList<>();

        public GridRemoteViewsFactory(Context applicationContext, Intent intent) {
            mContext = applicationContext;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            //Retrieving Data
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String json = preferences.getString(MainActivity.SHARED_PREFS_KEY, "");
            if (!json.equals("")) {
                Gson gson = new Gson();
                ingredientsArrayList = gson.fromJson(json, new TypeToken<ArrayList<String>>() {
                }.getType());
            }

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return ingredientsArrayList.size();
        }

        //Setting widget_gridview_item here
        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_gridview_item);
            views.setTextViewText(R.id.widget_gridview_item, ingredientsArrayList.get(position));

            //Setting up FillIn Intent here for the gridview - To fill in the PendingIntent Template
            Intent fillInIntent = new Intent();
            views.setOnClickFillInIntent(R.id.widget_gridview_item, fillInIntent);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

}
