package be.alphacredit.services.esign.model.dtos;

import java.util.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "id", "title", "lastName", "firstName", "dateOfBirth", "address", "email", "emails", "phones" })
public class QuickSignClientDTO
{
  private String id;
  private String title;
  private String lastName;
  private String firstName;
  private Calendar dateOfBirth;
  private QuickSignAddressDTO address;
  private String email;
  private QuickSignEmailDTO emails;
  private QuickSignPhoneDTO phones;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("id")
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @JsonProperty("title")
  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  @JsonProperty("lastName")
  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  @JsonProperty("firstName")
  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  @JsonProperty("dateOfBirth")
  public Calendar getDateOfBirth()
  {
    return dateOfBirth;
  }

  public void setDateOfBirth(Calendar dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  @JsonProperty("address")
  public QuickSignAddressDTO getAddress()
  {
    return address;
  }

  public void setAddress(QuickSignAddressDTO address)
  {
    this.address = address;
  }

  @JsonProperty("email")
  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  @JsonProperty("emails")
  public QuickSignEmailDTO getEmails()
  {
    return emails;
  }

  public void setEmails(QuickSignEmailDTO emails)
  {
    this.emails = emails;
  }

  @JsonProperty("phones")
  public QuickSignPhoneDTO getPhones()
  {
    return phones;
  }

  public void setPhones(QuickSignPhoneDTO phones)
  {
    this.phones = phones;
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
