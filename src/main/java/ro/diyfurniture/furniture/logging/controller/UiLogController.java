package ro.diyfurniture.furniture.logging.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.diyfurniture.furniture.logging.model.transmission.UiLogBatchRequest;
import ro.diyfurniture.furniture.logging.model.transmission.UiLogQueryResponse;
import ro.diyfurniture.furniture.logging.service.UiLogService;

@RestController
@RequestMapping("/api/ui-logs")
public class UiLogController {

	private final UiLogService uiLogService;

	public UiLogController(UiLogService uiLogService) {
		this.uiLogService = uiLogService;
	}

	@PostMapping
	public ResponseEntity<?> collect(@RequestBody UiLogBatchRequest request) {
		try {
			int accepted = uiLogService.collect(request);
			return new ResponseEntity<>(Map.of("accepted", accepted), HttpStatus.ACCEPTED);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(
				Map.of("error", "validation_error", "message", ex.getMessage()),
				HttpStatus.BAD_REQUEST
			);
		}
	}

	@GetMapping
	public ResponseEntity<UiLogQueryResponse> search(
		@RequestParam(value = "level", required = false) String level,
		@RequestParam(value = "sessionId", required = false) String sessionId,
		@RequestParam(value = "userId", required = false) String userId,
		@RequestParam(value = "from", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) Instant from,
		@RequestParam(value = "to", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) Instant to,
		@RequestParam(value = "limit", required = false) Integer limit
	) {
		return new ResponseEntity<>(
			uiLogService.search(level, sessionId, userId, from, to, limit),
			HttpStatus.OK
		);
	}
}
