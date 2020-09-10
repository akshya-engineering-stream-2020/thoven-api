package com.hackathon.thoven;

import com.hackathon.thoven.controller.TestingController;
import com.hackathon.thoven.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ThovenApiApplicationTests {

    @Autowired
    private TestingController controller;

    @Autowired
    private UserController userController;

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
    }

}
