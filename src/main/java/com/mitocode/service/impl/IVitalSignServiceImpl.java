package com.mitocode.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.VitalSign;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IVitalSignRepo;
import com.mitocode.service.iVitalSignService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IVitalSignServiceImpl  extends CRUDImpl<VitalSign, Integer> implements iVitalSignService{
	  private final IVitalSignRepo repo;

	    @Override
	    protected IGenericRepo<VitalSign, Integer> getRepo() {
	        return repo;
	    }

	    
	    @Override
	    public Page<VitalSign> listPage(Pageable pageable) {
	        return repo.findAll(pageable);
	    }
	   
}
