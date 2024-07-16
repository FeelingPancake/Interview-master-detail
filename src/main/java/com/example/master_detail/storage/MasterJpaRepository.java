package com.example.master_detail.storage;

import com.example.master_detail.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterJpaRepository extends JpaRepository<Master, Long> {
}
