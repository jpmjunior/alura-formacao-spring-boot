package med.voll.api.controller;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsultaAgendada;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.service.AgendamentoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AgendamentoService agendamentoService;

    @Autowired
    JacksonTester<DadosCadastroConsulta> dadosCadastroConsultaJson;

    @Autowired
    JacksonTester<DadosDetalhamentoConsultaAgendada> dadosDetalhamentoConsultaAgendadaJson;

    @Test
    @WithMockUser
    @DisplayName("Deveria devolver codico HTTP 400 quando informacoes estao invalidas")
    void agendar_cenario1() throws Exception {

        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @WithMockUser
    @DisplayName("Deveria devolver codico HTTP 200 quando informacoes estao validas")
    void agendar_cenario2() throws Exception {

        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var dadosCadastroConsulta = new DadosCadastroConsulta(1L, 1L, proximaSegundaAs10, Especialidade.CARDIOLOGIA);
        var dadosDetalhamentoConsultaAgendada = new DadosDetalhamentoConsultaAgendada(3L, "Jose Pinheiro", "Jardson Silva", proximaSegundaAs10);
        var consulta = getConsulta(dadosCadastroConsulta);

        var json = dadosCadastroConsultaJson.write(dadosCadastroConsulta).getJson();

        when(agendamentoService.agendar(dadosCadastroConsulta)).thenReturn(consulta);

        var response = mvc.perform(
                post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse();

        var jsonEsperado = dadosDetalhamentoConsultaAgendadaJson.write(dadosDetalhamentoConsultaAgendada).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

    private Consulta getConsulta(DadosCadastroConsulta dadosCadastroConsulta) {
        var consulta = new Consulta(dadosCadastroConsulta);
        consulta.getMedico().setNome("Jose Pinheiro");
        consulta.getPaciente().setNome("Jardson Silva");
        consulta.setId(3L);
        return consulta;
    }

}