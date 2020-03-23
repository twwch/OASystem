package com.oa.mapper;

import com.oa.bean.Sendmoney;
import com.oa.bean.SendmoneyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendmoneyMapper {
    int countByExample(SendmoneyExample example);

    int deleteByExample(SendmoneyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sendmoney record);

    int insertSelective(Sendmoney record);

    List<Sendmoney> selectByExample(SendmoneyExample example);

    Sendmoney selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sendmoney record, @Param("example") SendmoneyExample example);

    int updateByExample(@Param("record") Sendmoney record, @Param("example") SendmoneyExample example);

    int updateByPrimaryKeySelective(Sendmoney record);

    int updateByPrimaryKey(Sendmoney record);
}