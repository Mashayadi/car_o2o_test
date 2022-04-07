package cn.wolfcode.car.common.base.page;

import cn.wolfcode.car.common.web.StateType;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * 分页对象
 * @param <T>
 */
@Setter
@Getter
public class TablePageInfo<T>{

    private int code;           //返回值状态码：为0是表示成功
    private String msg;         //返回值信息

    private long total;         //分页总条数
    private List<T> rows = new ArrayList<>();       //当前页面显示数据
    public TablePageInfo(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        this.total = pageInfo.getTotal();
        this.rows = pageInfo.getList();

        //成功
        this.code = StateType.SUCCESS.getValue();
    }
}
