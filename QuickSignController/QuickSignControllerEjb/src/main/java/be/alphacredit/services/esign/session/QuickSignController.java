package be.alphacredit.services.esign.session;

import javax.ws.rs.core.*;

import com.ibm.websphere.jaxrs20.multipart.*;

import be.alphacredit.services.esign.exceptions.*;

public interface QuickSignController
{
  public Response getQuickSignRootResource() throws QuickSignException;
  public Response createQuickSignTransaction(IMultipartBody multipartBody) throws QuickSignException;
}
