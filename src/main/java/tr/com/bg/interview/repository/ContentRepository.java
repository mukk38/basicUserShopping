package tr.com.bg.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.bg.interview.model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
}