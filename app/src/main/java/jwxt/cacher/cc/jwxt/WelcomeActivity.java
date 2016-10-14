package jwxt.cacher.cc.jwxt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

/**
 * Created by ZN on 2016/10/3.
 */

public class WelcomeActivity extends Activity {
    public static int screenWidth;
    public static int screenHeight;
    public static int statusBarHeight;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //计算屏幕大小
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WelcomeActivity.screenHeight = dm.heightPixels;
        WelcomeActivity.screenWidth = dm.widthPixels;
        System.out.println(WelcomeActivity.screenWidth);
        System.out.println(WelcomeActivity.screenHeight);
        activity=this;

        //在界面上显示一个提示框
//        Toast.makeText(WelcomeActivity.this, "欢迎使用！", Toast.LENGTH_LONG).show();

        final Runnable callback = new Runnable() {
            //一段被运行的代码
            @Override
            public void run() {
                //跳转到新的页面
                Intent intent = new Intent(WelcomeActivity.this, SZSDLoginActivity.class);
                startActivity(intent);

                //获取状态栏高度
                Rect rect=new Rect();
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                WelcomeActivity.statusBarHeight=rect.top;

                //增加一个页面跳转的动画
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                //销毁当前的activity
                finish();
            }
        };
        //实例化消息传递者handler
        final Handler handler = new Handler();
        //在界面停留5秒钟
        Thread thread = new Thread() {
            //当线程运行的时候，执行的操作
            @Override
            public void run() {
                //在子线程里停留三秒钟
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //把异常信息打印在控制台上
                    e.printStackTrace();
                }
                handler.post(callback);
            }
        };
        //开启新的线程
        thread.start();
    }
}