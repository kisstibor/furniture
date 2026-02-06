package ro.diyfurniture.furniture.business.model;

import java.time.Instant;

public class WorktopTextureResponse {
	private String worktopId;
	private String fileName;
	private String contentType;
	private long sizeInBytes;
	private Instant uploadedAt;

	public WorktopTextureResponse(
		String worktopId,
		String fileName,
		String contentType,
		long sizeInBytes,
		Instant uploadedAt
	) {
		this.worktopId = worktopId;
		this.fileName = fileName;
		this.contentType = contentType;
		this.sizeInBytes = sizeInBytes;
		this.uploadedAt = uploadedAt;
	}

	public String getWorktopId() {
		return worktopId;
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
