package be.alphacredit.services.esign.tests;

import java.io.*;
import java.util.*;

import javax.ws.rs.client.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.*;

import org.apache.cxf.jaxrs.client.*;
import org.apache.cxf.jaxrs.ext.multipart.*;
import org.codehaus.jackson.jaxrs.*;
import org.junit.*;
import org.slf4j.*;

public class QuickControllerServiceIT
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(QuickControllerServiceIT.class);

  private static Client client;

  @BeforeClass
  public static void beforeClass()
  {
    client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
  }

  @AfterClass
  public static void afterClass()
  {
    client.close();
    client = null;
  }

  @Test
  public void testUpload() throws Exception
  {
    List providers = new ArrayList();
    providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
    providers.add(new org.apache.cxf.jaxrs.provider.MultipartProvider());
    WebClient webClient = WebClient.create("http://localhost:9081/qs/services/api/createTransaction", providers).accept(MediaType.APPLICATION_JSON);
    webClient.encoding("UTF-8");
    webClient.type(MediaType.MULTIPART_FORM_DATA);
    File[] files = new File(".").listFiles(new FilenameFilter()
    {
      @Override
      public boolean accept(File dir, String name)
      {
        return name.endsWith(".pdf");
      }
    });
    List<Attachment> attachments = new ArrayList<Attachment>();
    for (File file : files)
      try (FileInputStream stream = new FileInputStream(file);)
      {
        ContentDisposition cd = new ContentDisposition("filename=" + file.getName());
        attachments.add(new Attachment("contracts", stream, cd));
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    //webClient.post(new MultipartBody(attachments));
    webClient.post(new MultipartBody(new Attachment("contracts", new FileInputStream("test.pdf"), new ContentDisposition("filename=test.pdf"))));
  }
}
