package ehu.isad.utils;


import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Sarea {

    private static Sarea nireSarea = null;

    private Sarea() {
    }

    public static Sarea getNireSarea() {
        if (nireSarea == null) {
            nireSarea = new Sarea();
        }
        return nireSarea;
    }

    public String bueltatuGunearenMD5(String gunea) throws IOException {
        URL url = new URL(gunea);
        InputStream is = url.openStream();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            String digest = getDigest(is, md, 2048);
            return digest;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
            throws NoSuchAlgorithmException, IOException {

        md.reset();
        byte[] bytes = new byte[byteArraySize];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }
        byte[] digest = md.digest();
        String result = new String(Hex.encodeHex(digest));
        return result;
    }

}