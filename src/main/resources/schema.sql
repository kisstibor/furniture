CREATE TABLE IF NOT EXISTS furniture.business_sheet_texture (
  sheet_type_id VARCHAR(128) PRIMARY KEY,
  business_id VARCHAR(128) NOT NULL,
  file_name VARCHAR(255) NOT NULL,
  content_type VARCHAR(128) NOT NULL,
  uploaded_at TIMESTAMP NOT NULL,
  size_in_bytes BIGINT NOT NULL,
  data BYTEA NOT NULL
);
