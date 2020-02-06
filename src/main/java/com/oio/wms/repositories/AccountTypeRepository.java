package com.oio.wms.repositories;

import com.oio.wms.domain.AccoutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountTypeRepository extends JpaRepository<AccoutType, Long> {
}
