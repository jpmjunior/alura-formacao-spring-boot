package med.voll.api.service.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoNegocioException;

@Component
public class ValidadorPacienteAtivo implements AbstractValidadorNegocio{

    @Autowired
    PacienteRepository repository;

    @Override
    public void validar(DadosCadastroConsulta dados) {
    
        Paciente paciente = repository.getReferenceById(dados.idPaciente());

        if (!paciente.getAtivo()) {
            throw new ValidacaoNegocioException("Paciente não está ativo.");
        }
    
    }

}
