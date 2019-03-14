package ro.sandica.app.SpringApi.Helpers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/item")
	public Item createItem(@Valid @RequestBody Item item) {
		return itemR.save(item);
	}
	
	
}
