package fi.hh.swd20.MusicDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd20.MusicDB.domain.Song;
import fi.hh.swd20.MusicDB.domain.SongRepository;

@SpringBootApplication
public class MusicDbApplication {
	
	private static final Logger log = LoggerFactory.getLogger(MusicDbApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MusicDbApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(SongRepository songRepository) {
		return (args) -> {
			
			log.info("Adding some songs..");
			songRepository.save(new Song("The Way It Was", "The Killers", "Battle Born", "2012", "3:51"));
			songRepository.save(new Song("Someone That Loves You", "HONNE", "Warm on a Cold Night", "2016", "4:05"));
			
			log.info("Fetching all songs from database..");
			for(Song song : songRepository.findAll()) {
				log.info(song.toString());
			}
		};
	}

}
