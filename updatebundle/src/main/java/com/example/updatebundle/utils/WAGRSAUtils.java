package com.example.updatebundle.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by liulu on 2016/12/26.
 */
public class WAGRSAUtils {
    private static String RSA = "RSA";

    /**
     * 随机生成RSA密钥对(默认密钥长度为1024)
     *
     * @return
     */
    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(1024);
    }

    /**
     * 随机生成RSA密钥对
     *
     * @param keyLength 密钥长度，范围：512～2048<br>
     *                  一般1024
     * @return
     */
    public static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密
     *
     * @param privateKey 私钥
     * @return 加密数据
     */
    public static String encrypt(RSAPrivateKey privateKey, String data) {
        byte[] encryptByte = new byte[0];
        try {
            encryptByte = WAGRSAUtils.encryptData(data.getBytes(), privateKey);
            // 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密也是要用Base64先转换
            String encode = WAGBase64Utils.encode(encryptByte);
            return encode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 解密
     *
     * @param afterencrypt 加密过的数据
     * @param publicKey    公钥
     * @return 解密完数据
     */
    public static String decrypt(String afterencrypt, RSAPublicKey publicKey) {
        byte[] decryptByte = new byte[0];
        try {
            decryptByte = WAGRSAUtils.decryptData(WAGBase64Utils.decode(afterencrypt), publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decryptByte);
    }

    /**
     * 用私钥加密
     * 每次加密的字节数，不能超过密钥的长度值减去11
     *
     * @param data       需加密数据的byte数据
     * @param privateKey 私钥
     * @return 加密后的byte型数据
     */
    public static byte[] encryptData(byte[] data, RSAPrivateKey privateKey) throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空, 请设置");
        }
        try {
            Cipher cipher = Cipher.getInstance(RSA);
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            int key_len = privateKey.getModulus().bitLength() / 8;

            byte[][] arrays = splitArray(data, key_len);

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            for (byte[] arr : arrays) {
                byte[] subOutput = cipher.doFinal(arr);
                outStream.write(subOutput);
            }

            return outStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 需更新时得到url后的处理
     *
     * @param encrypt
     * @param publicKey
     */
    public static void decryptHandle(String encrypt, RSAPublicKey publicKey) {
        String decrypt = WAGRSAUtils.decrypt(encrypt, publicKey);
        if (TextUtils.isEmpty(decrypt)) {
            return;
        }
        String[] split = decrypt.split(",");
        String md5 = split[0];
        String url = split[1];
        WAGUpdateUtil.startUpdate(WAGUpdateUtil.getApplicationContext(), md5, url);
    }

    /**
     * 拆分数组
     */
    public static byte[][] splitArray(byte[] data, int len) {
        int x = data.length / len;
        int y = data.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        byte[][] arrays = new byte[x + z][];
        byte[] arr;
        for (int i = 0; i < x + z; i++) {
            if (i == x + z - 1 && y != 0) {
                arr = new byte[y];
                System.arraycopy(data, i * len, arr, 0, y);
            } else {
                arr = new byte[len];
                System.arraycopy(data, i * len, arr, 0, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }

    /**
     * 用公钥解密
     *
     * @param encryptedData 经过encryptedData()加密返回的byte数据
     * @param publicKey     公钥
     * @return
     */
    public static byte[] decryptData(byte[] encryptedData, RSAPublicKey publicKey) throws Exception {
        if (publicKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        try {

            int len = encryptedData.length;

            int key_len = publicKey.getModulus().bitLength() / 8;

            byte[][] arrays = splitArray(encryptedData, key_len);

            // 加密
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            for (byte[] arr : arrays) {
                byte[] subOutput = cipher.doFinal(arr);
                outStream.write(subOutput);
            }

            return outStream.toByteArray();


        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
     *
     * @param keyBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 得到公钥
     *
     * @param context
     * @return
     */
    public static RSAPublicKey getPublicKey(Context context) {
        InputStream inPublic = null;
        RSAPublicKey publicKey = null;
        try {
            inPublic = context.getResources().getAssets().open("rsa_public_key.pem");
            publicKey = WAGRSAUtils.loadPublicKey(inPublic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * 通过私钥byte[]将公钥还原，适用于RSA算法
     *
     * @param keyBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 得到私钥
     *
     * @param context
     * @return
     */
    public static RSAPrivateKey getPrivateKey(Context context) {
        InputStream inPrivate = null;
        RSAPrivateKey privateKey = null;
        try {
            inPrivate = context.getResources().getAssets().open("pkcs8_rsa_private_key.pem");
            privateKey = WAGRSAUtils.loadPrivateKey(inPrivate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     * 使用N、e值还原公钥
     *
     * @param modulus
     * @param publicExponent
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String modulus, String publicExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 使用N、d值还原私钥
     *
     * @param modulus
     * @param privateExponent
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String modulus, String privateExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = WAGBase64Utils.decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从字符串中加载私钥<br>
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.decode(privateKeyStr.getBytes(), Base64.DEFAULT);
            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param in 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKey(InputStream in) throws Exception {
        try {
            return loadPublicKey(readKey(in));
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }

    /**
     * 从文件中加载私钥
     *
     * @param in
     * @return 是否成功
     * @throws Exception
     */
    public static RSAPrivateKey loadPrivateKey(InputStream in) throws Exception {
        try {
            return loadPrivateKey(readKey(in));
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    /**
     * 读取密钥信息
     *
     * @param in
     * @return
     * @throws IOException
     */
    private static String readKey(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            if (readLine.charAt(0) == '-') {
                continue;
            } else {
                sb.append(readLine);
                sb.append('\r');
            }
        }

        return sb.toString();
    }

}
