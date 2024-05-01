package med.voll.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.service.validadores.agendamento.AbstractValidadorAgendamento;

@Service
public class AgendamentoService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    List<AbstractValidadorAgendamento> validadores;

    public Consulta agendar(DadosCadastroConsulta dados){

        validadores.forEach(v -> v.validar(dados));

        DadosCadastroConsulta dadosAtualizados = new DadosCadastroConsulta(escolherIdMedico(dados), dados.idPaciente(), dados.dataConsulta(), dados.especialidade());
        Consulta consulta = new Consulta(dadosAtualizados);
        consulta.getMedico().setNome(medicoRepository.findNomeById(dadosAtualizados.idMedico()));
        consulta.getPaciente().setNome(pacienteRepository.findNomeById(dadosAtualizados.idPaciente()));

        return consultaRepository.save(consulta);

    }

    public Long escolherIdMedico(DadosCadastroConsulta dados) {

        Optional<Long> idMedico = Optional.ofNullable(dados.idMedico());
        return idMedico.orElseGet(() -> medicoRepository.consultaMedicoAleatorio(dados.especialidade(), dados.dataConsulta()));

    }

}
