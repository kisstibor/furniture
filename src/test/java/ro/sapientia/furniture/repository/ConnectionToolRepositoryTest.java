package ro.sapientia.furniture.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import ro.sapientia.furniture.model.ConnectionTool;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class ConnectionToolRepositoryTest {
	@Autowired
	ConnectionToolRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void testBeEmpty() {
		var result = repository.findAll();
		assertEquals(0, result.size());
	}

	@Test
	public void testContainOneElement()
	{
		ConnectionTool connectionTool = ConnectionTool.builder().type("sin").size(3).build();
		connectionTool = entityManager.persistAndFlush(connectionTool);

		List<ConnectionTool> connectionTools = repository.findAll();
		assertEquals(1,connectionTools.size());
	}

	@Test
	public void testFindConnectionToolById()
	{
		ConnectionTool connectionTool = ConnectionTool.builder().type("sin").size(3).build();
		connectionTool = entityManager.persistAndFlush(connectionTool);

		ConnectionTool findConnectionTool = repository.findById(connectionTool.getId()).get();
		assertEquals(connectionTool.getId(), findConnectionTool.getId());
	}
	@Test
	public void testFindConnectionToolByIdFialed()
	{
		Optional<ConnectionTool> connectionTool = repository.findById(100L);

		assertTrue(connectionTool.isEmpty());
	}
	@Test
	public void testDeleteConnactionToolById()
	{
		ConnectionTool connectionTool = ConnectionTool.builder().type("sin").size(3).build();
		connectionTool = entityManager.persistAndFlush(connectionTool);

		repository.deleteById(connectionTool.getId());
		List<ConnectionTool> connectionTools = repository.findAll();
		assertEquals(0,connectionTools.size());
	}

	@Test
	public void testFindConnectionToolsBySize()
	{
		ConnectionTool connectionTool = ConnectionTool.builder().type("sin").size(3).build();
		connectionTool = entityManager.persistAndFlush(connectionTool);

		List<ConnectionTool> connectionTools = repository.findConnectionToolsBySize(connectionTool.getSize());
		assertEquals(1,connectionTools.size());
	}

	@Test
	public void testFindConnectionToolsByType()
	{
		ConnectionTool connectionTool = ConnectionTool.builder().type("sin").size(3).build();
		connectionTool = entityManager.persistAndFlush(connectionTool);

		List<ConnectionTool> connectionTools = repository.findConnectionToolsByType(connectionTool.getType());
		assertEquals(1,connectionTools.size());
	}

	@Test
	public void testCreateAConnectionTool()
	{
		ConnectionTool connectionTool = ConnectionTool.builder().type("sin").size(3).build();

		connectionTool = repository.saveAndFlush(connectionTool);
		assertEquals(connectionTool, repository.findById(connectionTool.getId()).get());

	}

	@Test
	public  void testUpdateConnectionTool()
	{
		ConnectionTool connectionTool = ConnectionTool.builder().type("sin").size(4).build();

		connectionTool = repository.saveAndFlush(connectionTool);
		connectionTool.setSize(3);

		connectionTool = repository.saveAndFlush(connectionTool);

		assertEquals(3, connectionTool.getSize());
	}

}
