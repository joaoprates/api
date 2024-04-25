
package prates.desenvolvimento.api.paciente;

import prates.desenvolvimento.api.endereco.Endereco;

public record DadosDetalhamentoPaciente(
    String nome,
    String email,
    String telefone,
    String cpf,
    Endereco endereco
) { 
    public DadosDetalhamentoPaciente(Paciente paciente) { 
        this(
            paciente.getNome(),
            paciente.getEmail(),
            paciente.getTelefone(),
            paciente.getCpf(),
            paciente.getEndereco()
        ); 
    }
}