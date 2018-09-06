
package encode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

public class FileEncAndDec {

    Key key;
    //DES加密
    public FileEncAndDec(String str) {
        getKey(str);// 生成密匙
    }

    /**
     * 根据参数生成KEY
     */
    public void getKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom(strKey.getBytes()));
            this.key = _generator.generateKey();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }

    //插入一段文件干扰
    public static final String PATH = "F:\\testEnc";

    /**
     * 加密
     * @throws Exception
     */
    public void encode() throws Exception {

        // 获取密钥文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(PATH + "\\password")));

        byte[] passwd = new byte[bis.available()];
        bis.read(passwd);
        bis.close();
        //DES加密
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        // 获取原视频文件
        BufferedInputStream bisOld = new BufferedInputStream(new FileInputStream(new File(PATH + "\\asd.mp4")));
        // 输出加密后的流文件
        BufferedOutputStream bosNew = new BufferedOutputStream(
                new FileOutputStream(new File(PATH + "\\encode\\asd_new.mp4")));
        //DES加密
        CipherInputStream cis = new CipherInputStream(bisOld, cipher);
        // 写入密钥
        bosNew.write(passwd);

        byte[] buffer = new byte[1024];
        int len = 0;
        // 加密文件
        while ((len = cis.read(buffer)) > 0) {
            bosNew.write(buffer, 0, len);
        }

        bosNew.flush();
        cis.close();
        bosNew.close();

        bisOld.close();

    }

    /**
     * 解密
     * @throws Exception
     */
    public void decode() throws Exception {
        //DES加密
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        // 获取密钥文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(PATH + "\\password")));
        int passwdLen = bis.available();
        byte[] passwd = new byte[passwdLen];
        bis.close();
        // 获取加密文件
        BufferedInputStream bisOld = new BufferedInputStream(new FileInputStream(new File(PATH + "\\encode\\asd_new.mp4")));
        // 输出解密后流文件
        BufferedOutputStream bosNew = new BufferedOutputStream(
                new FileOutputStream(new File(PATH + "\\decode\\asd.mp4")));
        //DES加密
        CipherOutputStream cos = new CipherOutputStream(bosNew, cipher);
        // 获取密钥
        bisOld.read(passwd);
        passwd = null;

        byte[] buffer = new byte[1024];
        int len = 0;

        while ((len = bisOld.read(buffer)) > 0) {
            cos.write(buffer, 0, len);
        }

        bosNew.flush();
        cos.close();
        bosNew.close();

        bisOld.close();
    }

    public static void main(String[] args) throws Exception {
        FileEncAndDec t = new FileEncAndDec("123456");
        // 加密
        t.encode();
        // 解密
        t.decode();
    }

}
