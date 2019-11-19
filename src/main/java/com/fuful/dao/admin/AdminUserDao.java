package com.fuful.dao.admin;

import com.fuful.domain.ManageUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by SunRuiBin on 2019/11/17.
 */
@Repository
public interface AdminUserDao {

    public ManageUser findUserInfo(@Param("uname") String name,@Param("password") String password);

}
