package ro.diyfurniture.furniture.logging.service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ro.diyfurniture.furniture.logging.model.UiLogEvent;
import ro.diyfurniture.furniture.logging.model.transmission.UiLogBatchRequest;
import ro.diyfurniture.furniture.logging.model.transmission.UiLogEntryRequest;
import ro.diyfurniture.furniture.logging.model.transmission.UiLogEntryResponse;
import ro.diyfurniture.furniture.logging.model.transmission.UiLogQueryResponse;
import ro.diyfurniture.furniture.logging.repository.UiLogEventRepository;

@Service
public class UiLogService {

	private static final int MAX_BATCH_SIZE = 500;
	private static final int DEFAULT_LIMIT = 200;
	private static final int MAX_LIMIT = 1000;

	private final UiLogEventRepository uiLogEventRepository;

	public UiLogService(UiLogEventRepository uiLogEventRepository) {
		this.uiLogEventRepository = uiLogEventRepository;
	}

	public int collect(UiLogBatchRequest request) {
		if (request == null || request.getLogs() == null || request.getLogs().isEmpty()) {
			return 0;
		}
		if (request.getLogs().size() > MAX_BATCH_SIZE) {
			throw new IllegalArgumentException("Batch too large. Maximum accepted logs per request is " + MAX_BATCH_SIZE + ".");
		}

		List<UiLogEvent> events = new ArrayList<>();
		for (UiLogEntryRequest entry : request.getLogs()) {
			if (entry == null || isBlank(entry.getMessage())) {
				continue;
			}
			UiLogEvent event = new UiLogEvent();
			event.setEventTime(parseTimestamp(entry.getTimestamp()));
			event.setReceivedAt(Instant.now());
			event.setLevel(normalizeLevel(entry.getLevel()));
			event.setMessage(trim(entry.getMessage(), 2048));
			event.setRoute(trim(entry.getRoute(), 1024));
			event.setSessionId(trim(entry.getSessionId(), 128));
			event.setUserId(trim(entry.getUserId(), 128));
			event.setDataJson(entry.getData() == null ? null : entry.getData().toString());
			events.add(event);
		}

		if (events.isEmpty()) {
			return 0;
		}

		uiLogEventRepository.saveAll(events);
		return events.size();
	}

	public UiLogQueryResponse search(String level, String sessionId, String userId, Instant from, Instant to, Integer limit) {
		Specification<UiLogEvent> spec = Specification.where(null);
		if (!isBlank(level)) {
			String normalizedLevel = normalizeLevel(level);
			spec = spec.and((root, query, cb) -> cb.equal(root.get("level"), normalizedLevel));
		}
		if (!isBlank(sessionId)) {
			spec = spec.and((root, query, cb) -> cb.equal(root.get("sessionId"), sessionId.trim()));
		}
		if (!isBlank(userId)) {
			spec = spec.and((root, query, cb) -> cb.equal(root.get("userId"), userId.trim()));
		}
		if (from != null) {
			spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("receivedAt"), from));
		}
		if (to != null) {
			spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("receivedAt"), to));
		}

		int safeLimit = limit == null ? DEFAULT_LIMIT : Math.max(1, Math.min(limit, MAX_LIMIT));
		Page<UiLogEvent> page = uiLogEventRepository.findAll(
			spec,
			PageRequest.of(0, safeLimit, Sort.by(Sort.Direction.DESC, "id"))
		);

		UiLogQueryResponse response = new UiLogQueryResponse();
		response.setTotal(page.getTotalElements());
		response.setCount(page.getNumberOfElements());
		List<UiLogEntryResponse> items = new ArrayList<>();
		for (UiLogEvent event : page.getContent()) {
			UiLogEntryResponse mapped = new UiLogEntryResponse();
			mapped.setId(event.getId());
			mapped.setEventTime(event.getEventTime());
			mapped.setReceivedAt(event.getReceivedAt());
			mapped.setLevel(event.getLevel());
			mapped.setMessage(event.getMessage());
			mapped.setRoute(event.getRoute());
			mapped.setSessionId(event.getSessionId());
			mapped.setUserId(event.getUserId());
			mapped.setDataJson(event.getDataJson());
			items.add(mapped);
		}
		response.setItems(items);
		return response;
	}

	private Instant parseTimestamp(String timestamp) {
		if (isBlank(timestamp)) {
			return Instant.now();
		}
		try {
			return Instant.parse(timestamp);
		} catch (DateTimeParseException ex) {
			return Instant.now();
		}
	}

	private String normalizeLevel(String level) {
		if (isBlank(level)) {
			return "info";
		}
		String normalized = level.trim().toLowerCase(Locale.ROOT);
		switch (normalized) {
			case "debug":
			case "info":
			case "warn":
			case "error":
				return normalized;
			default:
				return "info";
		}
	}

	private String trim(String value, int maxLength) {
		if (value == null) {
			return null;
		}
		String trimmed = value.trim();
		if (trimmed.length() <= maxLength) {
			return trimmed;
		}
		return trimmed.substring(0, maxLength);
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
