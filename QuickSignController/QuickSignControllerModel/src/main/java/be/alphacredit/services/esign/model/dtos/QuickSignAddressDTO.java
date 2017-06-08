package be.alphacredit.services.esign.model.dtos;

import java.util.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder(
{ "lines", "city", "zipCode" })
public class QuickSignAddressDTO
{
  private List<String> lines = null;
  private String city;
  private String zipCode;
  
  public QuickSignAddressDTO()
  {
  }

  public QuickSignAddressDTO(List<String> lines, String city, String zipCode, Map<String, Object> additionalProperties)
  {
    this.lines = lines;
    this.city = city;
    this.zipCode = zipCode;
    this.additionalProperties = additionalProperties;
  }

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();


  @JsonProperty("lines")
  public List<String> getLines()
  {
    return lines;
  }

  public void setLines(List<String> lines)
  {
    this.lines = lines;
  }

  @JsonProperty("city")
  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  @JsonProperty("zipCode")
  public String getZipCode()
  {
    return zipCode;
  }

  public void setZipCode(String zipCode)
  {
    this.zipCode = zipCode;
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
