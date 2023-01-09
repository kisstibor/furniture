package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.exception.ConnectionToolNotFoundException;
import ro.sapientia.furniture.model.ConnectionTool;
import ro.sapientia.furniture.repository.ConnectionToolRepository;


@Service
public class ConnectionToolService{

	private final ConnectionToolRepository connectionToolRepository;

	public ConnectionToolService(final ConnectionToolRepository connectionToolRepository){
		this.connectionToolRepository = connectionToolRepository;
	}

	public List<ConnectionTool> findAllConnectionTools(){
		return this.connectionToolRepository.findAll();
	}

	public ConnectionTool findConnectionToolById(final Long id){
		return this.connectionToolRepository.findById(id).orElseThrow(() -> new ConnectionToolNotFoundException(id));
	}


	public List<ConnectionTool> findConnectionToolsBySize(final int size)
	{
		return this.connectionToolRepository.findConnectionToolsBySize(size);
	}

	public List<ConnectionTool> findConnectionToolsByType(final String type)
	{
		return this.connectionToolRepository.findConnectionToolsByType(type);
	}

	public ConnectionTool create(ConnectionTool connectionTool){
		final ConnectionTool newConnectionTool = ConnectionTool.builder()
				.id(connectionTool.getId())
				.size(connectionTool.getSize())
				.type(connectionTool.getType())
				.build();
		return this.connectionToolRepository.saveAndFlush(newConnectionTool);
	}

	public ConnectionTool update(ConnectionTool connectionTool){
		findConnectionToolById(connectionTool.getId());
		return this.connectionToolRepository.saveAndFlush(connectionTool);
	}

	public void delete(Long id){
		findConnectionToolById(id);
		this.connectionToolRepository.deleteById(id);


	}
}