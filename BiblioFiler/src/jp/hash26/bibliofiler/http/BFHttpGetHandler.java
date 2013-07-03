
package jp.hash26.bibliofiler.http;

import android.os.Handler;
import android.os.Message;

/**
 * HTTP通信のPOSTタスク完了時に，通信の成否に応じて，受信した通信内容をUI上で取り扱うための抽象クラス。
 * http://d.hatena.ne.jp/language_and_engineering/20111121/p1
 */
public abstract class BFHttpGetHandler extends Handler {

    // このメソッドは隠ぺいし，Messageなどの低レベルオブジェクトを
    // 直接扱わないでもよいようにさせる
    public void handleMessage(Message msg) {
        boolean isGetSuccess = msg.getData().getBoolean("http_get_success");
        String http_response = msg.getData().get("http_response").toString();

        if (isGetSuccess) {
            onPostSuccess(http_response);
        } else {
            onPostFailed(http_response);
        }
    }

    // 下記をoverrideさせずに抽象化した理由は，本クラス指定時に
    // 「実装されていないメソッドの追加」でメソッドスタブを楽に自動生成させるため。
    // また，異常系の処理フローも真剣にコーディングさせるため。

    // 通信成功時の処理を記述させる。
    public abstract void onPostSuccess(String response);

    // 通信失敗時の処理を記述させる
    public abstract void onPostFailed(String response);

}
