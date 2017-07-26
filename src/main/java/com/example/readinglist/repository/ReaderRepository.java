/**
 *
 */
package com.example.readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.readinglist.entity.Reader;

/**
 * @author shuai.b.zhang
 *
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {

}
