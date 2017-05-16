package be.alphacredit.services.esign.rest;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.ws.rs.core.*;

import be.alphacredit.services.esign.exceptions.*;
import be.alphacredit.services.esign.model.dtos.*;

public interface QuickSignControllerService
{
  /**
   * Creates a Quick Sign transaction This request must use POST method and have
   * a header Content-Type with the value multipart/form-data (along with
   * appropriate boundary property).
   * 
   * @param ctx
   *          The Quick Sign context
   * @param contracts
   *          Collection of contracts
   * @return Response HTTP/1.1 201 Created
   * @throws QuickSignException
   *           Possible errors that can happen when you try to create a
   *           transaction are : E500000 UNKNOWN : Unknown error during
   *           transaction creation. E400001 BAD REQUEST : A bad request was
   *           received, e.g. the distributor entity or the product could not be
   *           found (based on ID). E400010 MISSING FIELD : Missing part (e.g.
   *           please check the name of the contract part) or missing field in
   *           the context part. E400011 BAD FIELD VALUE : Data format error for
   *           a field in the context part.
   */
  public Response createQuickSignTransaction(QuickSignCreateTransactionContextDTO ctx, Collection<URI> contracts) throws QuickSignException;

  /**
   * Gets a Quick Sign transaction
   * 
   * @param token
   *          The Quick Sign token returned by the previous
   *          createQuickSignTransaction call
   * @return Response containing a QuickSignTransactionResourceDTO
   * @throws QuickSignException
   *           Possible errors that can happen when you try to create a
   *           transaction are : E500020 UNKNOWN : Unknown error during
   *           transaction treatments. E400020 BAD TOKEN : Token format is
   *           invalid. E403020 ACCESS DENIED : Access to this transaction’s not
   *           allowed. E403060 COMPLETED : Transaction’s complete (cannot be
   *           accessed anymore). E404020 NOT FOUND : Transaction’s not found.
   */
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

  Response getQuickSignRootResource() throws QuickSignException;
}
