package med.voll.api.medico;

import med.voll.api.endereco.DadosEndereco;

public record DadosDetalhamentoMedico(
    String nome,
    String email,
    String telefone,
    String crm,
    Especialidade especialidade,
    DadosEndereco endereco
){
    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), new DadosEndereco(medico.getEndereco()));
    }
}
