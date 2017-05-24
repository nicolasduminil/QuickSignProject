package be.alphacredit.services.esign.rest;

import java.io.*;

import javax.ws.rs.core.*;

import com.ibm.websphere.jaxrs20.multipart.*;

import be.alphacredit.services.esign.exceptions.*;
import be.alphacredit.services.esign.model.dtos.*;

public interface QuickSignControllerService
{
  public Response getQuickSignTransactionResource(String token) throws QuickSignException;

  public Response getQuickSignTransactionSTatus(String token) throws QuickSignException;

  public Response updateQuickSignTransactionStatus(String token, QuickSignTransactionStatusDTO status) throws QuickSignException;

  public Response processDocuments(String token, QuickSignDocumentStatusDTO status) throws QuickSignException;

  public Response sendApplicationFile(String token) throws QuickSignException;

  public Response getQuickSignDocumentResource(String token) throws QuickSignException;

  public Response getQuickSignDocumentList(String token) throws QuickSignException;

  public Response uploadQuickSignDocument(String token, QuickSignDocumentDetailCodeDTO documentCode) throws QuickSignException;

  public Response changeQuickSignDocumentType(String token, QuickSignDocumentDetailCodeDTO documentCode, QuickSignDocumentDetailCodeDTO newDocumentCode) throws QuickSignException;

  public Response deleteQuickSignDocument(String token, QuickSignDocumentDetailCodeDTO documentCode) throws QuickSignException;

  public Response validateQuickSignUploadedDocuments(String token) throws QuickSignException;

  public Response getQuickSignWorkspaceResource(String token) throws QuickSignException;

  public Response uploadFileToQuickSignWorkspace(String token, FileDescriptor file) throws QuickSignException;

  public Response deleteFileFromQuickSignWorkspace(String token, FileDescriptor file) throws QuickSignException;

  public Response classifyFileToQuickSignDocument(QuickSignClassifyDocumentDTO doc) throws QuickSignException;

  public Response getQuickSignTransactionScore(String token) throws QuickSignException;

  public Response annotateQuickSignContract(String token, QuickSignAnnotateContractDTO dto) throws QuickSignException;

  public Response initiateQuickSignSignatureSession(String token) throws QuickSignException;

  public Response getQuickSignRootResource() throws QuickSignException;

  public Response createQuickSignTransaction(IMultipartBody body) throws QuickSignException;
}
