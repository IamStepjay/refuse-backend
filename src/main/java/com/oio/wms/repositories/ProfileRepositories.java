package com.oio.wms.repositories;

import com.oio.wms.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepositories extends JpaRepository<Profile, Long> {
}
