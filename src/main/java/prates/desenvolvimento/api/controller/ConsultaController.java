package prates.desenvolvimento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import prates.desenvolvimento.api.domain.ValidacaoException;
import prates.desenvolvimento.api.domain.consulta.AgendaDeConsultas;
import prates.desenvolvimento.api.domain.consulta.DadosAgendamentoConsulta;
import prates.desenvolvimento.api.domain.consulta.DadosCancelamentoConsulta;
import prates.desenvolvimento.api.domain.consulta.DadosDetalhamentoConsulta;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) throws ValidacaoException {
        agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) throws ValidacaoException {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}