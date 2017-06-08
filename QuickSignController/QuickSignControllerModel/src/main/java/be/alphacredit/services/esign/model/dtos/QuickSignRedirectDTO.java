package be.alphacredit.services.esign.model.dtos;

import java.util.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({"formUrl", "endUrl" })
public class QuickSignRedirectDTO
{
  private String formUrl;
  private String endUrl;
  
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public QuickSignRedirectDTO()
  {
  }

  public QuickSignRedirectDTO(String formUrl, String endUrl, Map<String, Object> additionalProperties)
  {
    this.formUrl = formUrl;
    this.endUrl = endUrl;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("formUrl")
  public String getFormUrl()
  {
    return formUrl;
  }

  public void setFormUrl(String formUrl)
  {
    this.formUrl = formUrl;
  }

  @JsonProperty("endUrl")
  public String getEndUrl()
  {
    return endUrl;
  }

  public void setEndUrl(String endUrl)
  {
    this.endUrl = endUrl;
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
