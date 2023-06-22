package gen.rsa;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 备注
 *
 * @author HeCG
 * @date 2023/6/22 23:34
 * @since 1.0
 */
public class RSACreate {

    public static void main(String... args) throws NoSuchAlgorithmException {
        String[] rsa = createRSA();
        System.out.println("Private Key:");
        System.out.println(rsa[0]);
        System.out.println("Public Key:");
        System.out.println(rsa[1]);
        byte[] key = create3DesKey();
        System.out.println(key.length);
        System.out.println(Base64.getEncoder().encodeToString(key));
    }

    public static String[] createRSA() throws NoSuchAlgorithmException {
        return createRSA(512);
    }

    public static String[] createRSA(int len) throws NoSuchAlgorithmException {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(len);
        KeyPair pair = gen.generateKeyPair();
        RSAPrivateKey pri = (RSAPrivateKey) pair.getPrivate();
        RSAPublicKey pub = (RSAPublicKey) pair.getPublic();
        Base64.Encoder encoder = Base64.getEncoder();
        String pri64 = encoder.encodeToString(pri.getEncoded());
        String pub64 = encoder.encodeToString(pub.getEncoded());
        return new String[]{pri64, pub64};
    }

    public static byte[] create3DesKey() throws NoSuchAlgorithmException {
        KeyGenerator des3 = KeyGenerator.getInstance("DESede");
        SecretKey key = des3.generateKey();
        return key.getEncoded();
    }

}
