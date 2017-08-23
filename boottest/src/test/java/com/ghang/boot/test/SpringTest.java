package com.ghang.boot.test;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class SpringTest {
    @Test
    public void testResource() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:com/ghang/**/*.properties");
        System.out.println(resources.length);
        for (Resource resource : resources) {
            System.out.println(resource.getDescription());
        }
    }
}
