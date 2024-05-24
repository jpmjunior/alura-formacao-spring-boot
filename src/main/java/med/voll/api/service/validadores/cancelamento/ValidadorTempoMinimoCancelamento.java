package med.voll.api.service.validadores.cancelamento;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.infra.exception.ValidacaoNegocioException;

@Component
public class ValidadorTempoMinimoCancelamento implements AbstractValidadorCancelamento{

    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(DadosCancelamentoConsulta dados) {

        Optional<Consulta> consulta = consultaRepository.findById(dados.idConsulta());

        consulta.ifPresent(c -> {
            if(LocalDateTime.now().plusHours(24).isAfter(c.getDataConsulta())) {
                throw new ValidacaoNegocioException("O cancelamento deve ser feito com no mínimo 24 horas de antecedência.");
            }
        });
    
    }

}
