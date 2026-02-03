package ro.diyfurniture.furniture.business.model;

import java.time.Instant;

public class SheetTextureResponse {
	private String sheetTypeId;
	private String fileName;
	private String contentType;
	private long sizeInBytes;
	private Instant uploadedAt;

	public SheetTextureResponse(
		String sheetTypeId,
		String fileName,
		String contentType,
		long sizeInBytes,
		Instant uploadedAt
	) {
		this.sheetTypeId = sheetTypeId;
		this.fileName = fileName;
		this.contentType = contentType;
		this.sizeInBytes = sizeInBytes;
		this.uploadedAt = uploadedAt;
	}

	public String getSheetTypeId() {
		return sheetTypeId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public Instant getUploadedAt() {
		return uploadedAt;
	}
}
