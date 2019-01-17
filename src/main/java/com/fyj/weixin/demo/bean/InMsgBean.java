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
public class InMsgBean {
	
	@XmlElement
	protected String ToUserName;
	
	@XmlElement
	protected String FromUserName;
	
	@XmlElement
	protected String CreateTime;
	
	@XmlElement
	protected String MsgType;
	
	@XmlElement
	protected String Content;
	
	@XmlElement
	protected String MsgId;
	
	@XmlElement
	protected String MediaID;
	
	@XmlElement
	protected String Format;
	
	@XmlElement
	protected String Recognition;
	
}
