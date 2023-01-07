package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
		return this.connectionToolRepository.findById(id).get();
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
		return this.connectionToolRepository.saveAndFlush(connectionTool);
	}

	public ConnectionTool update(ConnectionTool connectionTool){
		return this.connectionToolRepository.saveAndFlush(connectionTool);
	}

	public void delete(Long id){
		this.connectionToolRepository.deleteById(id);

	}
}