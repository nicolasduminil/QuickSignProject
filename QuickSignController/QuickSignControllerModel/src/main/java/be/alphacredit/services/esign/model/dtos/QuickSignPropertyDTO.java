package be.alphacredit.services.esign.model.dtos;

import java.util.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "propertyName", "propertyValue" })
public class QuickSignPropertyDTO
{
  private String propertyName;
  private String propertyValue;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public QuickSignPropertyDTO()
  {
  }

  public QuickSignPropertyDTO(String propertyName, String propertyValue, Map<String, Object> additionalProperties)
  {
    this.propertyName = propertyName;
    this.propertyValue = propertyValue;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("propertyName")
  public String getPropertyName()
  {
    return propertyName;
  }

  public void setPropertyName(String propertyName)
  {
    this.propertyName = propertyName;
  }

  @JsonProperty("propertyValue")
  public String getPropertyValue()
  {
    return propertyValue;
  }

  public void setPropertyValue(String propertyValue)
  {
    this.propertyValue = propertyValue;
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
