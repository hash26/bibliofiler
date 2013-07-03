
package jp.hash26.bibliofiler.http;

import jp.hash26.bibliofiler.util.BFLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BFJsonParser {

    public static void parseJson(String str) {

        try {
            JSONObject rootObject = new JSONObject(str);

            int count = rootObject.getInt("count");
            BFLog.debug("count=" + count);
            int page = rootObject.getInt("page");
            BFLog.debug("page=" + page);
            int first = rootObject.getInt("first");
            BFLog.debug("first=" + first);
            int last = rootObject.getInt("last");
            BFLog.debug("last=" + last);

            JSONArray itemsArray = rootObject.getJSONArray("Items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject items = itemsArray.getJSONObject(i);
                JSONObject item = items.getJSONObject("Item");
                String title = item.getString("title");
                BFLog.debug("title=" + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
