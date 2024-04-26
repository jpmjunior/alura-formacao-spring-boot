package med.voll.api.service.validadores;

import med.voll.api.domain.consulta.DadosCadastroConsulta;

public interface AbstractValidadorNegocio {

    public void validar(DadosCadastroConsulta dados);

}
