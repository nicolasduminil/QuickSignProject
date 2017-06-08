package be.alphacredit.services.esign.auth;

import java.security.*;
import java.util.*;

public class Consumer
{
  private String consumerKey;
  private PrivateKey privateKey;
  private String secret;

  public Consumer()
  {
  }

  public Consumer(String consumerKey, PrivateKey privateKey)
  {
    this.consumerKey = consumerKey;
    this.privateKey = privateKey;
    this.secret = Base64.getEncoder().encodeToString(privateKey.getEncoded());
  }

  public String getConsumerKey()
  {
    return consumerKey;
  }

  public PrivateKey getPrivateKey()
  {
    return privateKey;
  }
  
  public String getSecret()
  {
    return secret;
  }
}
