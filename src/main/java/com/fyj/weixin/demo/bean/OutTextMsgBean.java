package com.fyj.weixin.demo.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@XmlRootElement(name="xml")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutTextMsgBean extends OutMsgBean{
	
	@XmlElement
	protected String Content;
	
}
