package com.fyj.weixin.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.aip.nlp.AipNlp;
import com.fyj.weixin.demo.bean.InMsgBean;
import com.fyj.weixin.demo.bean.OutMsgBean;
import com.fyj.weixin.demo.bean.OutTextMsgBean;
import com.fyj.weixin.demo.model.APIKEYEntity;
import com.fyj.weixin.demo.model.APIKEYRepository;
import com.fyj.weixin.demo.model.LoginRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "msgService")
public class MsgServiceImpl implements MsgService {

	private AipNlp client;
	
	private List<HashMap<String,String>> BaiDuKeyList;
	
	@Autowired//自动从spring容器中加载userRepository
    private APIKEYRepository apiKeyRepository;
	
	public MsgServiceImpl() {
		
		BaiDuKeyList = new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String> apiMap = new HashMap<String,String>();
		apiMap.put("AppID","15433602");
		apiMap.put("APIKey","InOxOXpmnBH9bDqGZsF7sRI9");
		apiMap.put("SecretKey","9LjGoh7yOkGSA1yE2AQFQYG9PbRfdytn");
		BaiDuKeyList.add(apiMap);
		
		apiMap.put("AppID","15435417");
		apiMap.put("APIKey","jF6aaHFsCRp7qbxXuHTl3X1Y");
		apiMap.put("SecretKey","rHcOHwnSNpUpEtQcoqPbtDONOAcGZRIu");
		BaiDuKeyList.add(apiMap);
		
		apiMap.put("AppID","15435429");
		apiMap.put("APIKey","PU21tUq0jg2GyTFUfRaF13PE");
		apiMap.put("SecretKey","4r2GWxd6dRGBzqgOpW6p90VR2YqqQNH1");
		BaiDuKeyList.add(apiMap);
		
		apiMap.put("AppID","15435434");
		apiMap.put("APIKey","iXEQkwmnBydnQO9izYUcfikj");
		apiMap.put("SecretKey","yPhurNi29jXwIbelvTqMcYfNEHyTffD5");
		BaiDuKeyList.add(apiMap);
		
		apiMap.put("AppID","15435449");
		apiMap.put("APIKey","s2X6NfUtFrAKREYOykvvRMmq");
		apiMap.put("SecretKey","daRUfva12U3SxnlX7m8GNNiiZ09yhXeS");
		BaiDuKeyList.add(apiMap);

		// 初始化一个AipNlp
		log.debug("init MsgServiceImpl");

	}

	@Override
	@Transactional
	public OutTextMsgBean addTodo(InMsgBean inBean) {
		
		OutTextMsgBean result = new OutTextMsgBean();
		result.setFromUserName(inBean.getToUserName());
		result.setToUserName(inBean.getFromUserName());
		result.setCreateTime(inBean.getCreateTime());
		result.setMsgType("text");
		
		
	    String text = inBean.getRecognition();
	    log.debug("text:"+ text);
		//HashMap<String,String> apiMap = BaiDuKeyList.get((int) ((new Date().getTime()/200) % 5));
	    APIKEYEntity apikey = apiKeyRepository.getNextApiKey().get(0);
        client = new AipNlp(apikey.getAppid(), apikey.getApikey(), apikey.getSecretkey());
        //apikey.setUsedtime(String.valueOf(System.currentTimeMillis()));
        apiKeyRepository.updateApiKeyTime(apikey);
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
