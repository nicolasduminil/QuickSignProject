package be.alphacredit.services.esign.tests;

import static org.junit.Assert.*;

import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.apache.cxf.jaxrs.ext.multipart.*;
import org.codehaus.jackson.jaxrs.*;
import org.codehaus.jackson.map.*;
import org.junit.*;
import org.slf4j.*;

import be.alphacredit.services.esign.model.dtos.*;

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
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    new ObjectMapper().writeValue(baos, getDto());
    attachments.add(new Attachment("context", new ByteArrayInputStream(baos.toByteArray()), new ContentDisposition("form-data; name=\"create-transaction-context\"")));
    for (File file : files)
    {
      FileInputStream stream = new FileInputStream(file);
      attachments.add(new Attachment(file.getName(), stream, new ContentDisposition("form-data; name=\"contracts\"; filename=\"" + file.getName() + "\"")));
    }
    assertEquals(200,
        client.target(new URI("http://localhost:9081/qs/services/api/createTransaction")).request().post(Entity.entity(new MultipartBody(attachments), MediaType.MULTIPART_FORM_DATA)).getStatus());
  }
  
  private QuickSignCreateTransactionContextDTO getDto()
  {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("key", "toto");
    List<String> lst = new ArrayList<String>();
    lst.add("toto");
    QuickSignAddressDTO quickSignAddressDTO = new QuickSignAddressDTO(lst, "city", "zipCode", map);
    QuickSignEmailDTO quickSignEmailDTO = new QuickSignEmailDTO("secondary", map);
    QuickSignCellDTO  quickSignCellDTO = new QuickSignCellDTO("secondary", map);
    QuickSignLandlineDTO landlines = new QuickSignLandlineDTO("secondary", map);
    QuickSignPhoneDTO quickSignPhoneDTO = new QuickSignPhoneDTO("cell", quickSignCellDTO, "landline", landlines, map);
    QuickSignClientDTO quickSignClientDTO1 = new QuickSignClientDTO("id1", "title1", "lastName1", "firstName1", Calendar.getInstance(), quickSignAddressDTO, "email1", quickSignEmailDTO,
        quickSignPhoneDTO, map);
    QuickSignClientDTO quickSignClientDTO2 = new QuickSignClientDTO("id2", "title2", "lastName2", "firstName2", Calendar.getInstance(), quickSignAddressDTO, "email2", quickSignEmailDTO,
        quickSignPhoneDTO, map);
    QuickSignPropertyDTO quickSignPropertyDTO = new QuickSignPropertyDTO("propertyName", "propertyValue", map);
    List<QuickSignDocumentCategory> quickSignDocumentCategory = new ArrayList<QuickSignDocumentCategory>();
    quickSignDocumentCategory.add(QuickSignDocumentCategory.ONE);
    QuickSignAdvisorDTO quickSignAdvisorDTO = new QuickSignAdvisorDTO("email", map);
    QuickSignCreateTransactionContextDTO quickSignCreateTransactionDTO = new QuickSignCreateTransactionContextDTO("distributorId", "signatoryId", "productCode", BigDecimal.ONE, "documentReference", Calendar.getInstance(),
        "statusFlowTargetUrl", quickSignClientDTO1, quickSignClientDTO2, quickSignPropertyDTO, quickSignDocumentCategory, quickSignAdvisorDTO, map); 
    return quickSignCreateTransactionDTO;
  }
}
