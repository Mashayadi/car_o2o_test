package cn.wolfcode.car.base.service;

import cn.wolfcode.car.base.domain.Post;
import cn.wolfcode.car.base.domain.Post;
import cn.wolfcode.car.base.query.PostQuery;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 岗位服务接口
 */
public interface IPostService {

    /**
     * 分页
     * @param qo
     * @return
     */
    TablePageInfo<Post> query(PostQuery qo);


    /**
     * 查单个
     * @param id
     * @return
     */
    Post get(Long id);


    /**
     * 保存
     * @param post
     */
    void save(Post post);

  
    /**
     * 更新
     * @param post
     */
    void update(Post post);

    /**
     *  批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 查询全部
     * @return
     */
    List<Post> list();

    /**
     * 检查岗位名称是否存在
     * @param name
     * @return ture:存在， false：不存在
     */
    boolean checkNameExsit(String name);
    /**
     * 检查岗位编码是否存在
     * @param code
     * @return ture:存在， false：不存在
     */
    boolean checkCodeExsit(String code);

    /**
     * 用户所在岗位
     * @param userId
     * @return
     */
    List<Post> queryByUserId(Long userId);

    /**
     * 查询用户拥有的岗位
     * @param userId
     * @return
     */
    List<Post> listAllWithUserId(Long userId);
}
