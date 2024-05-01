package med.voll.api.service.validadores.agendamento;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoNegocioException;

@Component
public class ValidadorMedicoAtivo implements AbstractValidadorAgendamento{

    @Autowired
    MedicoRepository repository;

    @Override
    public void validar(DadosCadastroConsulta dados) {
        
        Optional<Long> idMedico = Optional.ofNullable(dados.idMedico());
        
        idMedico.ifPresent((id) -> {
            if (!repository.getReferenceById(id).getAtivo()) {
                throw new ValidacaoNegocioException("Medico não está ativo.");
            }
        });

    }

}
