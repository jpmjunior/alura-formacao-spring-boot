package med.voll.api.service.validadores.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.infra.exception.ValidacaoNegocioException;

@Component
public class ValidadorSomenteUmaConsultaPorPacientePorDia implements AbstractValidadorAgendamento{

    @Autowired
    ConsultaRepository repository;

    @Override
    public void validar(DadosCadastroConsulta dados) {

        boolean pacienteTemConsultaNaData = repository.temConsultaNaData(dados.idPaciente(), dados.dataConsulta().toLocalDate());

        if (pacienteTemConsultaNaData) {
            throw new ValidacaoNegocioException("Paciente já possui consulta na data informada");
        }
    
    }

}
