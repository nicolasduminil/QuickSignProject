package be.alphacredit.services.esign.rest;

import java.io.*;
import java.util.*;

import javax.ejb.*;
import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.deltaspike.core.api.config.*;
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
  @GET
  public Response getQuickSignRootResource() throws QuickSignException
  {
    return Response.ok(qss.getQuickSignRootResource()).build();
  }

  @Override
  public Response getQuickSignTransactionResource(String token) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response getQuickSignTransactionSTatus(String token) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response updateQuickSignTransactionStatus(String token, QuickSignTransactionStatusDTO status) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response processDocuments(String token, QuickSignDocumentStatusDTO status) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response sendApplicationFile(String token) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response getQuickSignDocumentResource(String token) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response getQuickSignDocumentList(String token) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response uploadQuickSignDocument(String token, QuickSignDocumentDetailCodeDTO documentCode) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response changeQuickSignDocumentType(String token, QuickSignDocumentDetailCodeDTO documentCode, QuickSignDocumentDetailCodeDTO newDocumentCode) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response deleteQuickSignDocument(String token, QuickSignDocumentDetailCodeDTO documentCode) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response validateQuickSignUploadedDocuments(String token) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response getQuickSignWorkspaceResource(String token) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response uploadFileToQuickSignWorkspace(String token, FileDescriptor file) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response deleteFileFromQuickSignWorkspace(String token, FileDescriptor file) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response classifyFileToQuickSignDocument(QuickSignClassifyDocumentDTO doc) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response getQuickSignTransactionScore(String token) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response annotateQuickSignContract(String token, QuickSignAnnotateContractDTO dto) throws QuickSignException
  {
    return null;
  }

  @Override
  public Response initiateQuickSignSignatureSession(String token) throws QuickSignException
  {
    return null;
  }

  @Override
  @POST
  @Consumes("multipart/form-data")
  @Path("createTransaction")
  public Response createQuickSignTransaction(IMultipartBody multipartBody) throws QuickSignException
  {
    List<IAttachment> attachments = multipartBody.getAllAttachments();
    for (IAttachment attachment : attachments)
    {
      if (attachment == null)
        continue;
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
