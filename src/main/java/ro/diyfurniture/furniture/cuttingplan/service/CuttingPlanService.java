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
		List<Part> parts = buildParts(request.getBodies(), stock);
		List<CuttingPlanSheet> sheets = pack(parts, stock);
		CuttingPlanStats stats = buildStats(sheets, parts, stock);
		return new CuttingPlanResponse("OK", sheets, stats);
	}

	private void validateRequest(CuttingPlanRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request is required.");
		}
		if (request.getBodies() == null || request.getBodies().isEmpty()) {
			throw new IllegalArgumentException("At least one body is required.");
		}
		for (BodyPayload body : request.getBodies()) {
			if (body == null) {
				throw new IllegalArgumentException("Body entries must not be null.");
			}
			if (!isPositive(body.getWidth()) || !isPositive(body.getHeight()) || !isPositive(body.getDeepth())) {
				throw new IllegalArgumentException("Body width, height, and deepth must be positive.");
			}
		}
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

	private List<Part> buildParts(List<BodyPayload> bodies, Stock stock) {
		List<Part> parts = new ArrayList<>();
		int sheetThickness = stock.getSheetThickness();
		for (BodyPayload body : bodies) {
			String prefix = "body-" + (body.getId() == null ? "x" : body.getId());
			int width = body.getWidth();
			int height = body.getHeight();
			int depth = body.getDeepth();

			parts.add(new Part(prefix + "-left", "CARCASS_LEFT", depth, height));
			parts.add(new Part(prefix + "-right", "CARCASS_RIGHT", depth, height));
			parts.add(new Part(prefix + "-top", "CARCASS_TOP", width - (2 * sheetThickness), depth));
			parts.add(new Part(prefix + "-bottom", "CARCASS_BOTTOM", width - (2 * sheetThickness), depth));

			if (Boolean.TRUE.equals(body.getIncludeBack())) {
				parts.add(new Part(prefix + "-back", "CARCASS_BACK", width, height));
			}

			if (body.getFrontElements() != null) {
				int index = 0;
				for (FrontElementPayload front : body.getFrontElements()) {
					if (front == null || front.getWidth() == null || front.getHeight() == null) {
						continue;
					}
					String partType = "FRONT_" + (front.getElementType() == null ? "UNKNOWN" : front.getElementType());
					parts.add(new Part(prefix + "-front-" + index, partType, front.getWidth(), front.getHeight()));
					index++;
				}
			}
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
			boolean oversize = !canFitOnSheet(part, allowRotate, usableWidth, usableHeight);
			PlacementCandidate candidate = selectOrientation(part, allowRotate, usableWidth, cursorX - margin);
			if (!candidate.fitsInRow) {
				cursorX = margin;
				cursorY += rowHeight + kerf;
				rowHeight = 0;
				candidate = selectOrientation(part, allowRotate, usableWidth, 0);
			}
			if (!candidate.fitsInSheet(cursorY - margin, usableHeight)) {
				sheets.add(new CuttingPlanSheet(sheetIndex, sheetWidth, sheetHeight, placements));
				sheetIndex++;
				placements = new ArrayList<>();
				cursorX = margin;
				cursorY = margin;
				rowHeight = 0;
				candidate = selectOrientation(part, allowRotate, usableWidth, 0);
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
		private final int width;
		private final int height;

		private Part(String id, String partType, int width, int height) {
			this.id = id;
			this.partType = partType;
			this.width = width;
			this.height = height;
		}
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
