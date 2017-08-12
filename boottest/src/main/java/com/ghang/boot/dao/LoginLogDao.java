/*
 * Copyright © 2016 Tencent and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.ghang.boot.dao;

import com.ghang.boot.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author kevinzhguan
 * @date 2017/8/11 0011
 */
@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertLoginLog(LoginLog loginLog) {
        String sql = "INSERT INTO t_login_log(user_id,ip,login_datetime) VALUES(?,?,?)";
        jdbcTemplate.update(sql, loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate());
    }
}
