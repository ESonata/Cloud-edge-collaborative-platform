package com.fuful.dao;

import com.fuful.domain.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersDAO {
    public Users findByUName(@Param("uname") String uname);
}
