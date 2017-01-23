package cn.dc.ding.operate;

import cn.dc.ding.entity.DingUser;

import java.util.List;

/**
 * Created by dongchen on 2017/1/23.
 */
public interface UserOperate {

    /**
     * 获取部门内的员工
     * @param id 部门id
     * @return 员工的集合
     */
    List<DingUser> getDepartmentMember(Long id);


}
