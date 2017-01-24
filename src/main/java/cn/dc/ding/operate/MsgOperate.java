package cn.dc.ding.operate;

import cn.dc.ding.core.DingClientFactory;
import cn.dc.ding.core.Operate;
import cn.dc.ding.entity.*;
import cn.dc.ding.exception.MsgException;
import com.alibaba.fastjson.JSON;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by dongchen on 2017/1/24.
 */
public class MsgOperate extends Operate{

    private static MsgOperate instance = null;
    public static MsgOperate getOperate() throws Exception {
        if (instance == null) {
            synchronized (DepartmentOperate.class) {
                if (instance == null) {
                    instance = new MsgOperate(DingClientFactory.getInstance());
                }
            }
        }
        return instance;
    }

    private MsgOperate(DingClientFactory factory) throws Exception {
        super(factory);
    }

    public DingMsgResponse sendMsg(DingMsg msg) throws URISyntaxException, IOException, MsgException {
        verifyMsg(msg);
        URI uri = new URIBuilder().setScheme("https").setHost("oapi.dingtalk.com").setPath("/message/send")
                .setParameter("access_token", ACCESS_TOKEN)
                .build();
        String responseBody = doPost(uri,msg);
        DingMsgResponse response = JSON.parseObject(responseBody, DingMsgResponse.class);
        return response;
    }

    /**
     * 验证消息的完整
     * @param msg
     */
    private void verifyMsg(DingMsg msg) {
        if (msg instanceof DingMsgHeader) {
            DingMsgHeader header = (DingMsgHeader) msg;
            List<DingDepartment> departments = header.getDepartments();
            List<DingUser> users = header.getUsers();
            if (departments == null && users == null) {
                throw new MsgException("用户列表或者部门列表二者至少填写一个");
            }

        } else {
            throw new MsgException("Msg为没有继承DingMsgHeader");
        }
    }
}
