package com.example.takaful_assessment.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public abstract class BaseMapper {
    private final ApplicationContext applicationContext;
    private final Map<Class<?>, Object> mapperCache = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    protected <T> T getMapper(Class<T> clazz) {
        return (T) mapperCache.computeIfAbsent(clazz, applicationContext::getBean);
    }
}
