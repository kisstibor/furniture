package ro.sapientia.furniture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sapientia.furniture.model.ConnectionTool;

public interface ConnectionToolRepository extends JpaRepository<ConnectionTool, Long>{
	public List<ConnectionTool> findConnectionToolsByType(String type);
	public List<ConnectionTool> findConnectionToolsBySize(int size);
}