package be.alphacredit.services.esign.model.dtos;

import java.math.*;
import java.util.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder(
{ "distributorId", "signatoryId", "productCode", "amount", "documentReference", "withdrawalPeriod", "statusFlowTargetUrl", "client1", "client2", "properties", "documentCategoryCodes", "redirect",
    "advisor" })
public class QuickSignCreateTransactionContextDTO
{
  private String distributorId;
  private String signatoryId;
  private String productCode;
  private BigDecimal amount;
  private String documentReference;
  private Calendar withdrawalPeriod;
  private String statusFlowTargetUrl;
  @JsonProperty("client1")
  private QuickSignClientDTO client1;
  private QuickSignClientDTO client2;
  private QuickSignPropertyDTO properties;
  @JsonProperty("documentCategoryCodes")
  private List<QuickSignDocumentCategory> documentCategoryCodes = null;
  private QuickSignRedirectDTO redirect;
  private QuickSignAdvisorDTO advisor;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public QuickSignCreateTransactionContextDTO()
  {
  }

  public QuickSignCreateTransactionContextDTO(String distributorId, String signatoryId, String productCode, BigDecimal amount, String documentReference, Calendar withdrawalPeriod,
      String statusFlowTargetUrl, QuickSignClientDTO client1, QuickSignClientDTO client2, QuickSignPropertyDTO properties, List<QuickSignDocumentCategory> documentCategoryCodes,
      QuickSignAdvisorDTO advisor, Map<String, Object> additionalProperties)
  {
    this.distributorId = distributorId;
    this.signatoryId = signatoryId;
    this.productCode = productCode;
    this.amount = amount;
    this.documentReference = documentReference;
    this.withdrawalPeriod = withdrawalPeriod;
    this.statusFlowTargetUrl = statusFlowTargetUrl;
    this.client1 = client1;
    this.client2 = client2;
    this.properties = properties;
    this.documentCategoryCodes = documentCategoryCodes;
    this.advisor = advisor;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("distributorId")
  public String getDistributorId()
  {
    return distributorId;
  }

  public void setDistributorId(String distributorId)
  {
    this.distributorId = distributorId;
  }

  @JsonProperty("signatoryId")
  public String getSignatoryId()
  {
    return signatoryId;
  }

  public void setSignatoryId(String signatoryId)
  {
    this.signatoryId = signatoryId;
  }

  @JsonProperty("productCode")
  public String getProductCode()
  {
    return productCode;
  }

  public void setProductCode(String productCode)
  {
    this.productCode = productCode;
  }

  @JsonProperty("amount")
  public BigDecimal getAmount()
  {
    return amount;
  }

  public void setAmount(BigDecimal amount)
  {
    this.amount = amount;
  }

  @JsonProperty("documentReference")
  public String getDocumentReference()
  {
    return documentReference;
  }

  public void setDocumentReference(String documentReference)
  {
    this.documentReference = documentReference;
  }

  @JsonProperty("withdrawalPeriod")
  public Calendar getWithdrawalPeriod()
  {
    return withdrawalPeriod;
  }

  public void setWithdrawalPeriod(Calendar withdrawalPeriod)
  {
    this.withdrawalPeriod = withdrawalPeriod;
  }

  @JsonProperty("statusFlowTargetUrl")
  public String getStatusFlowTargetUrl()
  {
    return statusFlowTargetUrl;
  }

  public void setStatusFlowTargetUrl(String statusFlowTargetUrl)
  {
    this.statusFlowTargetUrl = statusFlowTargetUrl;
  }

  @JsonProperty("client1")
  public QuickSignClientDTO getClient1()
  {
    return client1;
  }

  public void setClient1(QuickSignClientDTO client1)
  {
    this.client1 = client1;
  }

  @JsonProperty("client2")
  public QuickSignClientDTO getClient2()
  {
    return client2;
  }

  public void setClient2(QuickSignClientDTO client2)
  {
    this.client2 = client2;
  }

  @JsonProperty("properties")
  public QuickSignPropertyDTO getProperties()
  {
    return properties;
  }

  public void setProperties(QuickSignPropertyDTO properties)
  {
    this.properties = properties;
  }

  @JsonProperty("documentCategoryCodes")
  public List<QuickSignDocumentCategory> getDocumentCategoryCodes()
  {
    return documentCategoryCodes;
  }

  public void setDocumentCategoryCodes(List<QuickSignDocumentCategory> documentCategoryCodes)
  {
    this.documentCategoryCodes = documentCategoryCodes;
  }

  @JsonProperty("redirect")
  public QuickSignRedirectDTO getRedirect()
  {
    return redirect;
  }

  public void setRedirect(QuickSignRedirectDTO redirect)
  {
    this.redirect = redirect;
  }

  @JsonProperty("advisor")
  public QuickSignAdvisorDTO getAdvisor()
  {
    return advisor;
  }

  public void setAdvisor(QuickSignAdvisorDTO advisor)
  {
    this.advisor = advisor;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties()
  {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value)
  {
    this.additionalProperties.put(name, value);
  }
}
