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
public class BaseMsgBean {
	
	@XmlElement
	protected String ToUserName;
	
	@XmlElement
	protected String FromUserName;
	
	@XmlElement
	protected String CreateTime;
	
	@XmlElement
	protected String MsgType;
	
	@XmlElement
	protected String MsgId;
	
    /**
	 * 对象转XML
	 *
	 * @param obj 对象
	 * @return 字符串
	 */
	public final String xmlString(Class targetClass) {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    try {
	        JAXBContext context = JAXBContext.newInstance(targetClass);
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
