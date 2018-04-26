package cn.edu.gdmec.android.boxuegu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jack on 2018/3/27.
 * 创建一个md5()方法
 * 过 MessageDigest 的 getInstance()方法
 * 获取数据加密对象 digest，然后通过该对象的 digest()方法对密码进行加密
 * 由于注册登录涉及密码
 * 我们需要对用户的密码进行 MD5 算法加密
 * MD5 的全称是 Message-Digest Algorithm 5（信息--摘要算法）
 * MD5 算法简单来说就是把任意长度的字符串变换成固定长度（通常是128位）的16进制字符串
 * 且此算法不可逆
 */

public class MD5Utils {
    // md5 加密的算法
    public static String md5(String text){
        MessageDigest digest = null;

        try {
            //获取数据指纹对象
            digest = MessageDigest.getInstance("md5");
            //字节数组
            byte[] result = digest.digest(text.getBytes());
            //16进制转换
            StringBuffer sb = new StringBuffer();
            //获取所有字节进行转换
            for (byte b: result){
                //使用『与算法』，java使用unicode字符，所以每个字符占位两个
                // 需要与两位16进制最大值进行与运算，获取number值
                int number = b & 0xff;
                //number值转换字符串
                String hex = Integer.toHexString(number);
                if (hex.length() == 1){
                    //若转换后的字符长度等于1则进行字符串拼接
                    sb.append("0"+hex);
                }else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //发送异常return空字符串
            return "";
        }
    }
}
