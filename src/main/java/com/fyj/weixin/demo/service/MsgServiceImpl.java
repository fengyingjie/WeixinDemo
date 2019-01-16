package com.fyj.weixin.demo.service;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.baidu.aip.nlp.AipNlp;
import com.fyj.weixin.demo.bean.InMsgBean;
import com.fyj.weixin.demo.bean.OutMsgBean;
import com.fyj.weixin.demo.bean.OutTextMsgBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "msgService")
public class MsgServiceImpl implements MsgService {

	private AipNlp client;
	
	public MsgServiceImpl() {
		
		// 时间长了以后会不会Token过期
		// 初始化一个AipNlp
		log.debug("init MsgServiceImpl");
        client = new AipNlp("15433602", "InOxOXpmnBH9bDqGZsF7sRI9", "9LjGoh7yOkGSA1yE2AQFQYG9PbRfdytn");

	}

	@Override
	public OutTextMsgBean addTodo(InMsgBean inBean) {
		
		OutTextMsgBean result = new OutTextMsgBean();
		result.setFromUserName(inBean.getToUserName());
		result.setToUserName(inBean.getFromUserName());
		result.setCreateTime(inBean.getCreateTime());
		result.setMsgType("text");
		
		
	    String text = inBean.getRecognition();
	    log.debug("text:"+ text);
	    // 传入可选参数调用接口
	    HashMap<String, Object> options = new HashMap<String, Object>();
	    options.put("mode", 1);
	    
	    // 依存句法分析
	    JSONObject res = client.depParser(text, options);
	    log.debug("res:"+ res.toString(2));
	    result.setContent(res.toString(2));
	    //System.out.println();
		return result;
	}
	

}
