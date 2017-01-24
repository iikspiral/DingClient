package cn.dc.ding.core;

import cn.dc.ding.entity.DingMsg;
import cn.dc.ding.exception.GetTokenException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;

/**
 * Created by dongchen on 2017/1/24.
 */
public class Operate implements Closeable {
    protected CloseableHttpClient client = null;
    protected String ACCESS_TOKEN = null;

    public Operate(HttpClientFactory factory) throws Exception {
        this.client = factory.createClient();
        try {
            this.ACCESS_TOKEN = factory.getAccess_token();
        } catch (IOException e) {
            throw new GetTokenException("获取access_token异常：" + e);
        }
    }

    public void close() throws IOException {
        if (client != null) {
            client.close();
        }
    }


    protected String doPost(URI uri, DingMsg msg) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        StringEntity entity = new StringEntity(msg.getJsonString(), ContentType.create("application/json", "UTF-8"));
        httpPost.setEntity(entity);
        MyResponseHandler myResponseHandler = new MyResponseHandler();
        return client.execute(httpPost, myResponseHandler);
    }
}
