package com.example.demoregister.repository;

import com.example.demoregister.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScoreRepository extends JpaRepository<Score,Long> {
    @Query("SELECT score FROM Score score WHERE score.id=:id")
    List<Score> listAllScore(Long id);
    @Query("SELECT score FROM Score score WHERE score.username=:username")
    List<Score>  listScoreByUsername(@Param("username") String username);

}
