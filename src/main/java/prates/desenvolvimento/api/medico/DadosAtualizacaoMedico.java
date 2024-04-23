package prates.desenvolvimento.api.medico;

import jakarta.validation.constraints.NotNull;
import prates.desenvolvimento.api.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}