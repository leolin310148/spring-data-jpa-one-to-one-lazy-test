package me.leolin.v2;

import javax.persistence.*;

/**
 * @author leo
 */
@Entity
public class BookV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BookInfoV2 bookInfoV2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookInfoV2 getBookInfoV2() {
        return bookInfoV2;
    }

    public void setBookInfoV2(BookInfoV2 bookInfoV2) {
        this.bookInfoV2 = bookInfoV2;
    }
}
