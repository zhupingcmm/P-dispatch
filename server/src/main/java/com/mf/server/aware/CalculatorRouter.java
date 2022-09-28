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
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        map = applicationContext.getBeansOfType(Calculator.class);
    }

    public Calculator getService(String key){
        return map.get(key);
    }
}
