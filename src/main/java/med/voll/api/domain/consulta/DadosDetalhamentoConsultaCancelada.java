package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultaCancelada(
    Long id,
    String medico,
    String paciente,
    LocalDateTime dataConsulta,
    MotivoCancelamento motivoCancelamento
){
    public DadosDetalhamentoConsultaCancelada(Consulta consulta){
        this(
        consulta.getId(), 
        consulta.getMedico().getNome(), 
        consulta.getPaciente().getNome(), 
        consulta.getDataConsulta(),
        consulta.getMotivoCancelamento()
        );
    }
}
