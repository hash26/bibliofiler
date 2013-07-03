
package jp.hash26.bibliofiler.http;

import java.io.IOException;

import jp.hash26.bibliofiler.util.BFLog;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class BFHttpGetTask extends AsyncTask<Void, Void, Void> {

    // 設定事項
    private String request_encoding = "UTF-8";

    private String response_encoding = "UTF-8";

    private String http_err_msg = null;

    private String http_ret_msg = null;

    private HttpGet _request;

    private Handler _ui_handler;

    private Activity _parent_activity;

    BFHttpGetTask(String requestUrl) {
        _request = new HttpGet(requestUrl);
    }

    // 生成時
    public BFHttpGetTask(Activity parent_activity, String requestUrl, Handler ui_handler) {
        // 初期化
        _parent_activity = parent_activity;
        _request = new HttpGet(requestUrl);
        _ui_handler = ui_handler;

    }

    private ResponseHandler<Void> _responseHandler = new ResponseHandler<Void>() {

        @Override
        public Void handleResponse(HttpResponse response) throws ClientProtocolException,
                IOException {
            BFLog.debug("ResponseCode=" + response.getStatusLine().getStatusCode());

            switch (response.getStatusLine().getStatusCode()) {
                case HttpStatus.SC_OK:
                    BFLog.debug("レスポンス結果：レスポンスの取得に成功");

                    // レスポンスデータをエンコード済みの文字列として取得する。
                    // IOExceptionの可能性あり
                    BFHttpGetTask.this.http_ret_msg = EntityUtils.toString(response.getEntity(),
                            BFHttpGetTask.this.response_encoding);
                    break;

                case HttpStatus.SC_NOT_FOUND:
                    BFLog.debug("レスポンス結果：存在しない");
                    BFHttpGetTask.this.http_err_msg = "404 Not Found";
                    break;

                default:
                    BFLog.debug("レスポンス結果：通信エラー");
                    BFHttpGetTask.this.http_err_msg = "通信エラーが発生";
            }
            return null;
        }
    };

    @Override
    protected void onPreExecute() {
        BFLog.debug("onPreExecute()");
    }

    @Override
    protected Void doInBackground(Void... params) {

        BFLog.debug("doInBackground()");

        HttpClient httpClient = new DefaultHttpClient();
        try {
            httpClient.execute(_request, _responseHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        Message message = new Message();
        Bundle bundle = new Bundle();

        if (http_err_msg != null) {
            // エラー発生時
            bundle.putBoolean("http_get_success", false);
            bundle.putString("http_response", http_err_msg);
        } else {
            // 通信成功時
            bundle.putBoolean("http_get_success", true);
            bundle.putString("http_response", http_ret_msg);
        }
        message.setData(bundle);
        _ui_handler.sendMessage(message);
    }

}
