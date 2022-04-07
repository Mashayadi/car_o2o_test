package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.Config;
import cn.wolfcode.car.base.domain.User;
import cn.wolfcode.car.base.mapper.PostMapper;
import cn.wolfcode.car.base.mapper.RoleMapper;
import cn.wolfcode.car.base.mapper.UserMapper;
import cn.wolfcode.car.base.query.ConfigQuery;
import cn.wolfcode.car.base.query.UserQuery;
import cn.wolfcode.car.base.service.IUserService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import cn.wolfcode.car.common.util.Convert;
import cn.wolfcode.car.common.util.IpUtils;
import cn.wolfcode.car.common.web.AjaxResult;
import cn.wolfcode.car.common.web.ServletUtils;
import cn.wolfcode.car.shiro.ShiroUtils;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> queryByDeptId(Long deptId) {
        return userMapper.selectByDeptId(deptId);
    }

    @Override
    public User queryByUserName(String username) {
        return userMapper.selectByLoginName(username);
    }

    /**
     * 加密
     * @param loginName
     * @param password
     * @param salt
     * @return
     */
    @Override
    public String encryptPassword(String loginName, String password, String salt) {
        return new Md5Hash(loginName + password + salt).toHex();
    }

    @Override
    public boolean checkPhone(String phonenumber, Long userId) {
        User user = userMapper.selectByPhone(phonenumber);
        if(userId == null){
            return user != null;
        }
        return user != null && user.getId() != userId;
    }

    @Override
    public boolean checkEmail(String email, Long userId) {
        User user = userMapper.selectByEmail(email);
        if(userId == null){
            return user != null;
        }
        return user != null && user.getId() != userId;
    }

    @Override
    public boolean checkLoginName(String loginName, Long userId) {
        User user = userMapper.selectByLoginName(loginName);
        if(userId == null){
            return user != null;
        }
        return user != null && user.getId() != userId;
    }

    @Override
    public void update(User user) {
        userMapper.deleteRelation(user.getId());
        userMapper.deleteRoleRelation(user.getId());
        //维护与角色关系
        List<Long> roleIds = user.getRoleIds();
        if(roleIds != null && roleIds.size() > 0){
            for (Long roleId : roleIds) {
                roleMapper.insertUserRelation(roleId, user.getId());
            }
        }

        //维护与岗位关系
        List<Long> postIds = user.getPostIds();
        if(postIds != null && postIds.size() > 0){
            for (Long postId : postIds) {
                userMapper.insertRelation(user.getId(), postId);
            }
        }


        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void updateInfo(User currentUser) {
        userMapper.updateInfo(currentUser);
    }

    @Override
    public boolean matches(User user, String password) {
        return this.encryptPassword(user.getLoginName(),password,user.getSalt()).equals(user.getPassword());
    }

    @Override
    public void resetUserPwd(String oldPassword, String newPassword) {
        User user = ShiroUtils.getUser();
        if (!this.matches(user, oldPassword)){
            throw new BusinessException("修改密码失败，旧密码错误");
        }
        if (this.matches(user, newPassword)){
            throw new BusinessException("新密码不能与旧密码相同");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(this.encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
        userMapper.updateUserPassword(user);
        ShiroUtils.setUser(this.get(user.getId()));
    }

    @Override
    public void resetUserPwd(Long userId, String newPassword) {
        User user = this.get(userId);
        if (this.matches(user, newPassword)){
            throw new BusinessException("新密码不能与旧密码相同");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(this.encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
        userMapper.updateUserPassword(user);
        ShiroUtils.setUser(this.get(user.getId()));
    }

    @Override
    public TablePageInfo<User> query(UserQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<User>(userMapper.selectForList(qo));
    }

    @Override
    public TablePageInfo<User> queryAllocated(UserQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<User>(userMapper.selectForAllocated(qo));
    }

    @Override
    public TablePageInfo<User> queryUnAllocated(UserQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<User>(userMapper.selectForUnAllocated(qo));
    }

    @Override
    public void updateAvatar(Long id, String avatar) {
        userMapper.updateAvatar(id, avatar);
    }

    @Override
    public void changeStatus(Long userId, char status) {
        userMapper.updateStatus(userId,status);
    }


    @Override
    public void save(User user) {
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(this.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        userMapper.insert(user);

        //维护与角色关系
        List<Long> roleIds = user.getRoleIds();
        if(roleIds != null && roleIds.size() > 0){
            for (Long roleId : roleIds) {
                roleMapper.insertUserRelation(roleId, user.getId());
            }
        }


        //维护与岗位关系
        List<Long> postIds = user.getPostIds();
        if(postIds != null && postIds.size() > 0){
            for (Long postId : postIds) {
                userMapper.insertRelation(user.getId(), postId);
            }
        }


    }

    @Override
    public void insertUserAuth(Long userId, Long[] roleIds) {
        userMapper.deleteRoleRelation(userId);

        if(roleIds != null && roleIds.length > 0){
            for (Long roleId : roleIds) {
                roleMapper.insertUserRelation(roleId, userId);
            }
        }
    }

    @Override
    public void deleteUserByIds(String ids) {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds){
            if("admin".equalsIgnoreCase(this.get(userId).getLoginName())){
                throw new BusinessException("超管不能删除");
            }
            // 删除用户与角色关联
            userMapper.deleteRelation(userId);
            // 删除用户与岗位关联
            userMapper.deleteRelation(userId);

            userMapper.deleteByPrimaryKey(userId);
        }
    }

    @Override
    public List<User> list() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> queryByRoleKey(String rkey) {
        return userMapper.selectByRoleKey(rkey);
    }
}
