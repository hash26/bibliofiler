
package jp.hash26.bibliofiler.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import jp.hash26.bibliofiler.util.BFLog;
import android.database.CursorJoiner.Result;
import android.os.AsyncTask;
import android.util.Log;

public class BFHttpGetTask extends AsyncTask<Void, Void, Void> {

    // 設定事項
    private String request_encoding = "UTF-8";

    private String response_encoding = "UTF-8";

    private ResponseHandler<Void> _response_handler;

    private String http_err_msg = null;

    private String http_ret_msg = null;

    private String _requestUrl;

    private URI _requestUri;

    BFHttpGetTask(String requestUrl) {
        _requestUrl = requestUrl;
    }

    @Override
    protected void onPreExecute() {
        BFLog.debug("onPreExecute()");

        // レスポンスハンドラを生成
        _response_handler = new ResponseHandler<Void>() {

            @Override
            public Void handleResponse(HttpResponse response) throws ClientProtocolException,
                    IOException {
                BFLog.debug("ResponseCode=" + response.getStatusLine().getStatusCode());

                switch (response.getStatusLine().getStatusCode()) {
                    case HttpStatus.SC_OK:
                        BFLog.debug("レスポンス結果：レスポンスの取得に成功");

                        // レスポンスデータをエンコード済みの文字列として取得する。
                        // ※IOExceptionの可能性あり
                        BFHttpGetTask.this.http_ret_msg = EntityUtils.toString(
                                response.getEntity(), BFHttpGetTask.this.response_encoding);
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
    }

    @Override
    protected Void doInBackground(Void... params) {

        BFLog.debug("doInBackground()");

        try {
            _requestUri = new URI(_requestUrl);
        } catch (URISyntaxException e1) {
            // TODO 自動生成された catch ブロック
            e1.printStackTrace();
        }

        HttpClient httpClient = new DefaultHttpClient();
        httpClient.execute(_requestUri, _response_handler);
        HttpGet request = new HttpGet(_requestUri);
        HttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(request);
        } catch (Exception e) {
            Log.d("HttpSampleActivity", "Error Execute");
            Log.d("HttpSampleActivity", e.toString());
        }

        return null;
    }
}
