package med.voll.api.service.validadores.agendamento;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.infra.exception.ValidacaoNegocioException;

@Component
public class ValidadorCamposConsulta implements AbstractValidadorAgendamento{

    @Override
    public void validar(DadosCadastroConsulta dados) {
    
        if (dados.idMedico() == null && dados.especialidade() == null) {
            throw new ValidacaoNegocioException("Médico ou especialidade devem ser informados");
        }
    
    }

}
