package com.fyj.weixin.demo.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="xml")
public class TextMsgBean {
	
	@XmlElement
	private String ToUserName;
	
	@XmlElement
	private String FromUserName;
	
	@XmlElement
	private String CreateTime;
	
	@XmlElement
	private String MsgType;
	
	@XmlElement
	private String Content;
	
	@XmlElement
	private String MsgId;
	
}
