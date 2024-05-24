package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT m.nome FROM Medico m WHERE m.id = :id")
    String findNomeById(Long id);

    @Query("""
        SELECT m.id FROM Medico m WHERE m.ativo = true AND m.especialidade = :especialidade
        AND m.id NOT IN (
            SELECT c.medico.id FROM Consulta c 
            WHERE c.dataConsulta = :dataConsulta AND c.motivoCancelamento IS NULL)
        ORDER BY RAND() LIMIT 1
    """)
    Long consultaMedicoAleatorio(Especialidade especialidade, LocalDateTime dataConsulta);

    @Query("SELECT m.especialidade FROM Medico m WHERE m.id = :idMedico")
    Especialidade getEspecialidadeById(Long idMedico);
}
