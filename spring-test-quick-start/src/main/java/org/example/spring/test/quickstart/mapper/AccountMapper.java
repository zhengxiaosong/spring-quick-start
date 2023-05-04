package org.example.spring.test.quickstart.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.spring.test.quickstart.model.Account;

import java.util.List;

/**
 * Description:
 *
 * @author Song.Z
 */
@Mapper
public interface AccountMapper {
    List<Account> retrieveMultiple(@Param("id") long id, @Param("limit")int limit);

    int append(@Param("id") long id, @Param("name")String name, @Param("information") String information);

    int deletedById(@Param("id") long id);
}
