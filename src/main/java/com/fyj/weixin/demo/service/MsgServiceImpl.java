package com.fyj.weixin.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.fyj.weixin.demo.model.LoginEntity;
import com.fyj.weixin.demo.model.LoginRepository;
import com.fyj.weixin.demo.model.USEROOMRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "msgService")
public class MsgServiceImpl implements MsgService {

	private AipNlp client;
	
	private List<HashMap<String,String>> BaiDuKeyList;
	
	@Autowired//自动从spring容器中加载userRepository
    private USEROOMRepository useroomRepository;
	
	@Autowired//自动从spring容器中加载userRepository
    private APIKEYRepository apiKeyRepository;
	
	@Autowired//自动从spring容器中加载loginRepository
    private LoginRepository loginRepository;
	
	
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
		
		
	    String text = inBean.getRecognition().trim();
	    log.debug("text:"+ text);
		//HashMap<String,String> apiMap = BaiDuKeyList.get((int) ((new Date().getTime()/200) % 5));
	    APIKEYEntity apikey = apiKeyRepository.getNextApiKey().get(0);
        client = new AipNlp(apikey.getAppid(), apikey.getApikey(), apikey.getSecretkey());
        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        client.setHttpProxy("10.167.251.83", 8080);// 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        
        //apikey.setUsedtime(String.valueOf(System.currentTimeMillis()));
        apiKeyRepository.updateApiKeyTime(apikey);
	    // 传入可选参数调用接口
	    HashMap<String, Object> options = new HashMap<String, Object>();
	    options.put("mode", 1);
	    
	    // 依存句法分析
	    JSONObject res = client.depParser(text, options);
	    log.debug("res:"+ res.toString(2));
	    
	    
	    String regEx1 = "(.*)谁在(.*)会议室(.*)";
		Pattern pattern1 = Pattern.compile(regEx1);
		Matcher matcher1 = pattern1.matcher(text);
		boolean rs1 = matcher1.matches();
		
		String regEx2 = "(.*)约一下??会议室((.+)点(.*))到((.+)点(.*))(.*)";
		Pattern pattern2 = Pattern.compile(regEx2);
		Matcher matcher2 = pattern2.matcher(text);
		boolean rs2 = matcher2.matches();
		
		String resultStr="";
		if(rs1) {
			
			int index = text.indexOf("会议室");
			String name = text.substring(index-2, index);
			log.debug("find user in " + name);
			List<LoginEntity> userList = loginRepository.findUserinRoom(name);
			for(LoginEntity user : userList){
				resultStr = resultStr + user.getName();
			}
			result.setContent(resultStr);
			return result;
		}else if(rs2) {
		
			resultStr = "忙着呢，自己搞定！";
//			int index = text.indexOf("会议室");
//			String name = text.substring(index-2, index);
//			
//			text = text.replace("一", "1").replace("二", "2").
//					replace("三", "3").replace("四", "4").
//					replace("五", "5").replace("六", "6").
//					replace("七", "7").replace("八", "8")
//					.replace("九", "9").replace("十", "1");
//			index = text.indexOf("(([].+)点(.*))");
//
//			String regEx3 = "((\\d+)点(\\d*))";
//			Pattern pattern3 = Pattern.compile(regEx2);
//			Matcher matcher3 = pattern3.matcher(text);
//			matcher3.toMatchResult();
			result.setContent(resultStr);
			return result;
		}
		
		resultStr = "没听懂，你说啥？" + text + "么？";
		result.setContent(resultStr);
	    //System.out.println();
		return result;
	}
}
