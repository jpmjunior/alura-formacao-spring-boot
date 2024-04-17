package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosCadastroConsulta(
    Long idMedico,
    Long idPaciente,
    LocalDateTime dataConsulta
){}
