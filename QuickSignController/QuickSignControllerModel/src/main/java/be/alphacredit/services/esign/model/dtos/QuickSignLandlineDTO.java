package be.alphacredit.services.esign.model.dtos;

import java.util.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "secondary" })
public class QuickSignLandlineDTO
{
  private String secondary;

  public QuickSignLandlineDTO()
  {
  }

  public QuickSignLandlineDTO(String secondary, Map<String, Object> additionalProperties)
  {
    this.secondary = secondary;
    this.additionalProperties = additionalProperties;
  }

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("secondary")
  public String getSecondary()
  {
    return secondary;
  }

  public void setSecondary(String secondary)
  {
    this.secondary = secondary;
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
