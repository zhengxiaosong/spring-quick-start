package org.example.spring.test.quickstart;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.test.quickstart.mapper.AccountMapper;
import org.example.spring.test.quickstart.model.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Description:
 *
 * @author Song.Z
 */
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        AccountMapper accountMapper = context.getBean(AccountMapper.class);
        List<Account> accounts = accountMapper.retrieveMultiple(0, 20);
        for (Account account: accounts) {
            log.info("id={}, name=[{}], information=[{}]",
                    account.getId(), account.getName(), account.getInformation());
        }
    }
}
