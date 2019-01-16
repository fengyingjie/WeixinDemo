package com.fyj.weixin.demo.bean;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@XmlRootElement(name="xml")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
    /**
	 * 对象转XML
	 *
	 * @param obj 对象
	 * @return 字符串
	 */
	public String xmlString() {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    try {
	        JAXBContext context = JAXBContext.newInstance(this.getClass());
	        Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
	        marshaller.marshal(this, out);
	        try {
	            return new String(out.toByteArray(), "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
