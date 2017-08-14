package me.leolin.v1;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author leo
 */
public interface BookDao extends JpaRepository<Book, Integer> {
}
