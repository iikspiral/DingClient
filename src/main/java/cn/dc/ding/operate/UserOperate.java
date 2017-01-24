package cn.dc.ding.operate;

import cn.dc.ding.core.Cache;
import cn.dc.ding.core.DingClientFactory;
import cn.dc.ding.core.MyResponseHandler;
import cn.dc.ding.core.Operate;
import cn.dc.ding.entity.DingDepartment;
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

    private static UserOperate instance = null;
    public static UserOperate getOperate() throws Exception {
        if (instance == null) {
            synchronized (UserOperate.class) {
                if (instance == null) {
                    instance = new UserOperate(DingClientFactory.getInstance());
                }
            }
        }
        return instance;
    }

    private UserOperate(DingClientFactory factory) throws Exception {
        super(factory);
    }

    /**
     * 根据部门id查询部门下的所有员工
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

    /**
     * 获取公司内所有员工信息
     * @return
     */
    public List<DingUser> getAllUser() throws Exception {
        DepartmentOperate dOperate = DepartmentOperate.getOperate();
        Long companyId = dOperate.getCompanyId();
        if (companyId == null) {
            return null;
        }
        return getDepartmentMember(companyId);
    }

    /**
     * 根据名称获取钉钉userid
     * @param name
     * @return
     * @throws Exception
     */
    public DingUser findUser(String name) throws Exception {
        return findUser(name, null);
    }

    /**
     * 根据部门id，用户名称查询用户信息
     * @param departmentId
     * @param name
     * @return
     * @throws Exception
     */
    public DingUser findUser(Long departmentId, String name) throws Exception {
        // 部门号为空，直接搜索全公司
        if (departmentId == null) {
            return findUser(name, null);

        }
        // 获取部门内员工
        List<DingUser> member = getDepartmentMember(departmentId);
        for (DingUser user : member) {
            if (name.equals(user.getName())) {
                return user;
            }
        }
        // 部门没有此人
        return null;
    }

    /**
     * 通过用户名称和部门名称查询用户信息
     * @param dname
     * @param name
     * @return
     * @throws Exception
     */
    public DingUser findUser(String name, String dname) throws Exception {
        if (name == null) {
            return null;
        }
        DepartmentOperate dOperate = DepartmentOperate.getOperate();
        List<DingDepartment> departmentList = dOperate.getDepartmentList();
        for (DingDepartment department : departmentList) {
            if (dname != null && !dname.equals(department.getName())) {
                continue;
            }
            DingUser user = findUser(department.getId(), name);
            if (user != null) {
                return user;
            }
        }
        // 公司内没有这个人
        return null;
    }


}
