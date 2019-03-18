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
	
	@GetMapping("/pareri")
	public Page<Parere> getAllItems(Pageable pageable){
		return parereRep.findAll(pageable);
	}
	
	@GetMapping("/item/{iid}/pareri/{pid}")
	public Page<Parere> getParereByItemAndParereId(@PathVariable(value="iid") Long itemId, @PathVariable(value="pid") Long parereId, Pageable pageable){
		return parereRep.findByIdAndItemId(parereId, itemId, pageable);
	}
	
	@PostMapping("item/{id}/pareri")
	public Parere createParere(@PathVariable(value="id") Long id, @Valid @RequestBody Parere parere) {
	return itemRep.findById(id).map(item ->{
		parere.setItem(item);
		return parereRep.save(parere);
	}).orElseThrow(() -> new ExceptionNotFoundObject("Item "+id+" not found"));
	}
	
	@PutMapping("item/{itid}/pareri/{pid}")
	public Parere updateParere(@PathVariable(value="itid") Long itemId, @PathVariable(value="pid") Long parereId, @Valid @RequestBody Parere parereReq) {
		if (!itemRep.existsById(itemId)) {
			throw new ExceptionNotFoundObject("Item " + itemId + " not found");
		}
		return parereRep.findById(parereId).map(parere ->{
			parere.setText(parereReq.getText());
			return parereRep.save(parere);
		}).orElseThrow(() -> new ExceptionNotFoundObject("Item "+parereId+" not found"));
	}
	
	
	@DeleteMapping("item/{itid}/pareri/{pid}")
	public ResponseEntity<?> deletePareri(@PathVariable(value="itid") Long itemId, @PathVariable(value="pid") Long parereId) {
		return parereRep.findByIdAndItemId(parereId, itemId).map(parere ->{
			parereRep.delete(parere);
			return ResponseEntity.ok().build();
			
		}).orElseThrow(() -> new ExceptionNotFoundObject("Parere "+parereId+" not found"));
	}
	
	
}
