/*
 * Copyright © 2016 Tencent and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.ghang.dao;

import com.ghang.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author kevinzhguan
 * @date 2017/8/11 0011
 */
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String username, String password) {
        String sql = "SELECT count(*) FROM t_user WHERE username =? AND password =? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, Integer.class);
    }

    public User getUserByUsername(final String username) {
        String sql = "SELECT * FROM t_user WHERE username =? ";
        final User user = new User();
        jdbcTemplate.query(sql, new Object[]{username}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(username);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        String sql = "UPDATE t_user SET last_visit =?,last_ip =?,credits =? WHERE username =? ";
        jdbcTemplate.update(sql, user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUsername());
    }
}
