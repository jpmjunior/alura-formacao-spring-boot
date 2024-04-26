package med.voll.api.service.validadores;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.infra.exception.ValidacaoNegocioException;

@Component
public class ValidadorAntecedenciaMinima implements AbstractValidadorNegocio{

    @Override
    public void validar(DadosCadastroConsulta dados) {
        
        if (LocalDateTime.now().plusMinutes(30).isAfter(dados.dataConsulta())) {
            throw new ValidacaoNegocioException("Consulta deve ser marcada com no mínimo 30 minutos de antecedência");
        }
    }

}
