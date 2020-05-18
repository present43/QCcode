package com.qingcheng.pojo.goods;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//@Data
@Table(name = "tb_template")
public class Template implements Serializable {

  @Id
  private Integer id;

  private String name;

  private Integer specNum;

  private Integer paraNum;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Integer getSpecNum() {
    return specNum;
  }

  public void setSpecNum(Integer specNum) {
    this.specNum = specNum;
  }


  public Integer getParaNum() {
    return paraNum;
  }

  public void setParaNum(Integer paraNum) {
    this.paraNum = paraNum;
  }

}
