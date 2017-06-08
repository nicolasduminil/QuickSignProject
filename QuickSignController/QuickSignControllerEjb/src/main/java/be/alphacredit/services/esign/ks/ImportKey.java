package be.alphacredit.services.esign.ks;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.spec.*;
import java.util.*;

public class ImportKey
{
  private static InputStream fullStream(String fname) throws IOException
  {
    FileInputStream fis = new FileInputStream(fname);
    DataInputStream dis = new DataInputStream(fis);
    try
    {
      byte[] bytes = new byte[dis.available()];
      dis.readFully(bytes);
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      return bais;
    }
    finally
    {
      dis.close();
    }
  }

  public static void main(String args[])
  {
    String keypass = "California1";
    String defaultalias = "importkey";
    String keystorename = System.getProperty("keystore");
    if (keystorename == null)
      keystorename = System.getProperty("user.home") + System.getProperty("file.separator") + "keystore.ImportKey";
    String keyfile = "";
    String certfile = "";
    if (args.length < 2 || args.length > 3)
    {
      System.out.println("Usage: java comu.ImportKey keyfile certfile [alias]");
      System.exit(0);
    }
    else
    {
      keyfile = args[0];
      certfile = args[1];
      if (args.length > 2)
        defaultalias = args[2];
    }
    try
    {
      KeyStore ks = KeyStore.getInstance("JKS", "SUN");
      ks.load(null, keypass.toCharArray());
      System.out.println("Using keystore-file : " + keystorename);
      ks.store(new FileOutputStream(keystorename), keypass.toCharArray());
      ks.load(new FileInputStream(keystorename), keypass.toCharArray());
      InputStream fl = fullStream(keyfile);
      byte[] key = new byte[fl.available()];
      KeyFactory kf = KeyFactory.getInstance("RSA");
      fl.read(key, 0, fl.available());
      fl.close();
      PKCS8EncodedKeySpec keysp = new PKCS8EncodedKeySpec(key);
      PrivateKey ff = kf.generatePrivate(keysp);
      CertificateFactory cf = CertificateFactory.getInstance("X.509");
      InputStream certstream = fullStream(certfile);
      Collection c = cf.generateCertificates(certstream);
      Certificate[] certs = new Certificate[c.toArray().length];
      if (c.size() == 1)
      {
        certstream = fullStream(certfile);
        System.out.println("One certificate, no chain.");
        Certificate cert = cf.generateCertificate(certstream);
        certs[0] = cert;
      }
      else
      {
        System.out.println("Certificate chain length: " + c.size());
        certs = (Certificate[]) c.toArray();
      }
      ks.setKeyEntry(defaultalias, ff, keypass.toCharArray(), certs);
      System.out.println("Key and certificate stored.");
      System.out.println("Alias:" + defaultalias + "  Password:" + keypass);
      ks.store(new FileOutputStream(keystorename), keypass.toCharArray());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
}
