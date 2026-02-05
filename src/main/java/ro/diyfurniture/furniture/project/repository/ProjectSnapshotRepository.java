package ro.diyfurniture.furniture.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.diyfurniture.furniture.project.model.ProjectSnapshot;

public interface ProjectSnapshotRepository extends JpaRepository<ProjectSnapshot, Long> {

	List<ProjectSnapshot> findByOwnerIdOrderByUpdatedAtDesc(String ownerId);

	Optional<ProjectSnapshot> findByOwnerIdAndProjectId(String ownerId, String projectId);
}
