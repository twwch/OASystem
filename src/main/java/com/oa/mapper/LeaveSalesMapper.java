package com.oa.mapper;

import com.oa.bean.LeaveSales;
import com.oa.bean.LeaveSalesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeaveSalesMapper {
    int countByExample(LeaveSalesExample example);

    int deleteByExample(LeaveSalesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LeaveSales record);

    int insertSelective(LeaveSales record);

    List<LeaveSales> selectByExample(LeaveSalesExample example);

    LeaveSales selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LeaveSales record, @Param("example") LeaveSalesExample example);

    int updateByExample(@Param("record") LeaveSales record, @Param("example") LeaveSalesExample example);

    int updateByPrimaryKeySelective(LeaveSales record);

    int updateByPrimaryKey(LeaveSales record);
}