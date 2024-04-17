package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
    Long id,
    String medico,
    String paciente,
    LocalDateTime dataConsulta
){
    public DadosDetalhamentoConsulta(Consulta consulta){
        this(
        consulta.getId(), 
        consulta.getMedico().getNome(), 
        consulta.getPaciente().getNome(), 
        consulta.getDataConsulta()
        );
    }
}
