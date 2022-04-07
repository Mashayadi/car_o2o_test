package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.query.UserQuery;
import cn.wolfcode.car.common.base.page.TablePageInfo;

import java.util.List;

/**
 * 岗位服务接口
 */
public interface IUserService {
    /**
     * 查单个
     * @param id
     * @return
     */
    User get(Long id);

    /**
     * 查询指定部门下的员工
     * @param deptId
     * @return
     */
    List<User> queryByDeptId(Long deptId);


    /**
     * 通过name查询用户对象
     * @param username
     * @return
     */
    User queryByUserName(String username);

    /**
     * 加密
     * @param loginName
     * @param password
     * @param salt
     * @return
     */
    String encryptPassword(String loginName, String password, String salt);

    /**
     * 判断手机号是否唯一
     * @param phonenumber
     * @param userId
     * @return
     */
    boolean checkPhone(String phonenumber, Long userId);
    /**
     * 判断邮箱是否唯一
     * @param email
     * @param userId
     * @return
     */
    boolean checkEmail(String email, Long userId);

    /**
     * 判断登录名是否唯一
     * @param loginName
     * @param userId
     * @return
     */
    boolean checkLoginName(String loginName, Long userId);

    /**
     * 更新
     * @param user
     */
    void update(User user);
    /**
     * 更新
     * @param currentUser
     */
    void updateInfo(User currentUser);


    /**
     * 密码是否一致
     * @param user
     * @param password
     * @return
     */
    boolean matches(User user, String password);

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     */
    void resetUserPwd(String oldPassword, String newPassword);
    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    void resetUserPwd(Long userId, String newPassword);

    /**
     * 分页查询
     * @param qo
     * @return
     */
    TablePageInfo<User> query(UserQuery qo);
    /**
     * 未分配分页查询
     * @param qo
     * @return
     */
    TablePageInfo<User> queryAllocated(UserQuery qo);
    /**
     * 分配分页查询
     * @param qo
     * @return
     */
    TablePageInfo<User> queryUnAllocated(UserQuery qo);

    /**
     * 更新用户头像
     * @param id
     * @param avatar
     */
    void updateAvatar(Long id, String avatar);

    /**
     * 修改状态
     * @param userId
     * @param status
     */
    void changeStatus(Long userId, char status);


    /**
     * 添加用户
     * @param user
     */
    void save(User user);

    /**
     * 用户分配角色
     * @param userId
     * @param roleIds
     */
    void insertUserAuth(Long userId, Long[] roleIds);

    /**
     * 删除用户
     * @param ids
     */
    void deleteUserByIds(String ids);

    /**
     * 查看所有
     * @return
     */
    List<User> list();

    /**
     * 通过rolekey查询用户
     * @param rkey
     * @return
     */
    List<User> queryByRoleKey(String rkey);
}
