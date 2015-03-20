package com.endava.sbs.fii.repository.commons.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.endava.sbs.fii.commons.builders.ArtefactBuilder;
import com.endava.sbs.fii.commons.domain.Album;
import com.endava.sbs.fii.commons.domain.Artist;
import com.endava.sbs.fii.commons.repository.AlbumDAO;
import com.endava.sbs.fii.commons.repository.ArtistDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:hs.xml", "classpath:root-context.xml" })
@Transactional
public class AlbumDAOTest {

	@Autowired
	private AlbumDAO albumDAO;
	@Autowired
	private ArtistDAO artistDAO;

	private Album album;

	@Before
	public void setUp() {

	}

	@Test
	public void testSaveAlbum_Success() {
		album = ArtefactBuilder.anAlbum();
		albumDAO.save(album);
		assertTrue(album.getId() != null);
	}

	@Test
	public void testUpdateAlbum_Success() {
		album = ArtefactBuilder.anAlbum();
		albumDAO.save(album);
		album.setTitle("test 2");
		albumDAO.update(album);
		assertTrue(album.getId() != null);
		assertTrue(album.getTitle().equals("test 2"));
	}

	@Test
	public void testRemoveAlbum_Success() {
		album = ArtefactBuilder.anAlbum();
		albumDAO.save(album);
		albumDAO.remove(album);
		Album removedAlbum = albumDAO.findById(album.getId());
		assertTrue(removedAlbum == null);
	}

	@Test
	public void testListAllAlbums_Success() {
		Artist artist = ArtefactBuilder.anArtist();
		artistDAO.save(artist);
		assertTrue(artist.getId() != null);

		album = ArtefactBuilder.anAlbum();
		albumDAO.save(album);
		assertTrue(album.getId() != null);

		album.setArtist(artist);
		albumDAO.update(album);
		List<Album> albums = albumDAO.findAll();

		assertTrue(albums != null);
		assertTrue(albums.size() == 1);
		assertTrue(albums.get(0).getArtist().getId().equals(artist.getId()));
	}

}
