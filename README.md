Testing lazy behavior of Spring Data JPA
---

- to run this project
```
docker-compose up -d
```
```
./gradlew bootRun # first time for inserting book
./gradlew bootRun # see the results
```    
     

V1
---
* which makes lazy works by add an extra column `book_info_id` in table `book`

```
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BookInfo bookInfo;
    
    ...
}
```

```
@Entity
public class BookInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Book book;
    
    ...
}

```

* result
```
2017-08-14 21:45:16.590  : v1--------
Hibernate: select book0_.id as id1_0_0_, book0_.book_info_id as book_inf2_0_0_ from book book0_ where book0_.id=?
2017-08-14 21:45:16.602  : v1 sleeping
2017-08-14 21:45:18.607  : v1--------
Hibernate: select bookinfo0_.id as id1_1_0_ from book_info bookinfo0_ where bookinfo0_.id=?
^^^
did query after sleeping
 
2017-08-14 21:45:18.609  : me.leolin.v1.BookInfo@2abe9173
2017-08-14 21:45:18.610  : v1--------
```


V2
---
* which lazy is not working with `@PrimaryKeyJoinColumn` and `@MapsId`

```
@Entity
public class BookV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BookInfoV2 bookInfoV2;

    ...
}

```

```
@Entity
public class BookInfoV2 {

    @Id
    private Integer id;

    @MapsId
    @OneToOne(targetEntity = BookV2.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private BookV2 bookV2;

    ...
}

```

* result

```
2017-08-14 21:51:01.957  : v2--------
Hibernate: select bookv2x0_.id as id1_3_0_ from bookv2 bookv2x0_ where bookv2x0_.id=?
Hibernate: select bookinfov2x0_.id as id1_2_0_ from book_infov2 bookinfov2x0_ where bookinfov2x0_.id=?
^^^
did query before sleeping

2017-08-14 21:51:01.959  : v2 sleeping
2017-08-14 21:51:03.961  : v2--------
2017-08-14 21:51:03.961  : me.leolin.v2.BookInfoV2@d5af0a5
2017-08-14 21:51:03.961  : v2--------
```