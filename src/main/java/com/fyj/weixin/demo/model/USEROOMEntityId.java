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
public class USEROOMEntityId implements Serializable {

	@Id
	@Column(name = "ROOMID", nullable = false)
    private String roomid;

	@Id
    @Column(name = "STARTTIME", nullable = false)
    private String starttime;

}