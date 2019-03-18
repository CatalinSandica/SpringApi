package ro.sandica.app.SpringApi.Helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.sandica.app.SpringApi.Entities.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
	Page<Item> findById(Long id, Pageable pageable);
}
