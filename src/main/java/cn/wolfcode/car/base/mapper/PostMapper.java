package cn.wolfcode.car.base.mapper;

import cn.wolfcode.car.base.domain.Post;
import cn.wolfcode.car.base.query.PostQuery;

import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Post record);

    Post selectByPrimaryKey(Long id);

    List<Post> selectAll();

    int updateByPrimaryKey(Post record);

    List<Post> selectForList(PostQuery qo);

    Post selectByName(String name);

    Post selectByCode(String code);

    List<Post> selectByUserId(Long userId);
}