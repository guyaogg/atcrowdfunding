package com.atguigu.crowd.CrowdTest;

import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.AssignMapper;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.pojo.Admin;
import com.atguigu.crowd.pojo.Role;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author guyao
 * @create 2021-07-31 20:55
 */
@SpringJUnitConfig(locations = {"classpath:spring-persist-mybatis.xml"})
public class CrowdTest {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AssignMapper assignMapper;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminService adminService;

    Logger logger = LoggerFactory.getLogger(CrowdTest.class);

    @Test
    public void serviceTest() throws SQLException {
//        Admin admin = new Admin(null, "jerry", "123456", "杰瑞", "jerry@atguigu.com", null);
//        System.out.println(adminService.saveAdmin(admin));
        adminService.removeAdmin(4);
    }
    @Test
    public void test() throws SQLException {
//        Connection conn = dataSource.getConnection();
//        System.out.println(conn);
//        System.out.println(mapper.selectByPrimaryKey(1));

        logger.info("info");
        logger.debug("debug");
        logger.warn("warn");
        logger.error("error");
        System.out.println(adminService);


    }

    /**
     * 测试MD5加密方法
     */
    @Test
    public void testMd5(){
        String source = "123456";
        String encoded = CrowdUtil.md5(source);
        String encoded2 = CrowdUtil.md5(source);
        logger.info(encoded);
        logger.info(Boolean.valueOf(encoded.equals(encoded2)).toString());

    }
    @Test
    public void testData(){
        for (int i = 160; i < 300; i++) {
            roleMapper.insert(new Role(null, "role"+i));
        }
        System.out.println(roleMapper.countByExample(null));

    }
    @Test
    public void testNew$(){
//        List<Role> selectAssign = roleMapper.selectAssign(6);
//        logger.info(selectAssign.toString());

        List<Integer> list = assignMapper.selectRoleAuthById(2);
        logger.info("查询列表为："+list);
//        int insertNewRoleList = adminMapper.insertNewRoleList(Arrays.asList(3,4,5,6,8),3);

    }

}
