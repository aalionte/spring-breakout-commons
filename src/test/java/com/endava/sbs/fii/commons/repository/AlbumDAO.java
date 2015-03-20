package com.endava.sbs.fii.commons.repository;

import org.springframework.stereotype.Repository;

import com.endava.sbs.fii.commons.domain.Album;

@Repository
public class AlbumDAO  extends AbstractDAO<Album> {

	protected AlbumDAO() {
		super(Album.class);
	}
}
