package cn.dc.ding.core;

import cn.dc.ding.exception.CropException;
import cn.dc.ding.operate.ContactsOperate;
import cn.dc.ding.operate.MsgOperate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

/**
 * Created by dongchen on 2017/1/23.
 */
public class DingClientFactory {

    public final static int CONTACTS_OPERATE = 1;
    public final static int MSG_OPERATE = 2;


    public Operate getOperate(int i) throws Exception {
        String access_token = getAccess_token();
        switch (i) {
            case CONTACTS_OPERATE:
                return new ContactsOperate(access_token);
            case MSG_OPERATE :
                return new MsgOperate(access_token);
            default :
                return null;
        }
    }

    public DingClientFactory() {
    }

    public DingClientFactory(String corpId, String corpSecret) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
    }

    private String corpId;
    private String corpSecret;

    public void setCorpInfo(String corpId, String corpSecret) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
    }

    /**
     * 连接钉钉服务器获取token
     *
     * @return
     * @throws IOException
     */
    public String getAccess_token() throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            URI uri = new URIBuilder().setScheme("https").setHost("oapi.dingtalk.com").setPath("/gettoken")
                    .setParameter("corpid", corpId).setParameter("corpsecret", corpSecret)
                    .build();
            HttpGet httpget = new HttpGet(uri);
            MyResponseHandler myResponseHandler = new MyResponseHandler();
            String responseBody = httpclient.execute(httpget, myResponseHandler);
            JSONObject jObj = JSON.parseObject(responseBody);

            // crop信息错误
            if (jObj.size() == 2) {
                //{"errcode":40089,"errmsg":"不合法的corpid或corpsecret"}
                if (corpId == null || corpSecret == null) {
                    throw new CropException("corpid和corpsecret存在在空值");
                } else {
                    throw new CropException("错误代码：" + jObj.getString("errcode") + "，" + "错误信息："
                            + new String(jObj.getString("errmsg").getBytes("iso8859-1"), "utf-8"));
                }
            }
            return jObj.getString("access_token");
        } finally {
            httpclient.close();
        }
    }



}
