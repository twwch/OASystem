package com.oa.mapper;

import com.oa.bean.Salarys;
import com.oa.bean.SalarysExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SalarysMapper {
    int countByExample(SalarysExample example);

    int deleteByExample(SalarysExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Salarys record);

    int insertSelective(Salarys record);

    List<Salarys> selectByExample(SalarysExample example);

    Salarys selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Salarys record, @Param("example") SalarysExample example);

    int updateByExample(@Param("record") Salarys record, @Param("example") SalarysExample example);

    int updateByPrimaryKeySelective(Salarys record);

    int updateByPrimaryKey(Salarys record);
}