package fi.hh.swd20.MusicDB.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playlistId;
	private String name;
	
	@JsonIgnoreProperties ("playlist")
	@ManyToMany (cascade = {
				CascadeType.MERGE,
				CascadeType.PERSIST
				},mappedBy = "playlists")
	private Set<Song> songs = new HashSet<>();
	
	public Playlist() {
		super();
	}
	
	public Playlist(String name) {
		super();
		this.name = name;
	}


	public Long getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + playlistId + ", name=" + name + "]";
	}
	
	
}
