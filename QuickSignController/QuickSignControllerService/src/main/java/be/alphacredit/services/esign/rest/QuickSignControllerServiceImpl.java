package be.alphacredit.services.esign.rest;

import java.net.*;

import javax.ejb.*;
import javax.ws.rs.core.*;

import org.slf4j.*;

import be.alphacredit.services.esign.model.dtos.*;

public class QuickSignControllerServiceImpl implements QuickSignControllerService
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(QuickSignControllerService.class);
  @EJB
  private QuickSignControllerServiceLocal qss;

  @Override
  public Response createQuickSignTransaction(QuickSignCreateTransactionContextDTO ctx, URI[] contracts)
  {
    return null;
  }
}
