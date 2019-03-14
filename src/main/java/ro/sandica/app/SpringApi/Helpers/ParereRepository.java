package ro.sandica.app.SpringApi.Helpers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.sandica.app.SpringApi.Entities.Parere;

@Repository
public interface ParereRepository extends JpaRepository<Parere, Long>{
	Page<Parere> findByItemId(Long itemID, Pageable pageable);
//	Optional<Parere> findByIdandItemId(Long id, Long itemID);
}
