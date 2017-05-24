package be.alphacredit.services.esign.rest;

import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;
import javax.ws.rs.ext.*;

import be.alphacredit.services.esign.exceptions.*;

public class QuickSignExceptionMapper implements ExceptionMapper<QuickSignException>
{
  @Override
  public Response toResponse(QuickSignException ex)
  {
    return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
  }
}
