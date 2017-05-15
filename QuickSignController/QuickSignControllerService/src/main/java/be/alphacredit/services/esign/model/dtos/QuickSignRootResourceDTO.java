package be.alphacredit.services.esign.model.dtos;

import java.util.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "_links" })
public class QuickSignRootResourceDTO
{
  private QuickSignLinksDTO links;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("_links")
  public QuickSignLinksDTO getLinks()
  {
    return links;
  }

  public void setLinks(QuickSignLinksDTO links)
  {
    this.links = links;
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
