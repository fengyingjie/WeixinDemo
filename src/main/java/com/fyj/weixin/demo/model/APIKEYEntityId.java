package com.fyj.weixin.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class APIKEYEntityId implements Serializable {

	@Id
	@Column(name = "APPID", nullable = false)
    private String appid;

	@Id
    @Column(name = "APIKEY", nullable = false)
    private String apikey;

	@Id
    @Column(name = "SECRETKEY", nullable = false)
    private String secretkey;
	
}