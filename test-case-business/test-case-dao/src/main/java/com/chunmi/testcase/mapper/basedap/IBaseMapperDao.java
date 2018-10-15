package com.chunmi.testcase.mapper.basedap;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author sunny
 * @param <E>
 * @param <PK>
 */
public interface IBaseMapperDao<E, PK> extends BaseMapper {
    /**
     * @param e
     * @description 通过实体进行添加
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(final E e);

    /**
     * @param e
     * @description 通过集合进行批量添加
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void batchAdd(final List<E> e);

    /**
     * @param e
     * @description 通过主键进行删除
     */
    Integer remove(@Param(value = "id") final PK e);

    /**
     * @param e
     * @description 通过主键进行批量删除
     */
    void batchRemove(final PK[] e);

    /**
     * @param e
     * @description 通过实体更新
     */
    Integer update(final E e);

    /**
     * @param id
     * @return
     * @throws Exception
     * @description 通过主键查询
     */
    E getEntity(@Param(value = "id") final PK id);
}