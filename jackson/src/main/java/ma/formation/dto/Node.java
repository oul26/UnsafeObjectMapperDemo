package ma.formation.dto;

import java.util.Map;

public class Node {

  private String id;

  private String lbl;

  private String type;

  private Map<String, Object> meta;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLbl() {
    return lbl;
  }

  public void setLbl(String lbl) {
    this.lbl = lbl;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<String, Object> getMeta() {
    return meta;
  }

  public void setMeta(Map<String, Object> meta) {
    this.meta = meta;
  }
}
