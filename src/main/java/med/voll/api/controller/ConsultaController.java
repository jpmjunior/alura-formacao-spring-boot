package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsultaAgendada;
import med.voll.api.domain.consulta.DadosDetalhamentoConsultaCancelada;
import med.voll.api.service.AgendamentoService;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsultaAgendada> agendar(@RequestBody @Valid DadosCadastroConsulta dados){

        Consulta consulta = agendamentoService.agendar(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoConsultaAgendada(consulta));
        
    }

    @PostMapping("/cancelamento")
    public ResponseEntity<DadosDetalhamentoConsultaCancelada> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        
        Consulta consulta = agendamentoService.cancelar(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoConsultaCancelada(consulta));

    }

}
