package score.bwie.com.androidyuekaoa.application;

import android.app.Application;

import org.xutils.x;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/28 19:52
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
