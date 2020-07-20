package com.mzx.dao;

import com.mzx.domain.QueryVo;
import com.mzx.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     *
     * @return
     */
    List<User> findAll();

    /**
     * 保存方法
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    void deleteUser(Integer userId);

    /**
     *
     * @param userId
     * @return
     */
    User findById(Integer userId);

    List<User> findByName(String name);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据queryvo查询条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo );
}
