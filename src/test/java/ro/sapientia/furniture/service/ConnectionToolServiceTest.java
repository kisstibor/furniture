package ro.sapientia.furniture.service;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ro.sapientia.furniture.model.ConnectionTool;
import ro.sapientia.furniture.repository.ConnectionToolRepository;

public class ConnectionToolServiceTest{
	private ConnectionToolRepository repositoryMock;
	private ConnectionToolService service;

	@BeforeEach
	public void setUp(){
		repositoryMock = mock(ConnectionToolRepository.class);
		service = new ConnectionToolService(repositoryMock);
	}

	@Test
	public void testFindAllConnectionTools_empytList(){
		when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
		final List<ConnectionTool> connectionTools = service.findAllConnectionTools();

		assertEquals(0, connectionTools.size());
	}

	@Test
	public void testGetAllConnectionTools() {
		//given
		ConnectionTool connectionTool = new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");
		//when
		when(repositoryMock.findAll()).thenReturn(List.of(connectionTool));
		List<ConnectionTool> result = service.findAllConnectionTools();

		//then
		assertEquals(1, result.size());
	}

	@Test
	public void testFindConnectionToolsBySize() {
		//given
		ConnectionTool connectionTool = new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(repositoryMock.findConnectionToolsBySize(connectionTool.getSize())).thenReturn(List.of(connectionTool));
		List<ConnectionTool> result = service.findConnectionToolsBySize(3);

		//then
		assertEquals(1, result.size());
	}

	@Test
	public void testFindConnectionToolsByType() {
		//given
	    ConnectionTool connectionTool = new ConnectionTool();
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(repositoryMock.findConnectionToolsByType(connectionTool.getType())).thenReturn(List.of(connectionTool));
		List<ConnectionTool> result = service.findConnectionToolsByType("sin");

		//then
		assertEquals(1, result.size());
	}

	@Test
	public void testFindConnectionToolById()
	{
		//given
		final ConnectionTool connectionTool = new ConnectionTool();
		final long ctId = 1;
		connectionTool.setId(ctId);
		connectionTool.setSize(3);
		connectionTool.setType("sin");
		given(repositoryMock.findById(1L)).willReturn(Optional.of(connectionTool));

		//when
		ConnectionTool findConnectionTool = service.findConnectionToolById(connectionTool.getId());

		//then
		assertEquals((long) findConnectionTool.getId(), ctId);
	}

	@Test
	public void testDeleteConnectionToolById()
	{
		//given
		final ConnectionTool connectionTool = new ConnectionTool();
		final long ctId = 1;
		connectionTool.setId(ctId);
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		willDoNothing().given(repositoryMock).deleteById(connectionTool.getId());

		//when
		service.delete(connectionTool.getId());

		//then
		assertTrue(true);


	}


	@Test
	public void testCreateConnectionTool()
	{
		//given
		final ConnectionTool connectionTool = new ConnectionTool();
		final long ctId = 1;
		connectionTool.setId(ctId);
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(repositoryMock.saveAndFlush(any(ConnectionTool.class))).thenReturn(connectionTool);
		ConnectionTool newConnectionTool = service.create(connectionTool);

		//then
		assertEquals(ctId, (long) newConnectionTool.getId());

	}

	@Test
	public void testUpdateConnectionTool()
	{
		//given
		final ConnectionTool connectionTool = new ConnectionTool();
		final long myId = 1;
		connectionTool.setId(myId);
		connectionTool.setSize(4);
		connectionTool.setType("sin");

		//when
		when(repositoryMock.saveAndFlush(any(ConnectionTool.class))).thenReturn(connectionTool);
		ConnectionTool newConnectionTool = service.create(connectionTool);
		newConnectionTool.setSize(3);

		ConnectionTool updatedConnectionTool = service.update(newConnectionTool);

		//then
        assertEquals(3, updatedConnectionTool.getSize());
	}

	@Test
	public void testUpdateConnectionToolFailed()
	{
		//given
		final ConnectionTool connectionTool = new ConnectionTool();
		final long myId = 1;
		connectionTool.setId(myId);
		connectionTool.setSize(4);
		connectionTool.setType("sin");

		//when
		when(repositoryMock.findById(any())).thenReturn(Optional.empty());

		ConnectionTool updatedConnectionTool = service.update(connectionTool);

		//then
        assertNull(updatedConnectionTool);
	}





}