package com.tcdt.qlnvcategory.repository.khotang;

import com.tcdt.qlnvcategory.table.khotang.KtDtqgkv;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KtDtqgkvRepository extends CrudRepository<KtDtqgkv, Long> {
    Optional<KtDtqgkv> findByMaDtqgkv(String ma);

}
