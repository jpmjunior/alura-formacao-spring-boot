package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;
    
    @NotNull
    @Column(name = "data_consulta")
    private LocalDateTime dataConsulta;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_cancelamento")
    private MotivoCancelamento motivoCancelamento;

    public Consulta(DadosCadastroConsulta dados) {
        this.medico = new Medico();
        medico.setId(dados.idMedico());

        this.paciente = new Paciente();
        paciente.setId(dados.idPaciente());

        this.dataConsulta = dados.dataConsulta();

        this.motivoCancelamento = null;
    }

}
