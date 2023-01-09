package ro.sapientia.furniture.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import ro.sapientia.furniture.exception.ConnectionToolNotFoundException;
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
	public void testFindAllConnectionToolsBySize_empytList(){
		when(repositoryMock.findConnectionToolsBySize(100)).thenReturn(Collections.emptyList());
		final List<ConnectionTool> connectionTools = service.findConnectionToolsBySize(100);

		assertEquals(0, connectionTools.size());
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
	public void testFindAllConnectionToolsByType_empytList(){
		when(repositoryMock.findConnectionToolsByType("alma")).thenReturn(Collections.emptyList());
		final List<ConnectionTool> connectionTools = service.findConnectionToolsByType("alma");

		assertEquals(0, connectionTools.size());
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
	public void testFinConnectionToolByIdFailed()
	{
		//given

		//when
		when(repositoryMock.findById(any())).thenReturn(Optional.empty());


		//then
        assertThrows(ConnectionToolNotFoundException.class,() -> service.findConnectionToolById(100L));
	}

	@Test
	public void testAddConnectionTool()
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
	public void testAddConnectionToolFailed()
	{
		//given
		final ConnectionTool connectionTool = new ConnectionTool();
		final long ctId = 1;
		connectionTool.setId(ctId);
		connectionTool.setSize(4);
		connectionTool.setType("sin");

		//when
		when(repositoryMock.saveAndFlush(any())).thenReturn(null);

		ConnectionTool newConnectionTool = service.create(connectionTool);

		//then
        assertNull(newConnectionTool);
	}

	@Test
	public void testUpdateConnectionTool()
	{
		//given
		final ConnectionTool connectionTool = new ConnectionTool();
		final long ctId = 1;
		connectionTool.setId(ctId);
		connectionTool.setSize(4);
		connectionTool.setType("sin");

		//when
		when(repositoryMock.saveAndFlush(any(ConnectionTool.class))).thenReturn(connectionTool);
		ConnectionTool newConnectionTool = service.create(connectionTool);
		newConnectionTool.setSize(3);
		when(repositoryMock.findById(ctId)).thenReturn(Optional.of(newConnectionTool));

		ConnectionTool updatedConnectionTool = service.update(newConnectionTool);

		//then
	    assertEquals(newConnectionTool, updatedConnectionTool);


	}

	@Test
	public void testUpdateConnectionToolFailed()
	{
		//given
		final ConnectionTool connectionTool = new ConnectionTool();
		final long ctId = 100;
		connectionTool.setId(ctId);
		connectionTool.setSize(4);
		connectionTool.setType("sin");

		//when
		when(repositoryMock.saveAndFlush(any(ConnectionTool.class))).thenReturn(connectionTool);

		//then
		assertThrows(ConnectionToolNotFoundException.class,() -> service.update(connectionTool));

	}


	@Test
	public void testDeleteconnectionToolById()
	{
		final ConnectionTool connectionTool = new ConnectionTool();
		final long ctId = 1;
		connectionTool.setId(ctId);
		connectionTool.setSize(3);
		connectionTool.setType("sin");

		//when
		when(repositoryMock.saveAndFlush(any(ConnectionTool.class))).thenReturn(connectionTool);
		ConnectionTool newConnectionTool = service.create(connectionTool);
		given(repositoryMock.findById(ctId)).willReturn(Optional.of(newConnectionTool));
		willDoNothing().given(repositoryMock).deleteById(newConnectionTool.getId());

        service.delete(newConnectionTool.getId());

        //then
        verify(repositoryMock, times(1)).deleteById(ctId);
	}

	@Test
	public void testDeleteconnectionToolByIdFailed()
	{
		//given

		//when
		willDoNothing().given(repositoryMock).deleteById(100L);

		//then
		assertThrows(ConnectionToolNotFoundException.class,() -> service.delete(100L));


	}
}
