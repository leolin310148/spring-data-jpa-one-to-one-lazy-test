package me.leolin.v2;

import javax.persistence.*;

/**
 * @author leo
 */
@Entity
public class BookInfoV2 {

    @Id
    private Integer id;

    @MapsId
    @OneToOne(targetEntity = BookV2.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private BookV2 bookV2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookV2 getBookV2() {
        return bookV2;
    }

    public void setBookV2(BookV2 bookV2) {
        this.bookV2 = bookV2;
    }
}
