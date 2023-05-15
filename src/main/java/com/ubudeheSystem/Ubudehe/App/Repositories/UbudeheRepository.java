package com.ubudeheSystem.Ubudehe.App.Repositories;

import com.ubudeheSystem.Ubudehe.App.Domain.Ubudehe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbudeheRepository extends JpaRepository<Ubudehe, Long> {
}
