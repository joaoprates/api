package prates.desenvolvimento.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import prates.desenvolvimento.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}