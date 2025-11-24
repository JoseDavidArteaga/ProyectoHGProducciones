
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ClienteEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.ClienteRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ClienteDTO;

@Service
public class ClienteServiceImpl implements IClienteService {

	private ClienteRepository servicioAccesoBaseDatos;

	private ModelMapper modelMapper;

	// Constructor con inyección de dependencias
	public ClienteServiceImpl(ClienteRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ClienteDTO> findAll() {

		List<ClienteEntity> clientesEntity = this.servicioAccesoBaseDatos.findAll();
		List<ClienteDTO> clientesDTO = this.modelMapper.map(clientesEntity, new TypeToken<List<ClienteDTO>>() {
		}.getType());
		return clientesDTO;
	}

	@Override
	public ClienteDTO findById(Integer id) {
		ClienteEntity objCLienteEntity = this.servicioAccesoBaseDatos.findById(id).orElse(null);
		if (objCLienteEntity == null) {
			return null;
		}
		ClienteDTO clienteDTO = this.modelMapper.map(objCLienteEntity, ClienteDTO.class);
		return clienteDTO;
	}

	@Override
	public ClienteDTO save(ClienteDTO cliente) {
		ClienteEntity clienteEntity = this.modelMapper.map(cliente, ClienteEntity.class);
		clienteEntity.setCreateAt(new Date());
		ClienteEntity objCLienteEntity = this.servicioAccesoBaseDatos.save(clienteEntity);
		ClienteDTO clienteDTO = this.modelMapper.map(objCLienteEntity, ClienteDTO.class);
		return clienteDTO;  
	}
 
	@Override
	public ClienteDTO update(Integer id, ClienteDTO cliente) {
		ClienteEntity clienteExistente = this.servicioAccesoBaseDatos.findById(id).orElse(null);
		if (clienteExistente == null) {
			return null;
		}
		ClienteEntity clienteEntity = this.modelMapper.map(cliente, ClienteEntity.class);
		clienteEntity.setId(id); // Asegurar que mantiene el ID
		ClienteEntity clienteEntityActualizado = this.servicioAccesoBaseDatos.save(clienteEntity);
		ClienteDTO clienteDTO = this.modelMapper.map(clienteEntityActualizado, ClienteDTO.class);
		return clienteDTO;
	}

	@Override
	public boolean delete(Integer id) {
		if (this.servicioAccesoBaseDatos.existsById(id)) {
			this.servicioAccesoBaseDatos.deleteById(id);
			return true;
		}
		return false;
	}
}
