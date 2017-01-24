package cn.dc.ding.operate;

import cn.dc.ding.entity.DingMsg;
import cn.dc.ding.entity.DingMsgResponse;
import cn.dc.ding.exception.MsgException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by dongchen on 2017/1/24.
 */
public interface MsgOperate{
    DingMsgResponse sendMsg(DingMsg msg) throws URISyntaxException, IOException, MsgException;
}
