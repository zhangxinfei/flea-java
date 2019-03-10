package com.soecode.lyf.util;

import ch.qos.logback.core.util.SystemInfo;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wxl on 2018/6/1.
 */
public class RsaJsUtils {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCxiQSqD0q1pAUSGqrCLcMzsKR\n" +
            "wPBkiFW3WWZ97srAp3oRoupqL1nevJJRVPIZUF3JhTyV47pnjsCqWo4w0NqGVXhk\n" +
            "SQnIk4nj7S/w3I0gWUxqFzdP+xcPhA+mJxYq1HSMJ2qVQ6bQVT7/SgPSKWn++OEQ\n" +
            "WEFz5/uM/5STcJJlvQIDAQAB";

    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMLGJBKoPSrWkBRI\n" +
            "aqsItwzOwpHA8GSIVbdZZn3uysCnehGi6movWd68klFU8hlQXcmFPJXjumeOwKpa\n" +
            "jjDQ2oZVeGRJCciTiePtL/DcjSBZTGoXN0/7Fw+ED6YnFirUdIwnapVDptBVPv9K\n" +
            "A9Ipaf744RBYQXPn+4z/lJNwkmW9AgMBAAECgYAVumLlzezc7YivKpDzuYoqHJqq\n" +
            "BdsLbAYb6RY88DmgGk7Mzt7Vr6iX53NvLUnAty1vQLTMh9YQnWUy291G5tWpuoWx\n" +
            "CuMFG/i7krxcs1qGKRemgo2ID/zMWHZA8hkAGIIUPRvhZ+a6qKB5CAe4AhMEPnFb\n" +
            "Z49UUg0W8vd2RYKq5QJBAPyjw0bxwUv0pJ4/Nvi7pKCxKJDynoNqrn7w+uaFubxb\n" +
            "S4qXZHQj7hbkkRDZN9IEeRBykz8pW9tQM432Q8jN1msCQQDFXVh4aOo3hCzxlzXs\n" +
            "8xSUBYrpC9dWGSBNRCgSK5b2nhNvi6Kwe4u+BBf/OWDRWwHY9/c8XUETGQ2840in\n" +
            "Na53AkEAvTzljwSTZ5OuoyJyyMm1PPC2lCxH8+Q7JLbZGUddous3oG5aPmyTcO5w\n" +
            "gxBSMaxMCrfn983BEnv2wgWPLKCSVQJAKgaFZyZX8ThY5ZygI/Nix14ixRr7YF77\n" +
            "TNide7kCbUMdulvOaav1BqIShgsuvYWWcB/k6lqlF673COiQuOIqYwJBALB6lCON\n" +
            "Eq1BIy/x0AXuSDIp56wzNxWrFy5TOv7K2h4ubQ16MAsvPNPs/il7pqa1XbD4xrtv\n" +
            "nlOkcgsniNdbNzI=";
    /**
     * 解密算法
     */
    private static final String CIPHER_EN = "RSA";

