package ro.diyfurniture.furniture.cuttingplan.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import ro.diyfurniture.furniture.cuttingplan.model.transmission.BodyPayload;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.CuttingPlanPartPlacement;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.CuttingPlanRequest;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.CuttingPlanResponse;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.CuttingPlanSheet;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.CuttingPlanStats;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.FrontElementPayload;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.InteriorElementPayload;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.PartPayload;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.PartsRequest;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.PartsResponse;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.PlanRequest;
import ro.diyfurniture.furniture.cuttingplan.model.transmission.Stock;

@Service
public class CuttingPlanService {

	private static final int DEFAULT_SHEET_WIDTH = 2800;
	private static final int DEFAULT_SHEET_HEIGHT = 2070;
	private static final double DEFAULT_KERF = 3.175;
	private static final double DEFAULT_EDGE_BAND = 2.0;
	private static final int DEFAULT_SHEET_THICKNESS = 18;
	private static final double DEFAULT_MARGIN = 0.0;

	public CuttingPlanResponse generate(CuttingPlanRequest request) {
		validateRequest(request);
		Stock stock = applyDefaults(request.getStock());
		List<Part> parts = resolveParts(request, stock);
		List<CuttingPlanSheet> sheets = pack(parts, stock);
		CuttingPlanStats stats = buildStats(sheets, parts, stock);
		return new CuttingPlanResponse("OK", sheets, stats, toPayload(parts));
	}

