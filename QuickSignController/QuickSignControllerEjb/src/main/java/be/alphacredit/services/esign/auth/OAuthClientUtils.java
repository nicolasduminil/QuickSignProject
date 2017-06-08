package be.alphacredit.services.esign.auth;

import java.util.*;

import org.slf4j.*;

import be.alphacredit.services.esign.exceptions.*;
import net.oauth.*;
import net.oauth.signature.*;

public class OAuthClientUtils
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(OAuthClientUtils.class);
  
  public static String createAuthorizationHeader(Consumer consumer, String method, String requestURI) throws QuickSignException
  {
    Map<String, String> parameters = new HashMap<>();
    parameters.put(OAuth.OAUTH_CONSUMER_KEY, consumer.getConsumerKey());
    parameters.put(OAuth.OAUTH_NONCE, UUID.randomUUID().toString());
    parameters.put(OAuth.OAUTH_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
    parameters.put(OAuth.OAUTH_VERSION, OAuth.VERSION_1_0);
    parameters.put(OAuth.OAUTH_SIGNATURE_METHOD, OAuth.RSA_SHA1);
    OAuthAccessor accessor = createAccessor(consumer, parameters);
    return doGetAuthorizationHeader(accessor, method, requestURI, parameters);
  }

  private static OAuthAccessor createAccessor(Consumer consumer, Map<String, String> props)
  {
    OAuthConsumer oAuthConsumer = new OAuthConsumer(null, consumer.getConsumerKey(), consumer.getSecret(), null);
    if (props != null)
      for (Map.Entry<String, String> entry : props.entrySet())
        oAuthConsumer.setProperty(entry.getKey(), entry.getValue());
    oAuthConsumer.setProperty(RSA_SHA1.PRIVATE_KEY, consumer.getPrivateKey());
    return new OAuthAccessor(oAuthConsumer);
  }

  private static String doGetAuthorizationHeader(OAuthAccessor accessor, String method, String requestURI, Map<String, String> parameters) throws QuickSignException
  {
    try
    {
      OAuthMessage msg = accessor.newRequestMessage(method, requestURI, parameters.entrySet());
      StringBuilder sb = new StringBuilder();
      sb.append(msg.getAuthorizationHeader(null));
      for (Map.Entry<String, String> entry : parameters.entrySet())
        if (!entry.getKey().startsWith("oauth_"))
        {
          sb.append(", ");
          sb.append(OAuth.percentEncode(entry.getKey())).append("=\"");
          sb.append(OAuth.percentEncode(entry.getValue())).append('"');
        }
      return sb.toString();
    }
    catch (Exception ex)
    {
      throw new QuickSignException(ex);
    }
  }
}
