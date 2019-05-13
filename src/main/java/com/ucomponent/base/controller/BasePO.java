package com.ucomponent.base.controller;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class BasePO extends BaseLayuiVO {
  @Column(updatable=false)
  private int orgId ;
  @Column
  private String codesetGstatus = "G_STATUS_USE";
  @Column
  private Date createDatetime = new Date();
  @Column
  private Date updateDatetime = new Date();
  @Column(updatable=false)
  private int createUserId;
  @Column
  private int updateUserId;
}
