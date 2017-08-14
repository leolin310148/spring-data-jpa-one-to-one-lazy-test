package me.leolin.v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author leo
 */
@Component
public class RunnerV2 implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunnerV2.class);


    @Autowired
    private BookV2Dao bookV2Dao;

    @Transactional
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        if (bookV2Dao.count() == 0L) {
            BookV2 bookV2 = new BookV2();
            BookInfoV2 bookInfoV2 = new BookInfoV2();
            bookV2.setBookInfoV2(bookInfoV2);
            bookInfoV2.setBookV2(bookV2);
            bookV2Dao.save(bookV2);
        } else {

            LOGGER.info("v2--------");

            BookV2 bookV2 = bookV2Dao.findOne(1);

            LOGGER.info("v2 sleeping");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            LOGGER.info("v2--------");
            BookInfoV2 bookInfoV2 = bookV2.getBookInfoV2();

            LOGGER.info(String.valueOf(bookInfoV2));

            LOGGER.info("v2--------");

        }

    }
}
