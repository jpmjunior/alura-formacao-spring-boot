package med.voll.api.service.validadores.cancelamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.infra.exception.ValidacaoNegocioException;

@Component
public class ValidadorConsultaJaCancelada implements AbstractValidadorCancelamento{

    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        
        if (consultaRepository.existsbyIdAndMotivoNotNull(dados.idConsulta())) {
            throw new ValidacaoNegocioException("Consulta já se encontra cancelada.");
        }

    }

}
