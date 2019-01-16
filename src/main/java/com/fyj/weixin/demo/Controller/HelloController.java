package com.fyj.weixin.demo.Controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fyj.weixin.Util.CheckoutUtil;
import com.fyj.weixin.demo.bean.TextMsgBean;
import com.fyj.weixin.demo.model.LoginEntity;
import com.fyj.weixin.demo.model.LoginRepository;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RestController
public class HelloController {

	@Autowired//自动从spring容器中加载userRepository
    private LoginRepository loginRepository;
	
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
	public @ResponseBody String receviceMessge(@RequestBody TextMsgBean inBean) {
	
		TextMsgBean outBean = new TextMsgBean();
		log.debug("inBean:"+inBean.toString());
		
		outBean.setFromUserName(inBean.getToUserName());
		outBean.setToUserName(inBean.getFromUserName());
		outBean.setCreateTime(inBean.getCreateTime());
		outBean.setMsgType("text");
		outBean.setContent("text1");
		outBean.setMsgId(inBean.getMsgId());
		
		log.debug("outBean:"+outBean.toString());
		return outBean.xmlString();
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
}