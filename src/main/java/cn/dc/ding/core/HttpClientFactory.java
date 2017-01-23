package cn.dc.ding.core;

import cn.dc.ding.exception.CropException;
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
public class HttpClientFactory {
    private static HttpClientFactory instance = null;

    public static HttpClientFactory getInstance() {
        if (instance == null) {
            synchronized (HttpClientFactory.class) {
                if (instance == null) {
                    instance = new HttpClientFactory();
                }
            }
        }
        return instance;
    }

    private HttpClientFactory() {
    }

    private String corpid;
    private String corpsecret;
    private String agentid;

    public void setCorpInfo(String corpid, String corpsecret) {
        this.corpid = corpid;
        this.corpsecret = corpsecret;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public CloseableHttpClient createClient() {
        return HttpClients.createDefault();
    }

    private String access_token = null;

    public String getAccess_token() throws Exception{
        if (access_token == null) {
            access_token = getToken();
        }
        return access_token;
    }

    /**
     * 连接钉钉服务器获取token
     *
     * @return
     * @throws IOException
     */
    private String getToken() throws Exception{
        CloseableHttpClient httpclient = this.createClient();
        try {
            URI uri = new URIBuilder().setScheme("https").setHost("oapi.dingtalk.com").setPath("/gettoken")
                    .setParameter("corpid", corpid).setParameter("corpsecret", corpsecret)
                    .build();
            HttpGet httpget = new HttpGet(uri);
            MyResponseHandler myResponseHandler = new MyResponseHandler();
            String responseBody = httpclient.execute(httpget, myResponseHandler);
            JSONObject jObj = JSON.parseObject(responseBody);

            // crop信息错误
            if (jObj.size() == 2) {
                //{"errcode":40089,"errmsg":"不合法的corpid或corpsecret"}
                if (corpid == null || corpsecret == null) {
                    throw new CropException("corpid和corpsecret存在在空值");
                } else {
                    throw new CropException("错误代码：" + jObj.getString("errcode") + "，" +
                            "错误信息：" + jObj.getString("errmsg"));
                }
            }
            return jObj.getString("access_token");
        } finally {
            httpclient.close();
        }
    }
}
