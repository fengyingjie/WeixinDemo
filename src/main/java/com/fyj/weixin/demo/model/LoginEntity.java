package com.fyj.weixin.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "loginTBL") 
public class LoginEntity implements Serializable {

	@Id
	@Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "ROOMID", nullable = false)
    private String ROOMID;
    
    @Column(name = "Name", nullable = true)
    private String name;

    @Column(name = "LOGINTIME", nullable = true)
    private String time;

}