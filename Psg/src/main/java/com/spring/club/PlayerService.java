package com.spring.club;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
	@Autowired
	PlayerRepository playerRepository;
	
	public PlayerDetails createPlayer(PlayerDetails pd) {
		return playerRepository.save(pd);
	}
	public void removePlayerById(Integer id) {
		playerRepository.deleteById(id);
	}
	public PlayerDetails updatePlayer(Integer id, double salary) {
		PlayerDetails pd = playerRepository.findById(id).get();
		pd.setSalary(salary);
		PlayerDetails newpd = playerRepository.save(pd);
		return newpd;
	}
	public List<PlayerDetails> getAllPlayers() {
		return playerRepository.findAll();
	}
	public PlayerDetails getPlayer(Integer id) {
		return playerRepository.findById(id).get();
	}
}
