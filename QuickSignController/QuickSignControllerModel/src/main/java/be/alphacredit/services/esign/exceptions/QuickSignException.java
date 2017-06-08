package be.alphacredit.services.esign.exceptions;

import java.util.*;

import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

public class QuickSignException extends Exception
{
  private static final long serialVersionUID = 1L;
  
  private List<Link> links;
  private Status status;
  private QuickSignErrorCode errorCode;
  //private String message;
  private String developerMessage;

  public QuickSignException()
  {
    super();
  }

  public QuickSignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
  {
    super(message, cause, enableSuppression, writableStackTrace);

  }

  public QuickSignException(String message, Throwable cause)
  {
    super(message, cause);

  }

  public QuickSignException(String message)
  {
    super(message);

  }

  public QuickSignException(Throwable cause)
  {
    super(cause);

  }

  public QuickSignException(List<Link> links, Status status, QuickSignErrorCode errorCode, String message, String developerMessage)
  {
    super(message);
    this.links = links;
    this.status = status;
    this.errorCode = errorCode;
    this.developerMessage = developerMessage;
  }

  public List<Link> getLinks()
  {
    return links;
  }

  public void setLinks(List<Link> links)
  {
    this.links = links;
  }

  public Status getStatus()
  {
    return status;
  }

  public void setStatus(Status status)
  {
    this.status = status;
  }

  public QuickSignErrorCode getErrorCode()
  {
    return errorCode;
  }

  public void setErrorCode(QuickSignErrorCode errorCode)
  {
    this.errorCode = errorCode;
  }

  public String getDeveloperMessage()
  {
    return developerMessage;
  }

  public void setDeveloperMessage(String developerMessage)
  {
    this.developerMessage = developerMessage;
  }
}
