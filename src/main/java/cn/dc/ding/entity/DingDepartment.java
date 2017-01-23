package cn.dc.ding.entity;

/**
 * Created by dongchen on 2017/1/23.
 */
public class DingDepartment {
    private Long id;
    private String name;
    private Long parentid;

    public DingDepartment(){

    }
    public DingDepartment(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "DingDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentid=" + parentid +
                '}';
    }
}
