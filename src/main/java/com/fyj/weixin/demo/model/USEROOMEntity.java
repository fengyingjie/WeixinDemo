package com.fyj.weixin.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(USEROOMEntityId.class)
@Table(name = "USEROOMTBL") 
public class USEROOMEntity implements Serializable{
	
	@Id
	@Column(name = "ROOMID", nullable = false)
    private String roomid;

    @Column(name = "USERID", nullable = true)
    private String userid;

    @Id
    @Column(name = "STARTTIME", nullable = true)
    private String starttime;
    
    @Column(name = "ENDTIME", nullable = true)
    private String endtime;
}
