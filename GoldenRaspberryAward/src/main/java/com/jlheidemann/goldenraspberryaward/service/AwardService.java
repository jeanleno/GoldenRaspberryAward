package com.jlheidemann.goldenraspberryaward.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
		List<ProducerWins> producerWins = findProducerWithMinInterval();
		
		if (producerWins != null) {
			
			for (ProducerWins win : producerWins) {
				minInterval = new AwardIntervalDto();
				minInterval.setProducer(win.getProducer().getName());
				
				minInterval.setPreviousWin(win.getPreviousWin());
				minInterval.setFollowingWin(win.getFollowingWin());
				minInterval.setInterval(win.getMinDiference());
				
				award.addMinValue(minInterval);
			}
			
		}
		
		AwardIntervalDto maxInterval = new AwardIntervalDto();
		producerWins = findProducerWithMaxInterval();
		
		if (producerWins != null) {
			for (ProducerWins win : producerWins) {
				maxInterval = new AwardIntervalDto();
				maxInterval.setProducer(win.getProducer().getName());
				
				maxInterval.setPreviousWin(win.getPreviousWin());
				maxInterval.setFollowingWin(win.getFollowingWin());
				maxInterval.setInterval(win.getMaxDiference());
				
				award.addMaxValue(maxInterval);
			}
		}
		
		return award;
	}
	
	private List<ProducerWins> findProducerWithMaxInterval() {
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
    	
    	List<ProducerWins> winners = producerWins.stream().filter(p -> p.getMaxDiference() > 0).collect(Collectors.groupingBy(ProducerWins::getMaxDiference, TreeMap::new, Collectors.toList())).lastEntry().getValue();
    	
    	return winners;
    }
    
	private List<ProducerWins> findProducerWithMinInterval() {
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
    	
    	List<ProducerWins> winners = producerWins.stream().filter(p -> p.getMinDiference() > 0).collect(Collectors.groupingBy(ProducerWins::getMinDiference, TreeMap::new, Collectors.toList())).firstEntry().getValue();
    	
    	return winners;
    }
}
