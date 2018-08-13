package com.mvp.demo;



import android.util.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class RSAUtil {



    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAUtil(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }


    public byte[] encrypt(byte[] data) throws Exception {
        return encrypt(this.privateKey, data);
    }

    public byte[] dencrypt(byte[] data) throws Exception {
        return decrypt(this.publicKey, data);
    }



    public static PrivateKey loadPrivateKey(String alias, String path, String password) throws Exception {
        FileInputStream ksfis = null;
        try {
            KeyStore ks = KeyStore.getInstance("pkcs12");

            ksfis = new FileInputStream(path);
            char[] storePwd = password.toCharArray();
            char[] keyPwd = password.toCharArray();

            ks.load(ksfis, storePwd);

            // 从密钥仓库得到私钥
            return (PrivateKey) ks.getKey(alias, keyPwd);
        } finally {
            if (ksfis != null)
                ksfis.close();
        }
    }

    public static PublicKey loadPublicKey(String alias, String path, String password) throws Exception {
        FileInputStream ksfis = null;
        try {
            KeyStore ks = KeyStore.getInstance("pkcs12");

            ksfis = new FileInputStream(path);
            char[] storePwd = password.toCharArray();

            ks.load(ksfis, storePwd);

            // 从密钥仓库得到私钥
            return ks.getCertificate(alias).getPublicKey();
        } finally {
            if (ksfis != null)
                ksfis.close();
        }
    }

    /**
     * 生成密钥对
     *
     * @return KeyPair
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception {

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        final int KEY_SIZE = 1024;
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        KeyPair keyPair = keyPairGen.genKeyPair();
        return keyPair;
    }

    /**
     * 生成公钥
     *
     * @param modulus
     * @param publicExponent
     * @return RSAPublicKey
     * @throws Exception
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            throw ex;
        }

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw ex;
        }
    }

    public static PublicKey getPublickey(String CRTfile) {
        try {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            FileInputStream bais = new FileInputStream(CRTfile);
            X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(bais);
            return Cert.getPublicKey();
        } catch (Exception e) {

        }
        return null;
    }

/*    public static void main(String[] args) throws Exception {
        System.out.println(RSAUtil.getPublicKeyString((RSAPublicKey) getPublickey("C:\\temp111\\cloud_rsa_public.cer")));
    }*/

    /**
     * 生成私钥
     *
     * @param modulus
     * @param privateExponent
     * @return RSAPrivateKey
     * @throws Exception
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            throw ex;
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw ex;
        }
    }

    /**
     * 加密
     *
     * @param key  加密的密钥
     * @param data 待加密的明文数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(Key key, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            int blockSize = cipher.getBlockSize();//获得加密块大小，如：加密前数据为128个byte，而key_size=1024 加密块大小为 127 byte,加密后为128个byte;因此共有2个加密块，第一个127 byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);//获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到 ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了 OutputSize所以只好用dofinal方法。
                i++;
            }
            return raw;
        } catch (Exception e) {

            throw e;
        }
    }

    /**
     * 解密
     *
     * @param key 解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    public static byte[] decrypt(Key key, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;

            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        } catch (Exception e) {

            throw e;
        }
    }

    public static String getPublicKeyString(RSAPublicKey key) throws Exception {
        String exponent = RSAUtil.byte2hex(key.getPublicExponent().toByteArray());
        String modulus = RSAUtil.byte2hex(key.getModulus().toByteArray());
        StringBuffer sb = new StringBuffer();
        sb.append(modulus).append(" ").append(exponent);
        return sb.toString();
    }

    public static String getPrivateKeyString(RSAPrivateKey key) throws Exception {
        String exponent = RSAUtil.byte2hex(key.getPrivateExponent().toByteArray());
        String modulus = RSAUtil.byte2hex(key.getModulus().toByteArray());
        StringBuffer sb = new StringBuffer();
        sb.append(modulus).append(" ").append(exponent);
        return sb.toString();
    }

    public static RSAPublicKey getPublicKey(String keyString) throws Exception {
        String[] part = keyString.split(" ");
        if (part.length != 2)
            throw new Exception("密钥文件错误。");

        byte[] bytes = RSAUtil.hex2byte(part[0]);
        BigInteger modulus = new BigInteger(bytes);
        bytes = RSAUtil.hex2byte(part[1]);
        BigInteger publicExponent = new BigInteger(bytes);

        return RSAUtil.generateRSAPublicKey(modulus.toByteArray(), publicExponent.toByteArray());
    }

    public static RSAPrivateKey getPrivateKey(String keyString) throws Exception {
        String[] part = keyString.split(" ");
        if (part.length != 2)
            throw new Exception("密钥文件错误。");
        byte[] bytes = RSAUtil.hex2byte(part[0]);
        BigInteger modulus = new BigInteger(bytes);
        bytes = RSAUtil.hex2byte(part[1]);
        BigInteger privateExponent = new BigInteger(bytes);

        return RSAUtil.generateRSAPrivateKey(modulus.toByteArray(), privateExponent.toByteArray());
    }

    //字节码转换成16进制字符串
    public static String byte2hex(byte bytes[]) {
        StringBuffer retString = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF))
                    .substring(1).toUpperCase());
        }
        return retString.toString();
    }

    public static String byte2hex(byte bytes[], int index, int len) {
        StringBuffer retString = new StringBuffer();
        for (int i = index; i < len; ++i) {
            retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF))
                    .substring(1).toUpperCase());
        }
        return retString.toString();
    }

    //将16进制字符串转换成字节码
    public static byte[] hex2byte(String hex) {
        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),
                    16);
        }
        return bts;
    }
}  
