package com.mf.server.aware;

import com.mf.server.calculate.Calculator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CalculatorRouter implements ApplicationContextAware {

    private Map<String, Calculator> map;

    /**
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException
     * @des 获取所有实现了 Calculator 接口 对象
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        map = applicationContext.getBeansOfType(Calculator.class);
    }

    /**
     *
     * @param key
     * @return 根据 bean name 获取对象
     */
    public Calculator getService(String key){
        return map.get(key);
    }
}
