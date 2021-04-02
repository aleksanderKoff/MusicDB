package fi.hh.swd20.MusicDB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd20.MusicDB.domain.Genre;
import fi.hh.swd20.MusicDB.domain.GenreRepository;
import fi.hh.swd20.MusicDB.domain.Playlist;
import fi.hh.swd20.MusicDB.domain.PlaylistRepository;
import fi.hh.swd20.MusicDB.domain.Song;
import fi.hh.swd20.MusicDB.domain.SongRepository;
import fi.hh.swd20.MusicDB.domain.User;
import fi.hh.swd20.MusicDB.domain.UserRepository;

@SpringBootApplication
public class MusicDbApplication {
	
	private static final Logger log = LoggerFactory.getLogger(MusicDbApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MusicDbApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(SongRepository songRepository, GenreRepository genreRepository, PlaylistRepository playlistRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("Adding some genres..");
			Genre genre1 = new Genre("Rock");
			genreRepository.save(genre1);
			Genre genre2 = new Genre("Pop");
			genreRepository.save(genre2);
			Genre genre3 = new Genre("LoFi");
			genreRepository.save(genre3);
			Genre genre4 = new Genre("Hardstyle");
			genreRepository.save(genre4);
			Genre genre5 = new Genre("House");
			genreRepository.save(genre5);
			Genre genre6 = new Genre("Electronic");
			genreRepository.save(genre6);
			Genre genre7 = new Genre("Alternative");
			genreRepository.save(genre7);
			
			log.info("Creating some playlists..");
			Playlist playlist1 = new Playlist("Vibes");
			Playlist playlist2 = new Playlist("Workout");
			
			//Set<Playlist> playlistSet1 = new HashSet<>();
			//Set<Playlist> playlistSet2 = new HashSet<>();
			//playlistSet1.add(playlist1);
			//playlistSet2.add(playlist2);
			
			log.info("Adding some songs..");
			//songRepository.save(new Song("The Way It Was", "The Killers", "Battle Born", "2012", "3:51", genre1, playlist1));
			//songRepository.save(new Song("Someone That Loves You", "HONNE", "Warm on a Cold Night", "2016", "4:05", genre7, playlist2));
			Song song1 = new Song("The Way It Was", "The Killers", "Battle Born", "2012", "3:51", genre1);
			Song song2 = new Song("Someone That Loves You", "HONNE", "Warm on a Cold Night", "2016", "4:05", genre7);
			song1.getPlaylists().add(playlist2);
			song2.getPlaylists().add(playlist1);
			songRepository.save(song1);
			songRepository.save(song2);
			
			User user1 = new User("user", "$2a$10$yxv.jNBXnXT75y.SAl.yMupPMpQxvHb.NwTSB6cxU9M.uDM1/G.He", "user@user.com", "USER");
			User user2 = new User("admin", "$2a$10$ZxdjKUuEM.CnMZ0Dp4AAieCmPC1Ac/9S0y39/O4XWHYCUNU3CzLWy", "admin@admin.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			playlistRepository.save(playlist1);
			playlistRepository.save(playlist2);
			
			log.info("Fetching all songs from database..");
			for(Song song : songRepository.findAll()) {
				log.info(song.toString());
			}
			log.info("Fetching all genres from database..");
			for(Genre genre : genreRepository.findAll()) {
				log.info(genre.toString());
			}
			// log.info("Fetching all playlists from database");
		};
	}

}
