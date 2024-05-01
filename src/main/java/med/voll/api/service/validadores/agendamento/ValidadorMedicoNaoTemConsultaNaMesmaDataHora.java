package med.voll.api.service.validadores.agendamento;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.infra.exception.ValidacaoNegocioException;

@Component
public class ValidadorMedicoNaoTemConsultaNaMesmaDataHora implements AbstractValidadorAgendamento{

    @Autowired
    ConsultaRepository repository;

    @Override
    public void validar(DadosCadastroConsulta dados) {

        Optional<Long> idMedico = Optional.ofNullable(dados.idMedico());

        idMedico.ifPresent((id) -> {
            if (repository.temConsultaNaMesmaDataHora(id, dados.dataConsulta())) {
                throw new ValidacaoNegocioException("Médico já possui consulta neste horário.");
            }
        });
    
    }

}
