package com.mitocode.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mitocode.model.VitalSign;

public interface iVitalSignService extends ICRUD<VitalSign, Integer>{
	 Page<VitalSign> listPage(Pageable pageable);
}
