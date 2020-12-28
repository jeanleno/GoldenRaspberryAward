package com.jlheidemann.goldenraspberryaward.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlheidemann.goldenraspberryaward.entity.Movie;
import com.jlheidemann.goldenraspberryaward.entity.Producer;
import com.jlheidemann.goldenraspberryaward.repository.MovieRepository;
import com.jlheidemann.goldenraspberryaward.repository.ProducerRepository;
import com.jlheidemann.goldenraspberryaward.service.dto.AwardIntervalDto;
import com.jlheidemann.goldenraspberryaward.service.dto.AwardsDto;
import com.jlheidemann.goldenraspberryaward.service.util.ProducerWins;

/**
*
* @author jean.heidemann
*/
@Service
public class AwardService {
	@Autowired
    private ProducerRepository producerRepository;
    
    @Autowired
    private MovieRepository movieRepository;
	
	public AwardsDto findAwards() {
		AwardsDto award = new AwardsDto();
		
		AwardIntervalDto minInterval = new AwardIntervalDto();
		ProducerWins producerWins = findProducerWithMinInterval();
		
		if (producerWins != null) {
			minInterval = new AwardIntervalDto();
			minInterval.setProducer(producerWins.getProducer().getName());
			
			minInterval.setPreviousWin(producerWins.getPreviousWin());
			minInterval.setFollowingWin(producerWins.getFollowingWin());
			minInterval.setInterval(producerWins.getMinDiference());
		}
		
		AwardIntervalDto maxInterval = new AwardIntervalDto();
		producerWins = findProducerWithMaxInterval();
		
		if (producerWins != null) {
			maxInterval = new AwardIntervalDto();
			maxInterval.setProducer(producerWins.getProducer().getName());
			
			maxInterval.setPreviousWin(producerWins.getPreviousWin());
			maxInterval.setFollowingWin(producerWins.getFollowingWin());
			maxInterval.setInterval(producerWins.getMaxDiference());
		}
		
		award.setMin(minInterval);
		award.setMax(maxInterval);
		
		return award;
	}
	
	private ProducerWins findProducerWithMaxInterval() {
    	List<Movie> winnerMovies = this.movieRepository.findByWinnerTrue();
    	List<Producer> producers = this.producerRepository.findAll();
    	
    	List<ProducerWins> producerWins = new ArrayList<ProducerWins>();
    	for(Producer producer : producers) {
    		producerWins.add(new ProducerWins(producer));
    	}
    	
    	for (Movie movie : winnerMovies) {
			if (movie.getProducers() != null) {
				for (Producer movieProducer : movie.getProducers()) {
					producerWins.stream().filter(p -> p.getProducer().getId() == movieProducer.getId()).findFirst().get().addWinYear(movie.getYear());
				}
				
			}
		}
    	
    	ProducerWins winner = producerWins.stream().filter(p -> p.getMaxDiference() > 0).max(Comparator.comparing(ProducerWins::getMaxDiference)).get();
    	
    	return winner;
    }
    
	private ProducerWins findProducerWithMinInterval() {
    	List<Movie> winnerMovies = this.movieRepository.findByWinnerTrue();
    	List<Producer> producers = this.producerRepository.findAll();
    	
    	List<ProducerWins> producerWins = new ArrayList<ProducerWins>();
    	for(Producer producer : producers) {
    		producerWins.add(new ProducerWins(producer));
    	}
    	
    	for (Movie movie : winnerMovies) {
			if (movie.getProducers() != null) {
				for (Producer movieProducer : movie.getProducers()) {
					producerWins.stream().filter(p -> p.getProducer().getId() == movieProducer.getId()).findFirst().get().addWinYear(movie.getYear());
				}
				
			}
		}
    	
    	ProducerWins winner = producerWins.stream().filter(p -> p.getMinDiference() > 0).min(Comparator.comparing(ProducerWins::getMinDiference)).get();
    	
    	return winner;
    }
}
