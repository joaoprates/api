package prates.desenvolvimento.api.domain.medico;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
        SELECT m FROM Medico m 
        WHERE 
        m.especialidade = :especialidade AND m.ativo = TRUE
        AND
                m.id NOT IN(
                        SELECT c.medico.id FROM Consulta c
                        WHERE
                        c.data = :data
                )
        ORDER BY RAND()
        LIMIT 1
        """)
    Medico findByEspecialidade(Especialidade especialidade, @NotNull @Future LocalDateTime localDateTime);
}