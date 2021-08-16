package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.pojo.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**考虑到仅包含账号和密码，为此扩展user
 * @author guyao
 * @create 2021-08-07 22:18
 */
public class SecurityAdmin extends User {

    private static final long serialVersionUID = 1L;
    // 原始数据的对象
    private Admin originalAdmin;
    public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities) {
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);
        this.originalAdmin = originalAdmin;
        // 将原始数据中的密码擦除，提高安全性
        this.originalAdmin.setUserPswd(null);
    }

    /**
    * @Author guyaogg
    * 对外开放原始数据的拿取
    */

    public Admin getOriginalAdmin() {

        return originalAdmin;
    }


}
