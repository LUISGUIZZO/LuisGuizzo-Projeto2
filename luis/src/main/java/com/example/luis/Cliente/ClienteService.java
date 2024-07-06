package com.example.luis.Cliente;


import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.luis.Pessoa.Pessoa;
import com.example.luis.Pessoa.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

   

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id, id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

   

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);

        validaPorCpfEEmail(objDTO);

        Cliente newObj = new Cliente(objDTO);

        return clienteRepository.save(newObj);
    }

    

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        objDTO.setId(id);

        Cliente oldObj = findById(id);

        if (!objDTO.getSenha().equals(oldObj.getSenha())) {
            objDTO.setSenha(objDTO.getSenha());
        }

        validaPorCpfEEmail(objDTO);

        oldObj = new Cliente(objDTO);
        return clienteRepository.save(oldObj);
    }

    

    public void delete(Integer id) {
        Cliente obj = findById(id);

        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }

        clienteRepository.deleteById(id);
    }


    private void validaPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> objCpf = pessoaRepository.findByCpf(objDTO.getCpf());

        if (objCpf.isPresent() && objCpf.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF ja existe");
        }

        Optional<Pessoa> objEmail = pessoaRepository.findByEmail(objDTO.getEmail());

        if (objEmail.isPresent() && objEmail.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("Email ja existe");
        }
    }
}
