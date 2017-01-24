package cn.dc.ding.entity;

import java.util.List;

/**
 * Created by dongchen on 2017/1/23.
 */
public class DingUser {
    /**
     * {
     *  "active":true,
     *  "avatar":"http://static.dingtalk.com/media/lADOZpZdFs0CgM0Cfg_638_640.jpg",
     *  "department":[21016607,4324572,4324575,4324578],
     *  "dingId":"$:LWCP_v1:$QhG2ENkd9H0MzAUrGHStQQ==",
     *  "email":"",
     *  "isAdmin":true,
     *  "isBoss":true,
     *  "isHide":false,
     *  "isLeader":true,
     *  "jobnumber":"0005",
     *  "mobile":"13958175433",
     *  "name":"XXX",
     *  "openId":"iiQV6FY6cRUrgRX7WrhiPhXwiEiE",
     *  "order":229296509020304292,
     *  "position":"",
     *  "remark":"",
     *  "tel":"",
     *  "userid":"693910539800",
     *  "workPlace":""
     *  }
     */
    private Boolean active;
    private String avatar;
    private List<Long> department;
    private String dingId;
    private String email;
    private Boolean isAdmin;
    private Boolean isBoss;
    private Boolean isHide;
    private Boolean isLeader;
    private String jobnumber;
    private String mobile;
    private String name;
    private String openId;
    private String order;
    private String position;
    private String remark;
    private String tel;
    private String userid;
    private String workPlace;

    public DingUser() {}

    public DingUser(String i) {
        this.userid = i;
    }

    @Override
    public String toString() {
        return "DingUser{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getBoss() {
        return isBoss;
    }

    public void setBoss(Boolean boss) {
        isBoss = boss;
    }

    public Boolean getHide() {
        return isHide;
    }

    public void setHide(Boolean hide) {
        isHide = hide;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getDingId() {
        return dingId;
    }

    public void setDingId(String dingId) {
        this.dingId = dingId;
    }

    public Boolean getLeader() {
        return isLeader;
    }

    public void setLeader(Boolean leader) {
        isLeader = leader;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Long> getDepartment() {
        return department;
    }

    public void setDepartment(List<Long> department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
