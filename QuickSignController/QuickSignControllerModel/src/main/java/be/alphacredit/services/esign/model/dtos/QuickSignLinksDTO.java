package be.alphacredit.services.esign.model.dtos;

import java.util.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder(
{ "version", "transactions", "createTransaction" })
public class QuickSignLinksDTO
{
  private QuickSignVersionDTO version;
  private QuickSignTransactionsDTO transactions;
  private QuickSignCreateTransactionDTO createTransaction;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("version")
  public QuickSignVersionDTO getVersion()
  {
    return version;
  }

  public void setVersion(QuickSignVersionDTO version)
  {
    this.version = version;
  }

  @JsonProperty("transactions")
  public QuickSignTransactionsDTO getTransactions()
  {
    return transactions;
  }

  public void setTransactions(QuickSignTransactionsDTO transactions)
  {
    this.transactions = transactions;
  }

  @JsonProperty("createTransaction")
  public QuickSignCreateTransactionDTO getCreateTransaction()
  {
    return createTransaction;
  }
}