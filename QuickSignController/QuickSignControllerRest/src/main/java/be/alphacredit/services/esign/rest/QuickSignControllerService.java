package be.alphacredit.services.esign.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.ibm.websphere.jaxrs20.multipart.*;

import be.alphacredit.services.esign.exceptions.*;

public interface QuickSignControllerService
{
  @GET
  public Response getQuickSignRootResource() throws QuickSignException;
  
  @POST
  @Consumes("multipart/form-data")
  @Path("createTransaction")
  public Response createQuickSignTransaction(IMultipartBody body) throws QuickSignException;
}
