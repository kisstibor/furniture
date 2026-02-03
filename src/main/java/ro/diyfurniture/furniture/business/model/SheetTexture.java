package ro.diyfurniture.furniture.business.model;

import java.time.Instant;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "business_sheet_texture")
public class SheetTexture {
	@Id
	@Column(name = "sheet_type_id", nullable = false, updatable = false)
	private String sheetTypeId;

	@Column(name = "business_id", nullable = false)
	private String businessId;

	@Column(name = "file_name", nullable = false)
	private String fileName;

	@Column(name = "content_type", nullable = false)
	private String contentType;

	@Column(name = "uploaded_at", nullable = false)
	private Instant uploadedAt;

	@Column(name = "size_in_bytes", nullable = false)
	private long sizeInBytes;

	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "data", nullable = false)
	private byte[] data;

	public String getSheetTypeId() {
		return sheetTypeId;
	}

	public void setSheetTypeId(String sheetTypeId) {
		this.sheetTypeId = sheetTypeId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Instant getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(Instant uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
