package cn.dc.ding.operate;

import cn.dc.ding.core.Operate;
import cn.dc.ding.entity.DingMsg;
import cn.dc.ding.entity.DingMsgResponse;
import cn.dc.ding.exception.MsgException;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by dongchen on 2017/3/6.
 */
public class RobotOperate extends Operate {
    // Hook地址
    private String hookUrl;

    public void setHookUrl(String hookUrl) {
        this.hookUrl = hookUrl;
    }

    public DingMsgResponse sendTextMsg(DingMsg msg) throws URISyntaxException, IOException, MsgException {
        String responseBody = doPost(this.hookUrl, msg);
        return JSON.parseObject(responseBody, DingMsgResponse.class);
    }


}
