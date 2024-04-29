package prates.desenvolvimento.api.domain.paciente;

import jakarta.validation.Valid;
import prates.desenvolvimento.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco
) {
}