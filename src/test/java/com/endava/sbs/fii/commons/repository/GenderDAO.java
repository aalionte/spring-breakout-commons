package com.endava.sbs.fii.commons.repository;

import org.springframework.stereotype.Repository;

import com.endava.sbs.fii.commons.domain.Gender;

@Repository
public class GenderDAO  extends AbstractDAO<Gender> {

	protected GenderDAO() {
		super(Gender.class);
	}
}
