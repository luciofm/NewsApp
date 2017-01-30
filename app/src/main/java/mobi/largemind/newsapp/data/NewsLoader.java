package mobi.largemind.newsapp.data;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luciofm on 28/01/17.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private final String url = "http://content.guardianapis.com/search?q=android&api-key=test&page-size=50&show-tags=contributor";

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        String json = Network.makeHttpRequest(url);
        List<News> news = parseResponse(json);
        return news;
    }

    private List<News> parseResponse(String json) {
        if (TextUtils.isEmpty(json))
            return null;

        List<News> news = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(json);
            JSONArray array = root.getJSONObject("response").getJSONArray("results");

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String section = obj.getString("sectionName");
                String title = obj.getString("webTitle");
                String url = obj.getString("webUrl");
                String date = null;

                SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat localdate = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date utcDate = utcFormat.parse(obj.getString("webPublicationDate"));
                    date = localdate.format(utcDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String author = "";
                JSONArray tagArray = obj.getJSONArray("tags");
                if (tagArray.length() > 0) {
                    JSONObject tagObj = tagArray.getJSONObject(0);
                    author = tagObj.getString("webTitle");
                }

                news.add(new News(section, title, url, author, date));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return news;
    }
}
