package ro.sapientia.furniture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sapientia.furniture.model.Order;
import ro.sapientia.furniture.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(final OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Order>> findAllOrder(){
		return new ResponseEntity<>(this.orderService.findAllOrder(),HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Order> findOrderById(@PathVariable("id")final Long id) {
		return new ResponseEntity<>(this.orderService.findeOrderById(id),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Order> create(@RequestBody final Order order) {
		return new ResponseEntity<>(this.orderService.create(order),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Order> update(@RequestBody final Order order) {
		return new ResponseEntity<>(this.orderService.update(order),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")final Long id) {
		this.orderService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
