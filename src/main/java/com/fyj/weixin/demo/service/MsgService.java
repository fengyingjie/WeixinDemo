package com.fyj.weixin.demo.service;

import java.util.List;

import com.fyj.weixin.demo.bean.InMsgBean;
import com.fyj.weixin.demo.bean.OutMsgBean;
import com.fyj.weixin.demo.bean.OutTextMsgBean;

public interface MsgService {
	
	public OutTextMsgBean addTodo(InMsgBean inBean);

}
