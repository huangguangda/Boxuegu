package cn.edu.gdmec.android.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    public static String DB_NAME = "bxg.db";
    public static final String U_USER_INFO = "userInfo";
    public static final String U_VIDEO_PLAY_LIST="videoplaylist";

    /*public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/
    public SQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 当这个SQLiteOpenHelper的子类类被实例化时会创建指定名的数据库，在onCreate中创建个人信息表
         * **/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USER_INFO + "( "
                + "_id  INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR, "
                + "nickName VARCHAR, "
                + "sex VARCHAR, "
                + "signature VARCHAR, "
                + "qq VARCHAR "
                + ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_VIDEO_PLAY_LIST + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR, " //用户名
                + "chapterId INT, " //章节
                + "videoId int, "       //小节id
                + "videoPath VARCHAR,"  //视频地址
                + "title VARCHAR,"//视频章节名称
                + "secondTitle VARCHAR"//视频名单
                + ")");
    }

    /**
     * 当数据库版本号增加才会调用此方法
     **/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + U_USER_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + U_VIDEO_PLAY_LIST);
        onCreate(db);
    }
}