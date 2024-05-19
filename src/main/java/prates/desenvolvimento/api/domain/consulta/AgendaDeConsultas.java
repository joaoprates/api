package prates.desenvolvimento.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prates.desenvolvimento.api.domain.ValidacaoException;
import prates.desenvolvimento.api.domain.medico.MedicoRepository;
import prates.desenvolvimento.api.domain.paciente.PacienteRepository;
import prates.desenvolvimento.api.domain.medico.Medico;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) throws ValidacaoException {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Paciente não encontrado");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Médico não encontrado");
        }

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, null, dados.data()); 
        consultaRepository.save(consulta);
    }

    /**
     * @param dados
     * @return
     * @throws ValidacaoException
     */
    public Medico escolherMedico(DadosAgendamentoConsulta dados) throws ValidacaoException {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.findByEspecialidade(dados.especialidade(), dados.data());
    }
    
    public void cancelar(DadosCancelamentoConsulta dados) throws ValidacaoException {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }
    
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}