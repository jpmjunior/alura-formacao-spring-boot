package med.voll.api.service.validadores.agendamento;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.medico.MedicoRepository;

@Component
public class ValidadorMedicoExiste implements AbstractValidadorAgendamento{

    @Autowired
    MedicoRepository repository;

    @Override
    public void validar(DadosCadastroConsulta dados) {
    
        Optional<Long> idMedico = Optional.ofNullable(dados.idMedico());

        idMedico.ifPresent((id) -> {
            if (!repository.existsById(id)) {
                throw new EntityNotFoundException("Médico não encontrado");
            }
        });
    
    }

}
