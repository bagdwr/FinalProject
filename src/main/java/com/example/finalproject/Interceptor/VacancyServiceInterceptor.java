package com.example.finalproject.Interceptor;

import lombok.extern.java.Log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@VacancyInterceptor
@Log
public class VacancyServiceInterceptor {
    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Exception{
        log.info (invocationContext.getMethod()+"_________Before method");
        return invocationContext.proceed();
    }
}
