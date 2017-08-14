package me.leolin.v1;

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
public class Runner implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);


    @Autowired
    private BookDao bookDao;

    @Transactional
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        if (bookDao.count() == 0L) {
            Book book = new Book();
            BookInfo bookInfo = new BookInfo();
            book.setBookInfo(bookInfo);
            bookInfo.setBook(book);
            bookDao.save(book);
        } else {

            LOGGER.info("v1--------");

            Book book = bookDao.findOne(1);

            LOGGER.info("v1 sleeping");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            LOGGER.info("v1--------");
            BookInfo bookInfo = book.getBookInfo();

            LOGGER.info(String.valueOf(bookInfo));

            LOGGER.info("v1--------");

        }

    }
}
