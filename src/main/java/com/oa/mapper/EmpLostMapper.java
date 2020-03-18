package com.oa.mapper;

import com.oa.bean.EmpLost;
import com.oa.bean.EmpLostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpLostMapper {
    int countByExample(EmpLostExample example);

    int deleteByExample(EmpLostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmpLost record);

    int insertSelective(EmpLost record);

    List<EmpLost> selectByExample(EmpLostExample example);

    EmpLost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmpLost record, @Param("example") EmpLostExample example);

    int updateByExample(@Param("record") EmpLost record, @Param("example") EmpLostExample example);

    int updateByPrimaryKeySelective(EmpLost record);

    int updateByPrimaryKey(EmpLost record);
}