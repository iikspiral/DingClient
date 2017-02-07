package cn.dc.ding.operate;

import cn.dc.ding.core.Cache;
import cn.dc.ding.core.DingClientFactory;
import cn.dc.ding.core.MyResponseHandler;
import cn.dc.ding.core.Operate;
import cn.dc.ding.entity.DingDepartment;
import cn.dc.ding.utils.StringUtils;
import cn.dc.ding.utils.SysProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by dongchen on 2017/1/24.
 */
public class DepartmentOperate extends Operate{
    private static DepartmentOperate instance = null;
    public static DepartmentOperate getOperate() throws Exception {
        if (instance == null) {
            synchronized (DepartmentOperate.class) {
                if (instance == null) {
                    instance = new DepartmentOperate(DingClientFactory.getInstance());
                }
            }
        }
        return instance;
    }

    private DepartmentOperate(DingClientFactory factory) throws Exception {
        super(factory);
    }

    /**
     * 获取公司内所有部门
     * @return
     */
    public List<DingDepartment> getDepartmentList() {
        try {
            // 查询缓存
            Object cache = Cache.getCache(SysProperties.DEPARTMENT_LIST_KEY);
            if (cache != null) {
                return (List<DingDepartment>) cache;
            }

            URI uri = new URIBuilder().setScheme("https").setHost("oapi.dingtalk.com").setPath("/department/list")
                    .setParameter("access_token", ACCESS_TOKEN)
                    .build();
            HttpGet httpget = new HttpGet(uri);
            MyResponseHandler myResponseHandler = new MyResponseHandler();
            String responseBody = client.execute(httpget, myResponseHandler);
            JSONObject jsonObject = JSON.parseObject(StringUtils.encode(responseBody));
            JSONArray department = jsonObject.getJSONArray("department");
            if (department == null) {
                return null;
            }
            List<DingDepartment> departments = JSON.parseArray(department.toJSONString(), DingDepartment.class);

            // 缓存数据
            if (departments.size() > 0) {
                Cache.cacheDate(SysProperties.DEPARTMENT_LIST_KEY, departments);
            }

            return departments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取公司id，通过这个id可以查到公司内所有员工
     * @return
     */
    public Long getCompanyId(){
        List<DingDepartment> departmentList = getDepartmentList();
        for (DingDepartment department : departmentList) {
            if (department.getParentid() == null) {
                Long id = department.getId();
                Cache.cacheDate(SysProperties.COMPANY_ID, id);
                return id;
            }
        }
        return null;
    }

}
