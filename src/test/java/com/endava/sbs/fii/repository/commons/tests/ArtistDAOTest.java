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
import com.endava.sbs.fii.commons.domain.Artist;
import com.endava.sbs.fii.commons.domain.Gender;
import com.endava.sbs.fii.commons.repository.ArtistDAO;
import com.endava.sbs.fii.commons.repository.GenderDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:hs.xml",
		"classpath:root-context.xml"})
@Transactional
public class ArtistDAOTest {
	
	@Autowired
	private ArtistDAO artistDAO;
	@Autowired
	private GenderDAO genderDAO;
	
	
	private Artist artist;

	@Before
	public void setUp() {
		
	}

	@Test
	public void testSaveArtist_Success() {
		artist = ArtefactBuilder.anArtist();
		artistDAO.save(artist);
		assertTrue(artist.getId() != null);
	}
	
	@Test
	public void testUpdateArtist_Success() {
		artist = ArtefactBuilder.anArtist();
		artistDAO.save(artist);
		artist.setName("test 2");
		artistDAO.update(artist);
		assertTrue(artist.getId() != null);
		assertTrue(artist.getName().equals("test 2"));
	}
	
	@Test
	public void testRemoveArtist_Success() {
		artist = ArtefactBuilder.anArtist();
		artistDAO.save(artist);
		artistDAO.remove(artist);
		Artist removedArtist = artistDAO.findById(artist.getId());
		assertTrue(removedArtist == null);
	}
	
	@Test
	public void testListAllArtists_Success() {
		artist = ArtefactBuilder.anArtist();
		artistDAO.save(artist);
		List<Artist> artists =artistDAO.findAll();
		assertTrue(artists != null);
		assertTrue(artists.size() == 1);
	}
	
	@Test
	public void testSaveArtistWithGenders_Success() {
		artist = ArtefactBuilder.anArtistWithoutGenders();
		artistDAO.save(artist);
		
		Gender rockGender = ArtefactBuilder.aGender();
		rockGender.setName("Rock");
		genderDAO.save(rockGender);
		assertTrue(rockGender.getId() != null);
		
		Gender metalGender = ArtefactBuilder.aGender();
		metalGender.setName("Metal");
		genderDAO.save(metalGender);
		assertTrue(metalGender.getId() != null);
		
		artist.getGenders().add(rockGender);
		artist.getGenders().add(metalGender);
		artistDAO.update(artist);
		
		List<Artist> artists =artistDAO.findAll();
		assertTrue(artists != null);
		assertTrue(artists.size() == 1);
		assertTrue(artists.get(0).getGenders().size() == 2);
		
		for (Gender gender: artists.get(0).getGenders()){
			assertTrue(gender.getName().equals("Rock") || gender.getName().equals("Metal"));
		}
	}
	
	
}
