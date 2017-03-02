package cn.dc.ding.core;

import cn.dc.ding.entity.DingMsg;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

/**
 * Created by dongchen on 2017/1/24.
 */
public class Operate {
    protected String ACCESS_TOKEN = null;

    public Operate(String accessToken) throws Exception {
        this.ACCESS_TOKEN = accessToken;
    }

    protected String doPost(URI uri, DingMsg msg) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        StringEntity entity = new StringEntity(msg.getJsonString(), ContentType.create("application/json", "UTF-8"));
        httpPost.setEntity(entity);
        MyResponseHandler myResponseHandler = new MyResponseHandler();
        String execute = client.execute(httpPost, myResponseHandler);
        client.close();
        return execute;
    }

    protected String doGet(URI uri) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        MyResponseHandler myResponseHandler = new MyResponseHandler();
        String execute = client.execute(httpGet, myResponseHandler);
        client.close();
        return execute;
    }
}
