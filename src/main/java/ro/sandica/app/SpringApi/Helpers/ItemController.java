package ro.sandica.app.SpringApi.Helpers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ro.sandica.app.SpringApi.Entities.Item;
@RestController
public class ItemController {
	
	@Autowired
	private ItemRepository itemR;
	
	@GetMapping("/items")
	public Page<Item> getAllItems(Pageable pageable){
		return itemR.findAll(pageable);
	}
	
	@GetMapping("/item/{id}")
	public Page<Item> getItemById(@PathVariable Long id, Pageable pageable){
		return itemR.findById(id, pageable);
	}
	
	@PostMapping("/item")
	public Item createItem(@Valid @RequestBody Item item) {
		return itemR.save(item);
	}
	
	@PutMapping("items/{id}")
	public Item updateItem(@PathVariable Long id, @Valid @RequestBody Item itemReq) {
		return itemR.findById(id).map(item -> {
			item.setTitle(itemReq.getTitle());
			item.setDescription(itemReq.getDescription());
			return itemR.save(item);
		}).orElseThrow(() -> new ExceptionNotFoundObject("Item "+id+" not found"));
	}
	
	@DeleteMapping("items/{id}")
	public ResponseEntity<?> deleleItem(@PathVariable(value="id") Long id){
		return itemR.findById(id).map(item -> {
			itemR.delete(item);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ExceptionNotFoundObject("Item "+id+ " not found"));
	}
	
	
}
