package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.Config;
import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> selectByDeptId(Long deptId);

    User selectByLoginName(String username);

    User selectByPhone(String phonenumber);

    User selectByEmail(String email);

    void updateInfo(User currentUser);


    void updateUserPassword(User user);

    List<User> selectForList(UserQuery qo);

    List<User> selectForAllocated(UserQuery qo);

    List<User> selectForUnAllocated(UserQuery qo);

    void updateAvatar(@Param("userId") Long userId, @Param("avatar") String avatar);

    void updateStatus(@Param("userId")Long userId, @Param("status")char status);

    void insertRelation(@Param("userId")Long userId, @Param("postId")Long postId);

    void deleteRelation(@Param("userId")Long userId);

    void deleteRoleRelation(Long userId);

    List<User> selectByRoleKey(String rkey);
}