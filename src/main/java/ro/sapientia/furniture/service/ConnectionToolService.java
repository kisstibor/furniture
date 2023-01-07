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
		return this.connectionToolRepository.findById(id).orElse(null);
	}

	public List<ConnectionTool> findConnectionToolBySize(final int size)
	{
		return this.connectionToolRepository.findBySize(size);
	}

	public List<ConnectionTool> findConnectionToolByType(final String type)
	{
		return this.connectionToolRepository.findByType(type);
	}

	public ConnectionTool create(ConnectionTool connectionTool){
		final ConnectionTool newConnectionTool = ConnectionTool.builder()
				.size(connectionTool.getSize())
				.type(connectionTool.getType())
				.build();
		return this.connectionToolRepository.saveAndFlush(newConnectionTool);
	}

	public ConnectionTool update(ConnectionTool connectionTool){
		final ConnectionTool searchConnectionTool = findConnectionToolById(connectionTool.getId());
		if(searchConnectionTool == null)
		{
			return null;
		}
		final ConnectionTool upConnectionTool = ConnectionTool.builder()
					.id(searchConnectionTool.getId())
					.size(searchConnectionTool.getSize())
					.type(connectionTool.getType())
					.build();
		return this.connectionToolRepository.saveAndFlush(upConnectionTool);
	}

	public void delete(Long id){
		ConnectionTool connectionTool = findConnectionToolById(id);
		if(connectionTool == null)
		{
			System.out.println("Does not exist this connection tool!");
		}
		else
		{
			this.connectionToolRepository.deleteById(id);
		}
	}
}