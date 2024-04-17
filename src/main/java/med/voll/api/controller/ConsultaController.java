package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody DadosCadastroConsulta dados){

        if (!medicoRepository.existsById(dados.idMedico())) {
             throw new EntityNotFoundException("Médico não encontrado");
        }

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new EntityNotFoundException("Paciente não encontrado");
        }

        Consulta consulta = new Consulta(dados);
        consulta.setMedico(medicoRepository.getReferenceById(dados.idMedico()));
        consulta.setPaciente(pacienteRepository.getReferenceById(dados.idPaciente()));

        consulta = consultaRepository.save(consulta);

        return ResponseEntity.ok().body(new DadosDetalhamentoConsulta(consulta));
        
    }

}
