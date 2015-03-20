package com.endava.sbs.fii.commons.repository;

import org.springframework.stereotype.Repository;

import com.endava.sbs.fii.commons.domain.Artist;

@Repository
public class ArtistDAO  extends AbstractDAO<Artist> {

	protected ArtistDAO() {
		super(Artist.class);
	}
}
