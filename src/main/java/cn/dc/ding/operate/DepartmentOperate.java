package cn.dc.ding.operate;

import cn.dc.ding.entity.DingDepartment;

import java.util.List;

/**
 * Created by dongchen on 2017/1/23.
 */
public interface DepartmentOperate {

    /**
     * 获取公司内的所有部门
     * @return 部门列表
     */
    List<DingDepartment> getDepartmentList();

}
