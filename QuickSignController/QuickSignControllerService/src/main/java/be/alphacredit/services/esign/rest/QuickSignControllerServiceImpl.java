package be.alphacredit.services.esign.rest;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.ejb.*;
import javax.ws.rs.core.*;

import org.slf4j.*;

import be.alphacredit.services.esign.exceptions.*;
import be.alphacredit.services.esign.model.dtos.*;

public class QuickSignControllerServiceImpl implements QuickSignControllerService
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(QuickSignControllerService.class);
  @EJB
  private QuickSignControllerServiceLocal qss;

  @Override
  public Response getQuickSignRootResource() throws QuickSignException
  {
    return null;
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
  public Response createQuickSignTransaction(QuickSignCreateTransactionContextDTO ctx, Collection<URI> contracts) throws QuickSignException
  {
    return null;
  }
}
