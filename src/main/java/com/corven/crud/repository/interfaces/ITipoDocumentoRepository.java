package com.corven.crud.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.corven.crud.model.TipoDocumento;

@Repository
public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
    Optional<TipoDocumento> findByTipoDocumentoIgnoreCase(String tipoDocumento);
}
