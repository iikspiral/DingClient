package cn.dc.ding.spring;

import cn.dc.ding.core.DingClientFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by dongchen on 2017/1/24.
 */
public class OperateFactoryBean implements FactoryBean<DingClientFactory>, InitializingBean {

    /**
     * 在设置完参数时候触发
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {

    }

    public DingClientFactory getObject() throws Exception {
        return null;
    }

    public Class<?> getObjectType() {
        return null;
    }

    public boolean isSingleton() {
        return false;
    }
}
