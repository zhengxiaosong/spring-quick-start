package org.example.spring.mybatis.quickstart.service.impl;

import org.example.spring.mybatis.quickstart.mapper.ConsumerMapper;
import org.example.spring.mybatis.quickstart.model.Consumer;
import org.example.spring.mybatis.quickstart.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * Description:
 *
 * @author Song.Z
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public void retrieveDataDemo() {
        long lastId = 0;
        List<Consumer> consumers = consumerMapper.retrieveMultiple(lastId, 10);
        DateFormatter dateFormatter = new DateFormatter();
        for (Consumer consumer : consumers) {
            String timeString = "never mind";
            if (consumer.getRegisterTime() != null) {
                dateFormatter.setPattern("yyyy/MM/dd HH:mm:ss");
                timeString = dateFormatter.print(consumer.getRegisterTime(), Locale.CHINESE);
            }
            System.out.printf("\tid:%s\t\tregister time: %s\t\tnickname: %s\n",
                    consumer.getId(),
                    timeString,
                    consumer.getNickname());
        }
    }
}
