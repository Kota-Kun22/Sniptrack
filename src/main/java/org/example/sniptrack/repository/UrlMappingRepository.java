package org.example.sniptrack.repository;

import org.example.sniptrack.models.UrlMapping;
import org.example.sniptrack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByShortUrl(String shortUrl);
    List<UrlMapping> findByUser(User user);

}
