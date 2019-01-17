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
@IdClass(APIKEYEntityId.class)
@Table(name = "APIKEYTBL") 
public class APIKEYEntity implements Serializable {

	@Id
	@Column(name = "APPID", nullable = false)
    private String appid;

	@Id
    @Column(name = "APIKEY", nullable = false)
    private String apikey;

	@Id
    @Column(name = "SECRETKEY", nullable = false)
    private String secretkey;
	
    @Column(name = "USEDTIME", nullable = false)
    private String usedtime;

}