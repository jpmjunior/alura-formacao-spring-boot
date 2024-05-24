package med.voll.api.service.validadores.cancelamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

@Component
public class ValidadorConsultaExiste implements AbstractValidadorCancelamento{

    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(DadosCancelamentoConsulta dados) {

        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new EntityNotFoundException("Consulta inexistente.");
        }

    }

}
