package com.hbjs.hrscommon.form;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BaseSearchForm", description = "基础分页列表查询前端传入参数")
public class BaseSearchForm<T> {

    @Schema(title = "关键字")
    private String keyword;

    @Schema(title = "每页显示条数，默认 10")
    private long pageSize = 10;

    @Schema(title = "当前页，默认 1")
    private long current = 1;

    @Schema(title = "排序字段信息")
    private List<OrderItem> orders = new ArrayList<>();

    /**
     * 生成mybatis的分页实体
     * @param
     * @return
     */
    public Page<T> getPage() {
        Page<T> page = new Page<>(this.current, this.pageSize);
        page.setOrders(this.getOrders());
        return page;
    }

}
