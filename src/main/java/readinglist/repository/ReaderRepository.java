package readinglist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import readinglist.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, String>{

	Reader findByUsername(String username);
}
