/*
 * Copyright © 2016 Tencent and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.ghang.service;

import com.ghang.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author kevinzhguan
 * @date 2017/8/11 0011
 */
@ContextConfiguration("classpath*:/ghang-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private UserService userService;

    @Test
    public void testHasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "admin");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void testFindUserByUserName() throws Exception {
        User user = userService.findUserByUsername("admin");
        assertEquals(user.getUsername(), "admin");
    }

    @Test
    public void testAddLoginLog() {
        User user = userService.findUserByUsername("admin");
        user.setUserId(1);
        user.setUsername("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
