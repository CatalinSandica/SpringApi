package ro.sandica.app.SpringApi.Helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sandica.app.SpringApi.Entities.Parere;

@RestController
public class ParereController {
	@Autowired
	private ParereRepository parereRep;
	@Autowired
	private ItemRepository itemRep;
	
	@GetMapping("/item/{id}/pareri")
	public Page<Parere> getPareribyItemId(@PathVariable(value="id") Long id, Pageable pageable){
		return parereRep.findByItemId(id, pageable);
	}
	
	@PostMapping("item/{id}/pareri")
	public Parere createParere(@PathVariable(value="id") Long id, Parere parere) {
	return itemRep.findById(id).map(item ->{
		parere.setItem(item);
		return parereRep.save(parere);
	}).orElseThrow(() -> new ExceptionNotFoundObject("Item "+id+" not found"));
	}
}
