package cn.dc.ding.core;

import cn.dc.ding.exception.GetTokenException;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.Closeable;
import java.io.IOException;

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
}
