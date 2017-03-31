package score.bwie.com.androidyuekaoa.utils;

import android.content.Context;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/28 19:48
 */
public class HttpUtils {

    public  static  void  getConnect(Context context,String  url,final  SuccessInterface successInterface){
        x.http().get(new RequestParams(url), new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                  successInterface.succecc(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }


   public  interface   SuccessInterface{
        public  void   succecc(String  res);
    }
}
