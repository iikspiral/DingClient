package cn.dc.ding.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongchen on 2017/1/23.
 */
public abstract class DingMsgHeader {
    private List<DingUser> users = null;
    private List<DingDepartment> departments = null;
    private String agentid = "35330198";

    public List<DingUser> getUsers() {
        return users;
    }

    public void setUsers(List<DingUser> users) {
        this.users = users;
    }

    public void setUsers(String s) {
        ArrayList<DingUser> list = new ArrayList<DingUser>();
        list.add(new DingUser(s));
        this.users = list;
    }

    public List<DingDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DingDepartment> departments) {
        this.departments = departments;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public JSONObject format(){
        JSONObject obj = new JSONObject();
        StringBuffer touser = new StringBuffer();
        StringBuffer toparty = new StringBuffer();
        if (users != null && users.size()>0){
            int usize = users.size();
            for (int i = 0; i < usize; i++) {
                touser.append(users.get(i).getUserid());
                if ((i + 1) != usize) {
                    touser.append("|");
                }
            }
            obj.put("touser", touser.toString());
        }
        if (departments != null && departments.size()>0){
            int dsize = departments.size();
            for (int i = 0; i < dsize; i++) {
                toparty.append(departments.get(i).getId());
                if ((i + 1) != dsize) {
                    toparty.append("|");
                }
            }
            obj.put("toparty", toparty.toString());
        }

        obj.put("agentid", getAgentid());

        return obj;
    }
}
