package com.corven.crud.repository.interfaces;

import com.corven.crud.model.Genero;
import com.corven.crud.model.Pais;
import com.corven.crud.model.Persona;
import com.corven.crud.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Map;
import java.util.Optional;

public interface IPersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByTipoDocumentoAndDocumentoAndPaisAndGenero(TipoDocumento tipoDocumento, String documento,
            Pais pais, Genero genero);

    @Query(value = "SELECT " +
            "SUM(CASE WHEN genero_id = 2 THEN 1 ELSE 0 END) AS cantidad_mujeres, " +
            "SUM(CASE WHEN genero_id = 1 THEN 1 ELSE 0 END) AS cantidad_hombres, " +
            "ROUND((SUM(CASE WHEN pais_id = 1 THEN 1 ELSE 0 END) / CAST(COUNT(*) AS NUMERIC)) * 100, 2) AS porcentaje_argentinos "
            +
            "FROM persona", nativeQuery = true)
    Map<String, Object> obtenerCifrasTotalizadoras();

}
