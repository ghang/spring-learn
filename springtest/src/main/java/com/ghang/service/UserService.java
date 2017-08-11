/*
 * Copyright © 2016 Tencent and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.ghang.service;

import com.ghang.dao.LoginLogDao;
import com.ghang.dao.UserDao;
import com.ghang.domain.LoginLog;
import com.ghang.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kevinzhguan
 * @date 2017/8/11 0011
 */
@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String username, String password) {
        return userDao.getMatchCount(username, password) > 0;
    }

    public User findUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(user.getCredits() + 5);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());

        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
