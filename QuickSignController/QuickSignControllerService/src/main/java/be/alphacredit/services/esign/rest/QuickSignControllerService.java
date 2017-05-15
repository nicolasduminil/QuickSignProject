package be.alphacredit.services.esign.rest;

import java.net.*;

import javax.ws.rs.core.*;

import be.alphacredit.services.esign.model.dtos.*;

public interface QuickSignControllerService
{
  public Response createQuickSignTransaction (QuickSignCreateTransactionContextDTO ctx, URI[] contracts);
}
