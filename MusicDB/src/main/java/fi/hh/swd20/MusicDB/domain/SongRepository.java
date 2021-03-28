package fi.hh.swd20.MusicDB.domain;

import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {
	
}
