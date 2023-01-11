package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.model.HistoryBody;
import ro.sapientia.furniture.service.HistoryService;

/**
 * igy hatarozzuk meg az endpointot
 */
@RestController
@RequestMapping("/history")
public class HistoryController {
	
	private final HistoryService historyService;
	
	@Autowired
	public HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	/**
	 * azzal hivjuk meg ezt a getHistory() fuggvenyt, hogy a browserben localhost:8080/api/history-ra megyunk
	 */
	@GetMapping("/all")
	public List<HistoryBody> getHistory() {
		return historyService.findAllHistoryBodies();
	}
	
	@PostMapping("/add")
	public void registerNewHistory(@RequestBody HistoryBody history) {
		historyService.create(history);
	}
	 
	 @PostMapping("/update")
		public ResponseEntity<HistoryBody> updateHistoryBody(@RequestBody HistoryBody historyBody){
			final HistoryBody persistenHistoryBody = historyService.update(historyBody);
			return new ResponseEntity<>(persistenHistoryBody,HttpStatus.OK);
		}
	    
	 @GetMapping("delete/{id}")
		public ResponseEntity<?> deleteHistoryBodyById(@PathVariable("id") Long id){
		 historyService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
}
