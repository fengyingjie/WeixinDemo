package com.fyj.weixin.demo.model;

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
public class LoginEntity {

	@Id
	@Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "Name", nullable = true)
    private String name;

    @Column(name = "LoginTime", nullable = true)
    private String time;

}