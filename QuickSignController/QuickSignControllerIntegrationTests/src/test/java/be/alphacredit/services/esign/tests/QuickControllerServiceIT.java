package be.alphacredit.services.esign.tests;

import static org.junit.Assert.*;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

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
    {
        FileInputStream stream = new FileInputStream(file);
        ContentDisposition cd = new ContentDisposition("filename=" + file.getName());
        attachments.add (new Attachment(file.getName(), stream, cd));
    }
    assertEquals (200, client.target(new URI("http://localhost:9081/qs/services/api/createTransaction")).request().post(Entity.entity(new MultipartBody(attachments), MediaType.MULTIPART_FORM_DATA)).getStatus());
  }
}
