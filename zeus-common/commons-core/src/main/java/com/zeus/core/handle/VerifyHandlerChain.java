package com.zeus.core.handle;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VerifyHandlerChain implements InitializingBean {

    @Autowired
    private ApplicationContext applicationContext;

    public final List<VerifyHandler> verifyHandlerList = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        //获取IOC容器中类型为VerifyHandler的bean
        Map<String, VerifyHandler> beansOfType = applicationContext.getBeansOfType(VerifyHandler.class);
        //添加到验证集合中
        beansOfType.forEach((key, val) -> verifyHandlerList.add(val));

    }

    public List<Object> verify(List<Object> allObjList){
        List<Object> resultList = null;
        for (VerifyHandler each : verifyHandlerList){
            //对数据展开处理器链的全部调用，如果过程发现已无需调用验证的数据直接返回。
            resultList = each.verify(allObjList);
            if (CollectionUtils.isEmpty(resultList)){
                break;
            }
        }
        return resultList;
    }
}
