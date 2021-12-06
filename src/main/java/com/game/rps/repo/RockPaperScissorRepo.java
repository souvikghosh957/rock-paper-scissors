package com.game.rps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.rps.model.RockPaperScissor;

/**
 * Repository class for table "rock_paper_scissor" in Database: game_db.
 * 
 */
public interface RockPaperScissorRepo extends JpaRepository<RockPaperScissor, Integer>{

}
