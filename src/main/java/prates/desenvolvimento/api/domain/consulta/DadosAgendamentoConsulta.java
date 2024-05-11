package prates.desenvolvimento.api.domain.consulta;


import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import prates.desenvolvimento.api.domain.medico.Especialidade;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateConverter data,

        Especialidade especialidade) {
}