package cn.dc.ding.core;

import cn.dc.ding.entity.DingMsg;
import cn.dc.ding.exception.GetTokenException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URI;

/**
 * Created by dongchen on 2017/1/24.
 */
public class Operate {
    protected String ACCESS_TOKEN = null;
    private DingClientFactory factory;

    public Operate(DingClientFactory factory) throws Exception {
        this.factory = factory;
        try {
            this.ACCESS_TOKEN = factory.getAccess_token();
        } catch (IOException e) {
            throw new GetTokenException("获取access_token异常：" + e);
        }
    }

    protected String doPost(URI uri, DingMsg msg) throws IOException {
        CloseableHttpClient client = factory.createClient();
        HttpPost httpPost = new HttpPost(uri);
        StringEntity entity = new StringEntity(msg.getJsonString(), ContentType.create("application/json", "UTF-8"));
        httpPost.setEntity(entity);
        MyResponseHandler myResponseHandler = new MyResponseHandler();
        String execute = client.execute(httpPost, myResponseHandler);
        client.close();
        return execute;
    }

    protected String doGet(URI uri) throws IOException {
        CloseableHttpClient client = factory.createClient();
        HttpGet httpGet = new HttpGet(uri);
        MyResponseHandler myResponseHandler = new MyResponseHandler();
        String execute = client.execute(httpGet, myResponseHandler);
        client.close();
        return execute;
    }
}
