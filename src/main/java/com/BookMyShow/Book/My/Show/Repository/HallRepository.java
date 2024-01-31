package com.BookMyShow.Book.My.Show.Repository;

import com.BookMyShow.Book.My.Show.Models.Hall;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface HallRepository extends JpaRepository<Hall, UUID> {
}
