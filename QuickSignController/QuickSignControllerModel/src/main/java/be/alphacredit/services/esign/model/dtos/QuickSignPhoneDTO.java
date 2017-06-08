package be.alphacredit.services.esign.model.dtos;

import java.util.*;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "cell", "cells", "landline", "landlines" })
public class QuickSignPhoneDTO
{
  private String cell;
  private QuickSignCellDTO cells;
  private String landline;
  private QuickSignLandlineDTO landlines;

  public QuickSignPhoneDTO()
  {
    super();
    
  }

 
  public QuickSignPhoneDTO(String cell, QuickSignCellDTO cells, String landline, QuickSignLandlineDTO landlines, Map<String, Object> additionalProperties)
  {
    super();
    this.cell = cell;
    this.cells = cells;
    this.landline = landline;
    this.landlines = landlines;
    this.additionalProperties = additionalProperties;
  }

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();


  @JsonProperty("cell")
  public String getCell()
  {
    return cell;
  }

  public void setCell(String cell)
  {
    this.cell = cell;
  }

  @JsonProperty("cells")
  public QuickSignCellDTO getCells()
  {
    return cells;
  }

  public void setCells(QuickSignCellDTO cells)
  {
    this.cells = cells;
  }

  @JsonProperty("landline")
  public String getLandline()
  {
    return landline;
  }

  public void setLandline(String landline)
  {
    this.landline = landline;
  }

  @JsonProperty("landlines")
  public QuickSignLandlineDTO getLandlines()
  {
    return landlines;
  }

  public void setLandlines(QuickSignLandlineDTO landlines)
  {
    this.landlines = landlines;
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
