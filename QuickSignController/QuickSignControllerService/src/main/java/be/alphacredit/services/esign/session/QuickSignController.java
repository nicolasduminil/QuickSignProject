package be.alphacredit.services.esign.session;

import java.io.*;

import be.alphacredit.services.esign.exceptions.*;
import be.alphacredit.services.esign.model.dtos.*;

public interface QuickSignController
{
  public QuickSignRootResourceDTO getQuickSignRootResource() throws QuickSignException;
  void createQuickSignTransaction(QuickSignCreateTransactionContextDTO dto, InputStream pdf) throws QuickSignException;
}
