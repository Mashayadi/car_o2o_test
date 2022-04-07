package cn.wolfcode.car.base.service.impl;

import cn.wolfcode.car.base.domain.Post;
import cn.wolfcode.car.base.domain.Post;
import cn.wolfcode.car.base.mapper.PostMapper;
import cn.wolfcode.car.base.query.PostQuery;
import cn.wolfcode.car.base.service.IPostService;
import cn.wolfcode.car.common.base.page.TablePageInfo;
import cn.wolfcode.car.common.exception.BusinessException;
import cn.wolfcode.car.common.util.Convert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostMapper postMapper;


    @Override
    public TablePageInfo<Post> query(PostQuery qo) {
        PageHelper.startPage(qo.getPageNum(), qo.getPageSize());
        return new TablePageInfo<Post>(postMapper.selectForList(qo));
    }


    @Override
    public void save(Post post) {
        postMapper.insert(post);
    }

    @Override
    public Post get(Long id) {
        return postMapper.selectByPrimaryKey(id);
    }


    @Override
    public void update(Post post) {
        postMapper.updateByPrimaryKey(post);
    }

    @Override
    public void deleteBatch(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            postMapper.deleteByPrimaryKey(dictId);
        }
    }

    @Override
    public List<Post> list() {
        return postMapper.selectAll();
    }

    @Override
    public boolean checkNameExsit(String name) {
        return postMapper.selectByName(name) != null;
    }

    @Override
    public boolean checkCodeExsit(String code) {
        return postMapper.selectByCode(code) != null;
    }

    @Override
    public List<Post> queryByUserId(Long userId) {
        return postMapper.selectByUserId(userId);
    }

    @Override
    public List<Post> listAllWithUserId(Long userId) {

        List<Post> posts = postMapper.selectAll();
        List<Post> userPosts = this.queryByUserId(userId);

        List<Long> collect = userPosts.stream().map(p -> p.getId()).collect(Collectors.toList());

        for (Post post : posts) {
            if(collect.contains(post.getId())){
                post.setFlag(true);
            }
        }
        return posts;
    }
}
