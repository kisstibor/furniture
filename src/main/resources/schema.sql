CREATE TABLE IF NOT EXISTS furniture.business_sheet_texture (
  sheet_type_id VARCHAR(128) PRIMARY KEY,
  business_id VARCHAR(128) NOT NULL,
  file_name VARCHAR(255) NOT NULL,
  content_type VARCHAR(128) NOT NULL,
  uploaded_at TIMESTAMP NOT NULL,
  size_in_bytes BIGINT NOT NULL,
  data BYTEA NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS furniture.pk_ui_log_event START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS furniture.ui_log_event (
  id BIGINT PRIMARY KEY DEFAULT nextval('furniture.pk_ui_log_event'),
  event_time TIMESTAMPTZ NOT NULL,
  received_at TIMESTAMPTZ NOT NULL,
  level VARCHAR(16) NOT NULL,
  message VARCHAR(2048) NOT NULL,
  route VARCHAR(1024),
  session_id VARCHAR(128),
  user_id VARCHAR(128),
  data_json TEXT
);

CREATE INDEX IF NOT EXISTS idx_ui_log_event_received_at ON furniture.ui_log_event (received_at DESC);
CREATE INDEX IF NOT EXISTS idx_ui_log_event_level ON furniture.ui_log_event (level);
CREATE INDEX IF NOT EXISTS idx_ui_log_event_session_id ON furniture.ui_log_event (session_id);
