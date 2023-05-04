package org.example.spring.mybatis.quickstart.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.spring.mybatis.quickstart.model.Consumer;

import java.util.List;

/**
 * Description:
 *
 * @author Song.Z
 */
@Mapper
public interface ConsumerMapper {
    List<Consumer> retrieveMultiple(@Param("id") long id, @Param("limit")int limit);
}
