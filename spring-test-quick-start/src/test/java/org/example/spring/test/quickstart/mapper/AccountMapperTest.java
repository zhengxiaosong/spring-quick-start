package org.example.spring.test.quickstart.mapper;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.test.quickstart.model.Account;
import org.junit.jupiter.api.*;
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
@ActiveProfiles("no-data")
@Slf4j
class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @BeforeAll
    static void beforeAll() {
        log.info("Before All Test");
    }

    @AfterAll
    static void afterAll() {
        log.info("After All Test");
    }


    @BeforeEach
    void setUp() {
        for (int i = 0; i < 50; i++) {
            accountMapper.append(i, String.format("Name %dth", i),
                    String.format("Information for %d", i));
        }
        log.info("Before test case, had been inserted 50 data");
    }

    @AfterEach
    void tearDown() {
        for (int i = 0; i < 50; i++) {
            accountMapper.deletedById(i);
        }
        log.info("After test case, had been deleted all total data");
    }

    /**
     * 执行测试 1
     */
    @Test
    public void retrieveMultiple() {
        List<Account> accounts = accountMapper.retrieveMultiple(10, 5);
        for (Account account : accounts) {
            log.info("id={}, name=[{}], information=[{}]",
                    account.getId(), account.getName(), account.getInformation());
        }
        assertEquals(5, accounts.size());
    }

    /**
     * 执行测试 2
     */
    @Test
    public void retrieveMultipleInDouble() {
        List<Account> accounts = accountMapper.retrieveMultiple(46, 5);
        for (Account account : accounts) {
            log.info("id={}, name=[{}], information=[{}]",
                    account.getId(), account.getName(), account.getInformation());
        }
        assertEquals(4, accounts.size());
    }
}