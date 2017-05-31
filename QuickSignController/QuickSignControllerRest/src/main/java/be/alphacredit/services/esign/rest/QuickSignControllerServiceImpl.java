package be.alphacredit.services.esign.rest;

import java.io.*;
import java.util.*;

import javax.ejb.*;
import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.deltaspike.core.api.config.*;
import org.codehaus.jackson.map.*;
import org.slf4j.*;

import com.ibm.websphere.jaxrs20.multipart.*;

import be.alphacredit.services.esign.exceptions.*;
import be.alphacredit.services.esign.model.dtos.*;
import be.alphacredit.services.esign.session.*;

@Path("/api")
public class QuickSignControllerServiceImpl implements QuickSignControllerService
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(QuickSignControllerService.class);
  @Inject
  @ConfigProperty(name = "quicksign.controller.upload.file.server")
  public String uploadFileServer;

  @EJB
  private QuickSignControllerLocal qss;

  @Override
  public Response getQuickSignRootResource() throws QuickSignException
  {
    return Response.ok(/* qss.getQuickSignRootResource() */).build();
  }

  @Override
  public Response createQuickSignTransaction(IMultipartBody multipartBody) throws QuickSignException
  {
    QuickSignCreateTransactionContextDTO dto;
    List<IAttachment> attachments = multipartBody.getAllAttachments();
    for (IAttachment attachment : attachments)
    {
      if (attachment.getContentId().equals("context"))
        try
        {
          dto = new ObjectMapper().readValue(attachment.getDataHandler().getInputStream(), QuickSignCreateTransactionContextDTO.class);
          continue;
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }
      List<String> cds = attachment.getHeaders().get("Content-Disposition");
      String[] contentDisposition = attachment.getHeaders().getFirst("Content-Disposition").split(";");
      for (String tempName : contentDisposition)
        if ((tempName.trim().startsWith("filename")))
        {
          String[] names = tempName.split("=");
          String fileName = names[1].trim().replaceAll("\"", "");
          try
          {
            writeToFileServer(attachment.getDataHandler().getInputStream(), fileName);
          }
          catch (Exception ex)
          {
            throw new QuickSignException(ex);
          }
        }
    }
    return Response.ok().build();
  }

  private void writeToFileServer(InputStream inputStream, String fileName) throws Exception
  {
    OutputStream outputStream = null;
    try
    {
      outputStream = new FileOutputStream(new File(uploadFileServer + fileName));
      int read = 0;
      byte[] bytes = new byte[1024];
      while ((read = inputStream.read(bytes)) != -1)
        outputStream.write(bytes, 0, read);
      outputStream.flush();
    }
    finally
    {
      try
      {
        if (outputStream != null)
          outputStream.close();
      }
      catch (Exception ex)
      {
      }
    }
  }
}
