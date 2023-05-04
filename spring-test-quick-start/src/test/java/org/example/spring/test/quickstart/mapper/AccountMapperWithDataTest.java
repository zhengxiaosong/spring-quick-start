package org.example.spring.test.quickstart.mapper;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.test.quickstart.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Description:
 *
 * @author Song.Z
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("has-data")
@Slf4j
public class AccountMapperWithDataTest {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 执行测试 1
     */
    @Test
    public void retrieveMultiple() {
        List<Account> accounts = accountMapper.retrieveMultiple(0, 5);
        for (Account account : accounts) {
            log.info("id={}, name=[{}], information=[{}]",
                    account.getId(), account.getName(), account.getInformation());
        }
        assertEquals(2, accounts.size());
    }
}
