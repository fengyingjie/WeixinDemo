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
public class VoiceMsgBean extends BaseMsgBean{
	
	@XmlElement
	protected String MediaId;
	
	@XmlElement
	protected String Format;
}
