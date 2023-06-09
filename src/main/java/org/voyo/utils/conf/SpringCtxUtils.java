package org.voyo.utils.conf;

import org.springframework.context.ApplicationContext;

public class SpringCtxUtils {
    private static ApplicationContext ctx;
    public static void setCtx(ApplicationContext ctx){
        SpringCtxUtils.ctx =ctx;
    }
    public static ApplicationContext getCtx(){
        return SpringCtxUtils.ctx;
    }
    public static <T> T getBean(Class<T> beanClass){
        return ctx.getBean(beanClass);
    }
    public static <T> T getBean(String beanName,Class<T> beanClass){
        return ctx.getBean(beanName,beanClass);
    }

}
