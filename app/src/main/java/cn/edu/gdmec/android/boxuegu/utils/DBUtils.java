package cn.edu.gdmec.android.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.sqlite.SQLiteHelper;

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;

    /**
     * 构造方法，只有当类被实例化时候调用
     * 实例化SQLiteHelper类，从中得到一个课读写的数据库
     **/
    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 得到这个类的实例
     **/
    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }

    /**
     * 保存个人资料信息
     **/
    public void saveUserInfo(UserBean bean) {
        ContentValues cv = new ContentValues();
        cv.put("userName", bean.userName);
        cv.put("nickName", bean.nickName);
        cv.put("sex", bean.sex);
        cv.put("signature", bean.signature);
        cv.put("qq",bean.qq);
        //Convenience method for inserting a row into the database.
        //注意，我们是从数据库使用插入方法，传入表名和数据集完成插入
        db.insert(SQLiteHelper.U_USER_INFO, null, cv);
    }

    /**
     * 获取个人资料信息
     **/
    public UserBean getUserInfo(String userName) {
        String sql = "SELECT * FROM " + SQLiteHelper.U_USER_INFO + " WHERE userName=?";
        //?和下面数组内元素会逐个替换，可以多条件查询=?and =?
        //You may include ?s in where clause in the query, which will be replaced by the values from selectionArgs.
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        UserBean bean = null;
        //Move the cursor to the next row.
        while (cursor.moveToNext()) {
            bean = new UserBean();
            //根据列索引获取对应的数值，因为这里查询结果只有一个，我们也不需要对模型UserBean进行修改，
            //直接将对应用户名的所有数据从表中动态赋值给bean
            bean.userName = cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName = cursor.getString(cursor.getColumnIndex("nickName"));
            bean.sex = cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature = cursor.getString(cursor.getColumnIndex("signature"));
            bean.qq = cursor.getString(cursor.getColumnIndex("qq"));
        }
        cursor.close();
        return bean;
    }

    /**
     * 修改个人资料信息,这里的key指代表字段，value表示数值
     **/
    public void updateUserInfo(String key, String value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        //Convenience method for updating rows in the database.
        db.update(SQLiteHelper.U_USER_INFO, cv, "userName=?", new String[]
                {userName});
    }
}
