package be.alphacredit.services.esign.session;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;

import javax.annotation.*;
import javax.ejb.*;
import javax.inject.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

import org.apache.deltaspike.core.api.config.*;
import org.codehaus.jackson.jaxrs.*;
import org.slf4j.*;

import com.ibm.websphere.jaxrs20.multipart.*;

import be.alphacredit.services.esign.auth.*;
import be.alphacredit.services.esign.exceptions.*;
import be.alphacredit.services.esign.model.dtos.*;

@Stateless
public class QuickSignControllerEJB implements QuickSignControllerLocal, QuickSignControllerRemote
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(QuickSignControllerEJB.class);

  @Inject
  @ConfigProperty(name = "quicksign.base.url")
  private String quickSignBaseUrl;
  @Inject
  @ConfigProperty(name = "quicksign.consumer.key")
  private String quickSignConsumerKey;
  @Inject
  @ConfigProperty(name = "quicksign.controller.keystore.file")
  private String quickSignControllerKeystoreFile;
  @Inject
  @ConfigProperty(name = "quicksign.controller.keystore.password")
  private String quickSignControllerKeystorePassword;
  @Inject
  @ConfigProperty(name = "quicksign.controller.ac.alias")
  private String quickSignControllerAcAlias;
  @Inject
  @ConfigProperty(name = "quicksign.controller.ac.alias.password")
  private String quickSignControllerAcAliasPassword;
  private Client client;
  private URI baseURI;
  private QuickSignRootResourceDTO quickSignRootResource;
  private KeyStore qsKeyStore = null;
  private PrivateKey acPrivateKey = null;

  @PostConstruct
  public void init()
  {
    client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
    try
    {
      baseURI = new URI(quickSignBaseUrl);
    }
    catch (URISyntaxException ex)
    {
      slf4jLogger.error("### Invalid URI ");
    }
  }

  @Override
  public Response getQuickSignRootResource() throws QuickSignException
  {
    Response resp = client.target(baseURI).request().accept("application/json").get();
    if (!Status.OK.equals(resp.getStatusInfo()))
      throw new QuickSignException("### Unexpected exception during POST to " + baseURI + " " + resp.getStatus());
    quickSignRootResource = resp.readEntity(QuickSignRootResourceDTO.class);
    return resp;
  }

  @Override
  public Response createQuickSignTransaction(IMultipartBody multipartBody) throws QuickSignException
  {
    try
    {
      return client.target(getCreateTransactionLink()).request()
          .header("Authorization", OAuthClientUtils.createAuthorizationHeader(new Consumer(quickSignConsumerKey, getAcPrivateKey()), "POST", quickSignBaseUrl))
          .post(Entity.entity(multipartBody, MediaType.MULTIPART_FORM_DATA));
    }
    catch (QuickSignException ex)
    {
      slf4jLogger.error(ex.getMessage());
      throw ex;
    }
    catch (Exception ex)
    {
      throw new QuickSignException (ex);
    }
  }

  private Link getCreateTransactionLink() throws QuickSignException
  {
    if (quickSignRootResource == null)
      getQuickSignRootResource();
    return quickSignRootResource.getLinks().getCreateTransaction().getHref();
  }

  private KeyStore getQuickSignControllerKeyStore() /* throws Exception */ throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException
  {
    if (qsKeyStore == null)
    {
      qsKeyStore = KeyStore.getInstance("JKS");
      qsKeyStore.load(new FileInputStream(new File(quickSignControllerKeystoreFile)), quickSignControllerKeystorePassword.toCharArray());
    }
    return qsKeyStore;
  }

  private PrivateKey getAcPrivateKey() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException
  {
    if (acPrivateKey == null)
    {
      acPrivateKey = (PrivateKey) getQuickSignControllerKeyStore().getKey(quickSignControllerAcAlias, quickSignControllerAcAliasPassword.toCharArray());
    }
    return acPrivateKey;
  }
}
