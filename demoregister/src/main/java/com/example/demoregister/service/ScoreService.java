package com.example.demoregister.service;

import com.example.demoregister.entity.Score;
import com.example.demoregister.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public Score saveScore(Score score){
        return scoreRepository.save(score);
    }
    public List<Score>listAllScore(Long id){
    return scoreRepository.listAllScore(id);
    }
    public List<Score> listScoreByUsername(String username){
        return scoreRepository.findAll();
    }
}
