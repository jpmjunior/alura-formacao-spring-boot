package med.voll.api.service.validadores.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

public interface AbstractValidadorCancelamento {

    void validar(DadosCancelamentoConsulta dados);

}
