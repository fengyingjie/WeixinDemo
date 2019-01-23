package com.fyj.weixin.command;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fyj.weixin.demo.model.LoginEntity;
import com.fyj.weixin.demo.model.LoginRepository;
import com.fyj.weixin.demo.model.USEROOMRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MeetingRoomUsedCommand {

	private boolean runflag = false;

	private String KEY_WORD = "会议室";
	
	private String text;
	
	@Autowired//自动从spring容器中加载userRepository
    private LoginRepository loginRepository;
	
	public MeetingRoomUsedCommand(JSONObject input) {
		
		String text = (String) input.get("text");
		if(!StringUtils.isEmpty(text) && text.indexOf(KEY_WORD)>0) {
			runflag = true;
		}
	}
	
	//"谁在??会议室"
	//"约一下??会议室??点到??"
	public String poccess() {
		
		String regEx1 = "(.*)谁在??会议室(.*)";
		Pattern pattern1 = Pattern.compile(regEx1);
		Matcher matcher1 = pattern1.matcher(text);
		boolean rs1 = matcher1.matches();
		
		String regEx2 = "(.*)约一下??会议室((.+)点(.*))到((.+)点(.*))(.*)";
		Pattern pattern2 = Pattern.compile(regEx2);
		Matcher matcher2 = pattern2.matcher(text);
		boolean rs2 = matcher2.matches();
		
		if(rs1) {
			String result="";
			int index = text.indexOf("会议室");
			String name = text.substring(index-2, index);
			log.debug("find user in " + name);
			List<LoginEntity> userList = loginRepository.findUserinRoom(name);
			for(LoginEntity user : userList){
				result = result + user.getName();
			}
			return result;
		}else if(rs2) {
		
			return "忙着呢，自己搞定";
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
			
		}
		
		return "没听懂，你说啥？";
		
	}
}
