package cn.dc.ding.operate;

import cn.dc.ding.core.Cache;
import cn.dc.ding.core.HttpClientFactory;
import cn.dc.ding.core.MyResponseHandler;
import cn.dc.ding.core.Operate;
import cn.dc.ding.entity.DingUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by dongchen on 2017/1/23.
 */
public class UserOperate extends Operate{

    public UserOperate(HttpClientFactory factory) throws Exception {
        super(factory);
    }

    /**
     * url:
     * @param id 部门id
     * @return
     */
    public List<DingUser> getDepartmentMember(Long id) {
        try {
            if (id == null) {
                return null;
            }

            // 查询缓存
            Object cache = Cache.getCache(id.toString());
            if (cache != null) {
                return (List<DingUser>) cache;
            }

            URI uri = new URIBuilder().setScheme("https").setHost("oapi.dingtalk.com").setPath("/user/list")
                    .setParameter("access_token", ACCESS_TOKEN).setParameter("department_id", id.toString())
                    .build();
            HttpGet httpget = new HttpGet(uri);
            MyResponseHandler myResponseHandler = new MyResponseHandler();
            String responseBody = client.execute(httpget, myResponseHandler);
            JSONObject jsonObject = JSON.parseObject(responseBody);
            JSONArray userlist = jsonObject.getJSONArray("userlist");
            List<DingUser> users = JSON.parseArray(userlist.toJSONString(), DingUser.class);

            // 缓存数据
            if (users.size()>0) {
                Cache.cacheDate(id.toString(), users);
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
