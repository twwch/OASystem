package com.oa.mapper;

import com.oa.bean.PubIp;
import com.oa.bean.PubIpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubIpMapper {
    int countByExample(PubIpExample example);

    int deleteByExample(PubIpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PubIp record);

    int insertSelective(PubIp record);

    List<PubIp> selectByExample(PubIpExample example);

    PubIp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PubIp record, @Param("example") PubIpExample example);

    int updateByExample(@Param("record") PubIp record, @Param("example") PubIpExample example);

    int updateByPrimaryKeySelective(PubIp record);

    int updateByPrimaryKey(PubIp record);
}