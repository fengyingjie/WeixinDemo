package com.fyj.weixin.demo.Controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fyj.weixin.Util.CheckoutUtil;
import com.fyj.weixin.demo.bean.InMsgBean;
import com.fyj.weixin.demo.bean.OutMsgBean;
import com.fyj.weixin.demo.bean.OutTextMsgBean;
import com.fyj.weixin.demo.model.LoginEntity;
import com.fyj.weixin.demo.model.LoginRepository;
import com.fyj.weixin.demo.service.MsgService;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RestController
public class HelloController {

	@Autowired//自动从spring容器中加载userRepository
    private LoginRepository loginRepository;
	
	@Autowired//自动从spring容器中加载userRepository
    private MsgService msgService;
	
	@GetMapping("/d2")
    public String index() {
		log.debug("d2 OK");
        return "d2";
    }
	
	@GetMapping(path="BasicService")
	public String basicService(String signature,String timestamp,String nonce,String echostr,
			HttpServletRequest request) {
	
		if(CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
			log.debug("checkSignature OK");
			return echostr;
		}else {
			log.debug("checkSignature NG");
			return "echostr Error";
		}
	}
	
	@PostMapping(path="BasicService")
	public @ResponseBody String receviceMessge(@RequestBody InMsgBean inBean) {
	
		//OutTextMsgBean outBean = new OutTextMsgBean();
		
		//InMsgBean inBean = new InMsgBean();
		//inBean.setRecognition("语音识别很简单");
		log.debug("inBean:"+inBean.toString());
		
//		outBean.setFromUserName(inBean.getToUserName());
//		outBean.setToUserName(inBean.getFromUserName());
//		outBean.setCreateTime(inBean.getCreateTime());
//		outBean.setMsgType("text");
//		outBean.setContent(inBean.getRecognition());
		//outBean.setMsgId(inBean.getRecognition());
		
		OutTextMsgBean outBean = msgService.addTodo(inBean);
		log.debug("outBean:"+outBean.toString());
		return outBean.xmlString(outBean.getClass());
	}
	
    @GetMapping(path="/add2") // “/add”路径映射到addNewUser方法上
    public @ResponseBody String addNewUser2 () {
        // @ResponseBody 表示返回的string是一个回应（response），不是一个视图
        // @RequestParam 表示接收的参数可以是get或post

    	LoginEntity n = new LoginEntity();
        n.setId("test");
        n.setName("name");
        n.setTime(String.valueOf(System.currentTimeMillis()));

    	loginRepository.save(n);
        return "Saved";
    }
    
    @GetMapping(path="/add") // “/add”路径映射到addNewUser方法上
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody 表示返回的string是一个回应（response），不是一个视图
        // @RequestParam 表示接收的参数可以是get或post

    	LoginEntity n = new LoginEntity();
        n.setId("test");
        n.setName("name");
        n.setTime(String.valueOf(System.currentTimeMillis()));

    	loginRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<LoginEntity> getAllUsers() {
        // 返回一个json类型的user
        return loginRepository.findAll();
    }
    
    
    @GetMapping("/index")
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView("index");

//        List<Fans> fans = new ArrayList<>();
//        fans.add(new Fans(1L, "zhangsan"));
//        fans.add(new Fans(2L, "lis"));
//        fans.add(new Fans(3L, "wangwu"));

        //modelAndView.addObject("user", new User(1L, "mengday", "xxx@gmail.com", fans, true, new Date()));

        return modelAndView;
    }
    
	@RequestMapping(path="/userLogin")
	public @ResponseBody String userLogin(String ACTION,String ID,String LOGINTIME,
			HttpServletRequest request) {
		
		//request.getParameter("ACTION")
		log.debug("ACTION:"+ACTION + ",ID:"+ID + ",LOGINTIME:"+LOGINTIME);
		LoginEntity loginEn = new LoginEntity();
		loginEn.setId(ID);
		loginEn.setName(ID);
		loginEn.setROOMID("00202001");
		
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格
		loginEn.setTime(dateFormat.format(now));
		loginRepository.save(loginEn);
		//OutTextMsgBean outBean = new OutTextMsgBean();
		
		//InMsgBean inBean = new InMsgBean();
		//inBean.setRecognition("语音识别很简单");
		
		
//		outBean.setFromUserName(inBean.getToUserName());
//		outBean.setToUserName(inBean.getFromUserName());
//		outBean.setCreateTime(inBean.getCreateTime());
//		outBean.setMsgType("text");
//		outBean.setContent(inBean.getRecognition());
		//outBean.setMsgId(inBean.getRecognition());
		
		//log.debug("outBean:"+outBean.toString());
		return "";
	}
}