	public PartsResponse deriveParts(PartsRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request is required.");
		}
		Stock stock = applyDefaults(request.getStock());
		validateBodies(request.getBodies());
		List<Part> parts = buildParts(request.getBodies(), stock);
		return new PartsResponse(toPayload(parts));
	}

	public CuttingPlanResponse generateFromParts(PlanRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request is required.");
		}
		Stock stock = applyDefaults(request.getStock());
		List<Part> parts = buildPartsFromPayload(request.getParts());
		if (parts.isEmpty()) {
			throw new IllegalArgumentException("At least one part is required.");
		}
		List<CuttingPlanSheet> sheets = pack(parts, stock);
		CuttingPlanStats stats = buildStats(sheets, parts, stock);
		return new CuttingPlanResponse("OK", sheets, stats, toPayload(parts));
	}

	private void validateRequest(CuttingPlanRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request is required.");
		}
		if ((request.getBodies() == null || request.getBodies().isEmpty())
			&& (request.getParts() == null || request.getParts().isEmpty())) {
			throw new IllegalArgumentException("At least one body or part is required.");
		}
		if (request.getBodies() != null && !request.getBodies().isEmpty()) {
			validateBodies(request.getBodies());
		}
		if (request.getParts() != null && !request.getParts().isEmpty()) {
			validateParts(request.getParts());
		}
	}

	private List<PartPayload> toPayload(List<Part> parts) {
		List<PartPayload> payload = new ArrayList<>();
		for (Part part : parts) {
			PartPayload out = new PartPayload(part.id, part.partType, part.bodyId, part.width, part.height);
			out.setMustAlign(part.mustAlign);
			out.setAllowRotate(part.allowRotate);
			payload.add(out);
		}
		return payload;
	}

	private boolean isPositive(Integer value) {
		return value != null && value > 0;
	}

	private Stock applyDefaults(Stock stock) {
		Stock resolved = stock == null ? new Stock() : stock;
		if (resolved.getSheetWidth() == null) {
			resolved.setSheetWidth(DEFAULT_SHEET_WIDTH);
		}
		if (resolved.getSheetHeight() == null) {
			resolved.setSheetHeight(DEFAULT_SHEET_HEIGHT);
		}
		if (resolved.getKerf() == null) {
			resolved.setKerf(DEFAULT_KERF);
		}
		if (resolved.getEdgeBand() == null) {
			resolved.setEdgeBand(DEFAULT_EDGE_BAND);
		}
		if (resolved.getSheetThickness() == null) {
			resolved.setSheetThickness(DEFAULT_SHEET_THICKNESS);
		}
		if (resolved.getAllowRotate() == null) {
			resolved.setAllowRotate(Boolean.TRUE);
		}
		if (resolved.getMargin() == null) {
			resolved.setMargin(DEFAULT_MARGIN);
		}
		if (resolved.getSheetWidth() <= 0 || resolved.getSheetHeight() <= 0) {
			throw new IllegalArgumentException("Sheet width and height must be positive.");
		}
		if (resolved.getKerf() < 0 || resolved.getMargin() < 0) {
			throw new IllegalArgumentException("Kerf and margin must be non-negative.");
		}
		return resolved;
	}

	private void validateBodies(List<BodyPayload> bodies) {
		if (bodies == null || bodies.isEmpty()) {
			throw new IllegalArgumentException("At least one body is required.");
		}
		for (BodyPayload body : bodies) {
			if (body == null) {
				throw new IllegalArgumentException("Body entries must not be null.");
			}
			if (!isPositive(body.getWidth()) || !isPositive(body.getHeight()) || !isPositive(body.getDeepth())) {
				throw new IllegalArgumentException("Body width, height, and deepth must be positive.");
			}
		}
	}

	private void validateParts(List<PartPayload> parts) {
		for (PartPayload part : parts) {
			if (part == null) {
				throw new IllegalArgumentException("Part entries must not be null.");
			}
			if (part.getWidth() <= 0 || part.getHeight() <= 0) {
				throw new IllegalArgumentException("Part width and height must be positive.");
			}
		}
	}

	private List<Part> resolveParts(CuttingPlanRequest request, Stock stock) {
		if (request.getParts() != null && !request.getParts().isEmpty()) {
			return buildPartsFromPayload(request.getParts());
		}
		return buildParts(request.getBodies(), stock);
	}

	private List<Part> buildParts(List<BodyPayload> bodies, Stock stock) {
		List<Part> parts = new ArrayList<>();
		int sheetThickness = stock.getSheetThickness();
		for (BodyPayload body : bodies) {
			String prefix = "body-" + (body.getId() == null ? "x" : body.getId());
			int width = body.getWidth();
			int height = body.getHeight();
			int depth = body.getDeepth();
			Long bodyId = body.getId();

			parts.add(new Part(prefix + "-left", "CARCASS_LEFT", bodyId, depth, height));
			parts.add(new Part(prefix + "-right", "CARCASS_RIGHT", bodyId, depth, height));
			parts.add(new Part(prefix + "-top", "CARCASS_TOP", bodyId, width - (2 * sheetThickness), depth));
			parts.add(new Part(prefix + "-bottom", "CARCASS_BOTTOM", bodyId, width - (2 * sheetThickness), depth));

			if (Boolean.TRUE.equals(body.getIncludeBack())) {
				parts.add(new Part(prefix + "-back", "CARCASS_BACK", bodyId, width, height));
			}

			if (body.getFrontElements() != null) {
				int index = 0;
				for (FrontElementPayload front : body.getFrontElements()) {
					if (front == null || front.getWidth() == null || front.getHeight() == null) {
						continue;
					}
					String partType = "FRONT_" + (front.getElementType() == null ? "UNKNOWN" : front.getElementType());
					parts.add(new Part(prefix + "-front-" + index, partType, bodyId, front.getWidth(),
						front.getHeight()));
					index++;
				}
			}

			if (body.getInteriorElements() != null) {
				int index = 0;
				for (InteriorElementPayload interior : body.getInteriorElements()) {
					if (interior == null) {
						continue;
					}
					String type = interior.getType() == null ? "unknown" : interior.getType();
					String partType = "INTERIOR_" + type.toUpperCase();
					if ("divider".equalsIgnoreCase(type)) {
						parts.add(new Part(prefix + "-divider-" + index, partType, bodyId,
							depth, height - (2 * sheetThickness)));
					} else {
						parts.add(new Part(prefix + "-shelf-" + index, partType, bodyId,
							width - (2 * sheetThickness), depth));
					}
					index++;
				}
			}
		}
		parts.removeIf(p -> p.width <= 0 || p.height <= 0);
		for (Part part : parts) {
			applyDefaultOrientationRules(part);
		}
		return parts;
	}

	private List<Part> buildPartsFromPayload(List<PartPayload> payload) {
		List<Part> parts = new ArrayList<>();
		if (payload == null) {
			return parts;
		}
		for (PartPayload part : payload) {
			if (part == null) continue;
			String id = part.getId() == null ? "part-" + parts.size() : part.getId();
			String type = part.getPartType() == null ? "CUSTOM" : part.getPartType();
			Part newPart = new Part(id, type, part.getBodyId(), part.getWidth(), part.getHeight());
			newPart.mustAlign = part.getMustAlign();
			newPart.allowRotate = part.getAllowRotate();
			applyDefaultOrientationRules(newPart);
			parts.add(newPart);
		}
		parts.removeIf(p -> p.width <= 0 || p.height <= 0);
		return parts;
	}

	private List<CuttingPlanSheet> pack(List<Part> parts, Stock stock) {
		List<Part> sorted = new ArrayList<>(parts);
		sorted.sort(Comparator.comparingInt((Part p) -> p.height).reversed());

		List<CuttingPlanSheet> sheets = new ArrayList<>();
		if (sorted.isEmpty()) {
			return sheets;
		}

		int sheetWidth = stock.getSheetWidth();
		int sheetHeight = stock.getSheetHeight();
		int margin = (int) Math.round(Objects.requireNonNullElse(stock.getMargin(), 0.0));
		int usableWidth = sheetWidth - (2 * margin);
		int usableHeight = sheetHeight - (2 * margin);
		int kerf = (int) Math.ceil(Objects.requireNonNullElse(stock.getKerf(), 0.0));
		boolean allowRotate = Boolean.TRUE.equals(stock.getAllowRotate());

		int sheetIndex = 0;
		List<CuttingPlanPartPlacement> placements = new ArrayList<>();
		int cursorX = margin;
		int cursorY = margin;
		int rowHeight = 0;

		for (Part part : sorted) {
			boolean partRotate = allowRotate && Boolean.TRUE.equals(part.allowRotate);
			boolean oversize = !canFitOnSheet(part, partRotate, usableWidth, usableHeight);
			PlacementCandidate candidate = selectOrientation(part, partRotate, usableWidth, cursorX - margin);
			if (!candidate.fitsInRow) {
				cursorX = margin;
				cursorY += rowHeight + kerf;
				rowHeight = 0;
				candidate = selectOrientation(part, partRotate, usableWidth, 0);
			}
			if (!candidate.fitsInSheet(cursorY - margin, usableHeight)) {
				sheets.add(new CuttingPlanSheet(sheetIndex, sheetWidth, sheetHeight, placements));
				sheetIndex++;
				placements = new ArrayList<>();
				cursorX = margin;
				cursorY = margin;
				rowHeight = 0;
				candidate = selectOrientation(part, partRotate, usableWidth, 0);
			}
			String partType = oversize ? "OVERSIZE_" + part.partType : part.partType;
			placements.add(new CuttingPlanPartPlacement(part.id, partType, cursorX, cursorY, candidate.width,
				candidate.height));
			cursorX += candidate.width + kerf;
			rowHeight = Math.max(rowHeight, candidate.height);
		}

		if (!placements.isEmpty()) {
			sheets.add(new CuttingPlanSheet(sheetIndex, sheetWidth, sheetHeight, placements));
		}
		return sheets;
	}

	private PlacementCandidate selectOrientation(Part part, boolean allowRotate, int usableWidth, int rowCursorX) {
		int width = part.width;
		int height = part.height;
		boolean fitsNormal = rowCursorX + width <= usableWidth;
		boolean fitsRotated = allowRotate && rowCursorX + height <= usableWidth;

		if (fitsNormal) {
			return new PlacementCandidate(width, height, true);
		}
		if (fitsRotated) {
			return new PlacementCandidate(height, width, true);
		}
		return new PlacementCandidate(width, height, false);
	}

	private boolean canFitOnSheet(Part part, boolean allowRotate, int usableWidth, int usableHeight) {
		boolean fitsNormal = part.width <= usableWidth && part.height <= usableHeight;
		boolean fitsRotated = allowRotate && part.height <= usableWidth && part.width <= usableHeight;
		return fitsNormal || fitsRotated;
	}

	private CuttingPlanStats buildStats(List<CuttingPlanSheet> sheets, List<Part> parts, Stock stock) {
		long usedArea = 0;
		for (Part part : parts) {
			usedArea += (long) part.width * (long) part.height;
		}
		int sheetCount = sheets.size();
		long sheetArea = (long) stock.getSheetWidth() * (long) stock.getSheetHeight();
		double wastePercent = 0.0;
		if (sheetCount > 0 && sheetArea > 0) {
			wastePercent = ((sheetArea * sheetCount) - usedArea) * 100.0 / (sheetArea * sheetCount);
		}
		return new CuttingPlanStats(sheetCount, usedArea, wastePercent);
	}

	private static class Part {
		private final String id;
		private final String partType;
		private final Long bodyId;
		private final int width;
		private final int height;
		private Boolean allowRotate;
		private Boolean mustAlign;

		private Part(String id, String partType, Long bodyId, int width, int height) {
			this.id = id;
			this.partType = partType;
			this.bodyId = bodyId;
			this.width = width;
			this.height = height;
		}
	}

	private void applyDefaultOrientationRules(Part part) {
		boolean mustAlign = part.mustAlign != null ? part.mustAlign : isMustAlignByType(part.partType);
		part.mustAlign = mustAlign;
		if (part.allowRotate == null) {
			part.allowRotate = !mustAlign;
		}
		if (part.mustAlign) {
			part.allowRotate = false;
		}
	}

	private boolean isMustAlignByType(String partType) {
		if (partType == null) return false;
		if (partType.startsWith("FRONT_")) return true;
		return "CARCASS_BACK".equals(partType);
	}

	private static class PlacementCandidate {
		private final int width;
		private final int height;
		private final boolean fitsInRow;

		private PlacementCandidate(int width, int height, boolean fitsInRow) {
			this.width = width;
			this.height = height;
			this.fitsInRow = fitsInRow;
		}

		private boolean fitsInSheet(int cursorY, int usableHeight) {
			return cursorY + height <= usableHeight;
		}
	}
}
