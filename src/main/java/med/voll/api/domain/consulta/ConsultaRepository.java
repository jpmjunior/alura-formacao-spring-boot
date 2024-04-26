package med.voll.api.domain.consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.validation.constraints.NotNull;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

    @Query("SELECT EXISTS (SELECT c.id FROM Consulta c WHERE c.paciente.id = :idPaciente AND DATE(c.dataConsulta) = :dataConsulta)")
    boolean temConsultaNaData(@NotNull Long idPaciente, @NotNull LocalDate dataConsulta);

    @Query("SELECT EXISTS (SELECT c.id FROM Consulta c WHERE c.medico.id = :idMedico AND c.dataConsulta = :dataConsulta)")
    boolean temConsultaNaMesmaDataHora(Long idMedico, @NotNull LocalDateTime dataConsulta);

}
