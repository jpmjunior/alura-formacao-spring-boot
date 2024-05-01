package med.voll.api.service.validadores.agendamento;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.infra.exception.ValidacaoNegocioException;

@Component
public class ValidadorHorarioFuncionamento implements AbstractValidadorAgendamento{

    @Override
    public void validar(DadosCadastroConsulta dados) {
        LocalDateTime dataHora = dados.dataConsulta();

        boolean ehDomingo = dataHora.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesDaAbertura = dataHora.getHour() < 8;
        boolean depoisDaAbertura = dataHora.getHour() > 18 || (dataHora.getHour() == 18 && dataHora.getMinute() > 0);

        if ( ehDomingo || antesDaAbertura || depoisDaAbertura) {
            throw new ValidacaoNegocioException("Consulta fora do horário de funcionamento da clínica");
        }
        
    }
    
    

}
