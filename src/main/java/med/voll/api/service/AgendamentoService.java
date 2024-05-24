package med.voll.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.service.validadores.agendamento.AbstractValidadorAgendamento;
import med.voll.api.service.validadores.cancelamento.AbstractValidadorCancelamento;

@Service
public class AgendamentoService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    List<AbstractValidadorAgendamento> validadoresAgendamento;

    @Autowired
    private List<AbstractValidadorCancelamento> validadoresCancelamento;

    public Consulta agendar(DadosCadastroConsulta dados){

        validadoresAgendamento.forEach(v -> v.validar(dados));

        DadosCadastroConsulta dadosAtualizados = new DadosCadastroConsulta(escolherMedico(dados), dados.idPaciente(), dados.dataConsulta(), dados.especialidade());
        Consulta consulta = new Consulta(dadosAtualizados);
        consulta.getMedico().setNome(medicoRepository.findNomeById(dadosAtualizados.idMedico()));
        consulta.getPaciente().setNome(pacienteRepository.findNomeById(dadosAtualizados.idPaciente()));

        return consultaRepository.save(consulta);

    }

    private Long escolherMedico(DadosCadastroConsulta dados) {

        Optional<Long> idMedico = Optional.ofNullable(dados.idMedico());
        return idMedico.orElse(medicoRepository.consultaMedicoAleatorio(dados.especialidade(), dados.dataConsulta()));
    
    }

    @Transactional
    public Consulta cancelar(@Valid DadosCancelamentoConsulta dados) {
    
        validadoresCancelamento.forEach(v -> v.validar(dados));

        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.setMotivoCancelamento(dados.motivo());

        return consulta;

    }


}
