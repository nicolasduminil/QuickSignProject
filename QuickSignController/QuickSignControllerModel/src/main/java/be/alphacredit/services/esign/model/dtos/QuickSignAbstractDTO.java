package be.alphacredit.services.esign.model.dtos;

import java.util.*;

import javax.ws.rs.core.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "href", "method" })
public abstract class QuickSignAbstractDTO
{
  private Link href;
  private String method;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("href")
  public Link getHref()
  {
    return href;
  }

  public void setHref(Link href)
  {
    this.href = href;
  }

  @JsonProperty("method")
  public String getMethod()
  {
    return method;
  }

  public void setMethod(String method)
  {
    this.method = method;
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
