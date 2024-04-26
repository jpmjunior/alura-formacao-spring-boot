package med.voll.api.service.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

@Component
public class ValidadorPacienteExiste implements AbstractValidadorNegocio{

    @Autowired
    PacienteRepository repository;

    @Override
    public void validar(DadosCadastroConsulta dados) {
    
        if (!repository.existsById(dados.idPaciente())) {
            throw new EntityNotFoundException("Paciente não encontrado");
        }
        
    }

}
