package be.alphacredit.services.esign.rest;

import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.slf4j.*;

import com.ibm.websphere.jaxrs20.multipart.*;

import be.alphacredit.services.esign.exceptions.*;
import be.alphacredit.services.esign.session.*;

@Path("/api")
public class QuickSignControllerServiceImpl implements QuickSignControllerService
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(QuickSignControllerService.class);

  @EJB
  private QuickSignControllerLocal qss;

  @Override
  public Response getQuickSignRootResource() throws QuickSignException
  {
    return qss.getQuickSignRootResource();
  }

  @Override
  public Response createQuickSignTransaction(IMultipartBody multipartBody) throws QuickSignException
  {
    return qss.createQuickSignTransaction(multipartBody);
  }
}
