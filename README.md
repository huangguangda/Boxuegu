# Boxuegu
AndroidStudio3.0.1教育系统APP

## Github 欢迎 Star、Fork

### 如果喜欢，那就点个赞吧！❤️ 

## 内容简介

涵盖Android基础的全部知识，不仅是对Android基础的巩固与提高，更是对项目经验的积累。

从项目的需求分析、产品设计、产品开发一直到项目上线，讲解了项目开发的全过程。

## 项目功能模块

注册与登录模块、我的模块、个人资料模块、习题模块、课程模块5个模块

## 应用图标&欢迎界面

<h3>应用图标</h3>
<p>自适应图标为 Android O 新增的一种全新应用图标样式，具体可以参照</p>
<blockquote>Google 设计师兼开发者 <a href="https://medium.com/@crafty" rel="nofollow noreferrer">Nick Butcher</a> 的以下三篇文章：<br><a href="https://medium.com/google-design/understanding-android-adaptive-icons-cee8a9de93e2" rel="nofollow noreferrer">Understanding Android Adaptive Icons</a><br><a href="https://medium.com/google-design/designing-adaptive-icons-515af294c783" rel="nofollow noreferrer">Designing Adaptive Icons</a><br><a href="https://medium.com/google-developers/implementing-adaptive-icons-1e4d1795470e" rel="nofollow noreferrer">Implementing Adaptive Icons</a>
</blockquote>
<blockquote>英文不好的朋友可以直接看国内译者 <a href="https://sspai.com/user/720580/updates" rel="nofollow noreferrer">HarveyJanson</a> 的文章：<br><a href="https://sspai.com/post/40230" rel="nofollow noreferrer">Android O 自适应图标的意义何在？Google 设计师给你答案 | 科普</a><br><a href="https://sspai.com/post/40223" rel="nofollow noreferrer">设计自适应图标</a><br><a href="https://sspai.com/post/40240" rel="nofollow noreferrer">实现自适应图标</a>
</blockquote>
<p>但最好还是多看官方文档，而且看英文版的，因为新的内容经常还未被汉化，于是会出现同一网址不同语言显示的完全不是同个东西的窘境<br><span class="img-wrap"><img data-src="/img/bV0XIf?w=1920&amp;h=1007" src="https://cdn.segmentfault.com/v-5c4e90ec/global/img/squares.svg" alt="中文与英文" title="中文与英文"></span></p>
<p>本人参照了 <a href="https://developer.android.com/guide/practices/ui_guidelines/icon_design_adaptive.html#studio" rel="nofollow noreferrer">Adaptive Icons</a> ，<a href="https://developer.android.com/studio/write/image-asset-studio.html" rel="nofollow noreferrer">Create App Icons with Image Asset Studio</a>。</p>
<p>首先把设计好的前景图和背景图放置在项目中，本人将这两张图片放置在了 drawable 目录下，然后在 res 目录 右键-&gt; New -&gt; Image Asset<br><span class="img-wrap"><img data-src="/img/bV0XML?w=741&amp;h=827" src="https://cdn.segmentfault.com/v-5c4e90ec/global/img/squares.svg" alt="Image Asset" title="Image Asset"></span></p>
<p>然后在 Icon Type中选择 Launcher Icons (Adaptive and Legacy)，在下方的 Foreground Layer 选项卡中的 Asset Type 选中 Image ，在 Path 选择项目中的图片路径，同理设置背景图片，为了兼容低版本的系统，Image Asset Studio 还会生成适用于低版本的图片，然后根据各自需求调整缩放等参数即完成应用图标的设置。</p>
<p><span class="img-wrap"><img data-src="/img/bV0XQr?w=1132&amp;h=814" src="https://cdn.segmentfault.com/v-5c4e90ec/global/img/squares.svg" alt="Foreground Layer" title="Foreground Layer"></span></p>
<p><span class="img-wrap"><img data-src="/img/bV0XQG?w=1132&amp;h=814" src="https://cdn.segmentfault.com/v-5c4e90ec/global/img/squares.svg" alt="Background Layer" title="Background Layer"></span></p>
<p><span class="img-wrap"><img data-src="/img/bV0XQP?w=1132&amp;h=814" src="https://cdn.segmentfault.com/v-5c4e90ec/global/img/squares.svg" alt="Legacy" title="Legacy"></span></p>
<p><span class="img-wrap"><img data-src="/img/bV0XQT?w=1132&amp;h=814" src="https://cdn.segmentfault.com/v-5c4e90ec/global/img/squares.svg" alt="完成" title="完成"></span></p>
<hr>
<h3>欢迎界面</h3>
<p>首先同理把欢迎界面的图片导入到drawable目录下，在导入时 Android Studio 会提示如下</p>
<p><span class="img-wrap"><img data-src="/img/bV0XSt?w=415&amp;h=428" src="https://cdn.segmentfault.com/v-5c4e90ec/global/img/squares.svg" alt="drawable" title="drawable"></span></p>
<p>具体本人尚未弄明白，待理解后会重新补全本部分内容，在此本人选了第一个</p>
<p>然后创建一个包名为activity的包，在activity下创建SplashActivity</p>
<p><span class="img-wrap"><img data-src="/img/bV0XUk?w=800&amp;h=786" src="https://cdn.segmentfault.com/v-5c4e90ec/global/img/squares.svg" alt="SplashActivity" title="SplashActivity"></span></p>
<p>此时会发现 R 报红，只需要将光标锁定到R之后即会提示需要导入R包，如未提示，则手动敲击 Alt+Enter 选中 import class 即可导入</p>
<p><span class="img-wrap"><img data-src="/img/bV0XV0?w=471&amp;h=390" src="https://cdn.segmentfault.com/v-5c4e90ec/global/img/squares.svg" alt="R报红" title="R报红"></span></p>
<p>再而处理代码部分</p>
<h4>SplashActivity</h4>
<pre><code class="java">package cn.edu.lt.android.boxueguapp.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.lt.android.boxueguapp.MainActivity;
import cn.edu.lt.android.boxueguapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置此界面为
        // 竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        TextView tv_version = (TextView)findViewById(R.id.tv_version);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            tv_version.setText("V" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("V");
        }

        //利用timer让此界面延迟3秒后跳转，timer有一个线程，该线程不断执行task
        Timer timer = new Timer();
        //TimerTask实现runnable接口，TimerTask类表示在一个指定时间内执行的task
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {//发送intent实现页面跳转，第一个参数为当前页面的context，第二个参数为要跳转的主页
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();//跳转后关闭当前欢迎页面
            }
        };
        timer.schedule(timerTask,3000);//调度执行timerTask，第二个参数传入延迟时间（毫秒）

    }
}
</code></pre>
<p>创建 Activity 时同时自动创建了一个布局文件，首先修改为 RelativeLayout 布局</p>
<p>具体代码如下：</p>
<h4>activity_splash.xml</h4>
<pre><code class="xml">&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width = "match_parent"
                android:layout_height="match_parent"
                android:background="@drawable/launch_bg"&gt;
    &lt;TextView
        android:id="@+id/tv_version"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="@android:color/white"
        android:textSize="14sp"
        android:layout_centerInParent="true"/&gt;&lt;!--显示版本号--&gt;

&lt;/RelativeLayout&gt;
</code></pre>
<p>最后在清单文件 AndroidManifest.xml 中配置欢迎界面，将应用入口修改为欢迎界面，再去除 ActionBar 效果</p>
<h4>AndroidManifest.xml</h4>
<pre><code class="xml">&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;manifest xmlns:android="http://schemas.android.com/apk/res/android"
package="cn.edu.lt.android.boxueguapp"&gt;

&lt;application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:theme="@style/Theme.AppCompat.NoActionBar"&gt;
    &lt;!--原为android:theme="@style/AppTheme"--&gt;&lt;!--去除ActionBar标题栏--&gt;
    &lt;activity android:name=".MainActivity"&gt;
    &lt;/activity&gt;
    &lt;activity android:name=".activity.SplashActivity"&gt;
        &lt;intent-filter&gt;
            &lt;action android:name="android.intent.action.MAIN" /&gt;

            &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
        &lt;/intent-filter&gt;
    &lt;/activity&gt;
&lt;/application&gt;

&lt;/manifest&gt;</code></pre>
<hr>
