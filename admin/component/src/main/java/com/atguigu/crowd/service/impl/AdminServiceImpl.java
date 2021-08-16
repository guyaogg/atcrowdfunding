package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseException;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.exception.QueryFailedException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.pojo.Admin;
import com.atguigu.crowd.pojo.AdminExample;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Objects;
import java.util.logging.SimpleFormatter;

import static com.atguigu.crowd.constant.CrowdConstant.*;

/**
 * @author guyao
 * @create 2021-08-01 13:44
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public int saveAdmin(Admin admin) {
        // 密码加密
        String userPswd = admin.getUserPswd();

//        String md5Pswd = CrowdUtil.md5(userPswd);
        // 使用加盐值加密方法
        String saltEncodePswd = passwordEncoder.encode(userPswd);
        admin.setUserPswd(saltEncodePswd);

        // 生成创建时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);

        int insert = 0;
        try {
            insert = adminMapper.insert(admin);
        } catch (Exception e) {
            loginAccess(e,MESSAGE_LOGIN_ACCT_ALREADY_IN_USE.getStr());
        }
//        int i = 12 /0;
        return insert;
    }

    /**
     * 处理重复名异常
     * @param e
     */
    private void loginAccess(Exception e,String message){
        logger.info("大的错误类为：" + e.getClass());

        if (e instanceof DuplicateKeyException) {
            logger.info("错误的类为：" + LoginAcctAlreadyInUseException.class);
            throw new LoginAcctAlreadyInUseException(message);
        }
    }

    /**
     * 依照id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public Admin getAdminById(Integer id) {
        Admin admin = null;
        // 用户不存在
        if (id == null || id < 1 || (admin = adminMapper.selectByPrimaryKey(id)) == null)
            throw new QueryFailedException(MESSAGE_QUERY_FAIL.getStr());
        return admin;

    }

    @Override
    public int update(Admin admin) {
        // 有选择的更新对null值不更新
        int updateCount = 0;
        try {
            updateCount = adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            loginAccess(e,"更新的登录账号已经存在");
        }
        return updateCount;
    }



    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public Integer removeAdmin(Integer id) {
        Integer delete = adminMapper.deleteByPrimaryKey(id);
//                int i = 12 /0;
        return delete;
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(null);
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String password) {
        // 根据loginAcct查询账号
        if (loginAcct == null || loginAcct.length() == 0)
            throw new LoginFailedException(MESSAGE_STRING_INVALIDATE.getStr());
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // 查询是否为null
        // 如果为null返回异常
        if (admins == null || admins.size() == 0)
            throw new LoginFailedException(MESSAGE_LOGIN_FAIL.getStr());
        if (admins.size() > 1)
            throw new RuntimeException(MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE.getStr());
        Admin admin = admins.get(0);
        if (admin == null)
            throw new LoginFailedException(MESSAGE_LOGIN_FAIL.getStr());
        // 不为null对密码加密
        String userPswd = admin.getUserPswd();
        String PswdForm = CrowdUtil.md5(password);
        // 查看加密后密码是否一样
        // 不一样返回异常
        if (!Objects.equals(userPswd, PswdForm))
            throw new LoginFailedException(MESSAGE_LOGIN_FAIL.getStr());

        // 一样返回admin对象
        return admin;

    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize, Integer pageCount) {
        // 先调用PageHelper的静态分页方法
        // 体现"非侵入式"设计
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);
        PageInfo pageInfo = new PageInfo(list, pageCount);

        // 封装到PageInfo对象
        logger.info(list.toString());

        return pageInfo;
    }
}