    /**
     * 密钥长度
     */
    private static final Integer KEY_LENGTH = 1024;

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;


    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }


    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     * @throws InvalidKeySpecException
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        // 得到公钥
        byte[] keyBytes = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key key = keyFactory.generatePublic(x509EncodedKeySpec);
        // 加密数据，分段加密
        Cipher cipher = Cipher.getInstance(CIPHER_EN);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        int inputLength = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        while (inputLength - offset > 0) {
            if (inputLength - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offset, inputLength - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 私钥解密
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKeyFenduan(byte[] data, String privateKey) throws Exception {
        // 得到私钥
        byte[] keyBytes = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key key = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
        // 解密数据，分段解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        int inputLength = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        byte[] tmp;
        while (inputLength - offset > 0) {
            if (inputLength - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offset, inputLength - offset);
            }
//            out.write(cache, 0, cache.length);
            out.write(cache);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 解密
     * @param encodedData
     * @param
     * @return
     * @throws Exception
     */
    public static String  decryptByPrivateKeyData(String encodedData)throws Exception{
//        byte[] decodedData = decryptByPrivateKeyFenduan(decryptBASE64(encodedData), RsaJsUtils.PRIVATE_KEY);
        encodedData = encodedData.replaceAll("%2F","/");
        encodedData = encodedData.replaceAll("%2B","+");
        encodedData = encodedData.replaceAll("%3D","=");
        byte[] decodedData = decryptByPrivateKey(encodedData, RsaJsUtils.PRIVATE_KEY);
        String jsonStr = new String(decodedData);
        return jsonStr;
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data    加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data   加密数据
     * @param publicKey 公钥
     * @param sign   数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key)
            throws Exception {
        return decryptByPrivateKey(decryptBASE64(data),key);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
        return keyMap;
    }

    public static void main(String[] args) throws Exception{
        Map<String, Key> keyMap = RsaJsUtils.initKey();
        //"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDbQPesw6//kWjGGxwmt1cJWCIHUKIM4QDiOmNyYhtMzOloTunHNHBZTBSSKqXaHT5rGAWLrzSg+UFS9Awbt66ucB3vljMiNGBmdxuDdbBpM75OomXyw0R6zrwif2JUm3kLtJVERyiidqNFyzDLNbqfwRkhx7wl5Ms99+nkRu8liwIDAQAB";//
       String publicKey = RsaJsUtils.getPublicKey(keyMap);
        String privateKey = RsaJsUtils.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);

        System.err.println("公钥加密——私钥解密");
        String inputStr = "{\"admin_name\":\"张新飞\",\"admin_number\":\"111\"}";
//        byte[] encodedData = encryptByPublicKey(inputStr, publicKey);
//        String encoded = new String(encodedData);
//        String miyao1 = "ngW0Fu0AuksVfuzbZxtyjV%2F6TWDzd2MbQy9u8S8fyNc2xM4Kl97Oazp5bWDv%2FQ%2FKDswCh5xcGW8yDDc%2BDpdMD42lxUQIXps2%2BG%2FIxOiQxpLQpKBcDmaC2WCDFw0lZ46pdPeS0iCxDajAgJTs0oDp0RHcVaUlUXLcuTcuMESBwGw%3D";
        String miyao1 = "vfVgVNQ%2Fy236m%2FXf8BEu7IVA7wT8KOaVoCD3Vi43nbFGrFai%2BZws0MKpptgjB2wGZbJr9aUGh6WUUg5Fd2lJsB1xrDWhUHzokByHB40SJl7OKFmHhjdNt%2Bcx16bCkVYlMVzMhrWy%2BP4no1xzSPsBdAMrC1kopv1V6Rlnn0chYUw%3D";        String miyao2 = "ngW0Fu0AuksVfuzbZxtyjV/6TWDzd2MbQy9u8S8fyNc2xM4Kl97Oazp5bWDv/Q/KDswCh5xcGW8yDDc+DpdMD42lxUQIXps2+G/IxOiQxpLQpKBcDmaC2WCDFw0lZ46pdPeS0iCxDajAgJTs0oDp0RHcVaUlUXLcuTcuMESBwGw=";
        miyao1 = miyao1.replaceAll("%2F","/");
        miyao1 = miyao1.replaceAll("%2B","+");
        miyao1 = miyao1.replaceAll("%3D","=");
        System.out.println("前端加密直接粘贴过来："+miyao2);
        System.out.println("前端加密后传递过来的，去除%2F后："+miyao1);
//        System.out.println("加密后："+encoded);

        byte[] encodedData = decryptBASE64(miyao1);

        System.out.println("加密后字节的长度为："+encodedData.length);
        String ee = new String(encodedData);

        byte[] decodedData = decryptByPrivateKey(encodedData, privateKey);
//        byte[] decodedData = decryptByPrivateKeyFenduan(encodedData,privateKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);



     /*   System.err.println("私钥加密——公钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();
        byte[] encodedData1 = .encryptByPrivateKey(data, privateKey);
        byte[] decodedData1= .decryptByPublicKey(encodedData, publicKey);
        String outputStr1 = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        (inputStr, outputStr);
        System.err.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = .sign(encodedData, privateKey);
        System.err.println("签名:" + sign);
        // 验证签名
        boolean status = RSACoder.verify(encodedData, publicKey, sign);
        System.err.println("状态:" + status);
        assertTrue(status);*/
    }
}
