
package encode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESTest {

    private static  String PATH = "F:\\testEnc";
    private static  String File = "\\8.jpg";
    private static  String SECRET_KEY = "bjyfuvgjhffcjtufuyjf=";

    /**
     * 加密
     * @throws Exception
     */
    private void encode() throws Exception {

        // AES加密，创建AES的Key生产者
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 利用用户密码作为随机数初始化出128位的key生产者
        kgen.init(128, new SecureRandom(SECRET_KEY.getBytes()));
        // 加密没关系，SecureRandom是生成安全随机数序列，SECRET_KEY.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有SECRET_KEY就行
        SecretKey secretKey = kgen.generateKey();
        //根据用户密码，生成一个密钥 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
        byte[] enCodeFormat = secretKey.getEncoded();
        // 转换为AES专用密钥
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        //初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // 获取原视频文件
        BufferedInputStream bisOld = new BufferedInputStream(new FileInputStream(new File(PATH + File)));
        // 输出加密后的流文件
        BufferedOutputStream bosNew = new BufferedOutputStream(
                new FileOutputStream(new File(PATH + "\\encode" + File)));
        // 加密流
        CipherInputStream cis = new CipherInputStream(bisOld, cipher);

        byte[] buffer = new byte[1024];
        int len;
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
    private void decode() throws Exception {
        // AES加密，创建AES的Key生产者
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 利用用户密码作为随机数初始化出128位的key生产者
        kgen.init(128, new SecureRandom(SECRET_KEY.getBytes()));
        // 加密没关系，SecureRandom是生成安全随机数序列，SECRET_KEY.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有SECRET_KEY就行
        SecretKey secretKey = kgen.generateKey();
        //根据用户密码，生成一个密钥 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
        byte[] enCodeFormat = secretKey.getEncoded();
        // 转换为AES专用密钥
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        //初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.DECRYPT_MODE, key);

        // 获取加密文件
        BufferedInputStream bisOld = new BufferedInputStream(new FileInputStream(new File(PATH + "\\encode" + File)));
        // 输出解密后流文件
        BufferedOutputStream bosNew = new BufferedOutputStream(
                new FileOutputStream(new File(PATH + "\\decode" + File)));
        // 加密流
        CipherOutputStream cos = new CipherOutputStream(bosNew, cipher);

        byte[] buffer = new byte[1024];
        int len;

        while ((len = bisOld.read(buffer)) > 0) {
            cos.write(buffer, 0, len);
        }

        bosNew.flush();
        cos.close();
        bosNew.close();

        bisOld.close();
    }

    public static void main(String[] args) throws Exception {
        AESTest t = new AESTest();
        //t.encode();
        // 解密
        t.decode();
    }

}
