package be.alphacredit.services.esign.session;

import java.io.*;
import java.net.*;

import javax.annotation.*;
import javax.ejb.*;
import javax.inject.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.apache.deltaspike.core.api.config.*;
import org.codehaus.jackson.jaxrs.*;
import org.slf4j.*;

import be.alphacredit.services.esign.exceptions.*;
import be.alphacredit.services.esign.model.dtos.*;

@Stateless
public class QuickSignControllerEJB implements QuickSignControllerLocal, QuickSignControllerRemote
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(QuickSignControllerEJB.class); 
  
  @Inject
  @ConfigProperty(name = "quicksign.base.url")
  private String quickSignBaseUrl; 
  private Client client;
  private URI baseURI;
  private QuickSignRootResourceDTO quickSignRootResource;

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
  public QuickSignRootResourceDTO getQuickSignRootResource() throws QuickSignException
  {
    Response resp = client.target(baseURI).request().accept("application/json").get();
    quickSignRootResource = resp.readEntity(QuickSignRootResourceDTO.class);
    return quickSignRootResource;
  }

  @Override
  public void createQuickSignTransaction(QuickSignCreateTransactionContextDTO dto, InputStream pdf) throws QuickSignException
  {
    /*List<Attachment> attachments = new ArrayList<Attachment>();
    for (URI uri : files)
      try
      {
        File file = new File (uri);
        Attachment attachment = new Attachment("root", new FileInputStream(file), new ContentDisposition("attachment;" + file.getName()));
        attachments.add(attachment);
      }
      catch (Exception ex)
      {
        throw new QuickSignException(new ArrayList<Link>(), Status.NOT_ACCEPTABLE, QuickSignErrorCode.E400900, "URI Syntax Exception", ex.getMessage());
      }

    MultipartBody multipartBody = new MultipartBody(attachments);
    client.target(getCreateTransactionLink()).request().header("Content-Type", "multipart/form-data").accept("application/json").post(Entity.entity(multipartBody, MediaType.MULTIPART_FORM_DATA));*/
  }

  private Link getCreateTransactionLink() throws QuickSignException
  {
    if (quickSignRootResource == null)
      getQuickSignRootResource();
    return quickSignRootResource.getLinks().getCreateTransaction().getHref();
  }
}
