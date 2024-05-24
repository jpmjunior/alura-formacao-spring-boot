package med.voll.api.domain.consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

    @Query("SELECT EXISTS (SELECT c.id FROM Consulta c WHERE c.paciente.id = :idPaciente AND DATE(c.dataConsulta) = :dataConsulta AND c.motivoCancelamento IS NULL)")
    boolean temConsultaNaData(Long idPaciente, LocalDate dataConsulta);

    @Query("SELECT EXISTS (SELECT c.id FROM Consulta c WHERE c.medico.id = :idMedico AND c.dataConsulta = :dataConsulta AND c.motivoCancelamento IS NULL)")
    boolean temConsultaNaMesmaDataHora(Long idMedico, LocalDateTime dataConsulta);

    @Query("SELECT EXISTS (SELECT c.id FROM Consulta c WHERE c.id = :idConsulta AND c.motivoCancelamento IS NOT NULL)")
    boolean existsbyIdAndMotivoNotNull(Long idConsulta);

}
