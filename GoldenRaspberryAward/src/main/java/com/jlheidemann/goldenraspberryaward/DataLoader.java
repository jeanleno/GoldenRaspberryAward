package com.jlheidemann.goldenraspberryaward;

import com.jlheidemann.goldenraspberryaward.service.MovieService;
import com.jlheidemann.goldenraspberryaward.service.ProducerService;
import com.jlheidemann.goldenraspberryaward.service.StudioService;
import com.jlheidemann.goldenraspberryaward.entity.Movie;
import com.jlheidemann.goldenraspberryaward.entity.Producer;
import com.jlheidemann.goldenraspberryaward.entity.Studio;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author jean.heidemann
 */
@Component
public class DataLoader implements ApplicationRunner {
    private StudioService studioService;
    private ProducerService producerService;
    private MovieService movieService;
    
    @Autowired 
    public DataLoader(StudioService studioService, ProducerService producerService, MovieService movieService) {
        this.studioService = studioService;
        this.producerService = producerService;
        this.movieService = movieService;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }
    
    private static final String DELIMITER = ";";
    
    public void init() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("movielist.csv");
        
        boolean isFirstLine = true;

        try (Scanner scanner = new Scanner(inputStream);) {
            while (scanner.hasNextLine()) {
                if (isFirstLine) {
                    isFirstLine = false;
                    scanner.nextLine();
                } else {
                    readLine(scanner.nextLine());
                }
            }
        }
    }

    private void readLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        
        List<Studio> studios = readLineStudios(values.get(2));
        List<Producer> producers = readLineProducer(values.get(3));
        
        String winner = "";
        if (values.size() > 4) {
            winner = values.get(4);
        }
        readLineMovie(values.get(0), values.get(1), winner, studios, producers);
    }
    
    private List<Studio> readLineStudios(String param) {
        List<Studio> studios = new ArrayList<>();
        if (param.contains(",")) {
            String[] arrStudios = param.split(",");
            for (int i = 0; i < arrStudios.length; i++) {
                String name = arrStudios[i].trim();
                studios.add(insertStudio(name));
            }
        } else {
            studios.add(insertStudio(param.trim()));
        }
        return studios;
    }
    
    private List<Producer> readLineProducer(String param) {
        List<Producer> producers = new ArrayList<>();
        param = param.replace(" and ", ",");
        if (param.contains(",")) {
            String[] arrProducers = param.split(",");
            for (int i = 0; i < arrProducers.length; i++) {
                String name = arrProducers[i].trim();
                producers.add(insertProducer(name));
            }
        } else {
            producers.add(insertProducer(param.trim()));
        }
        return producers;
    }
    
    private void readLineMovie(String year, String movieTitle, String winner, List<Studio> studios, List<Producer> producers) {
        boolean isWinner = winner != null && winner.trim().equalsIgnoreCase("yes");
        Movie movie = new Movie(movieTitle.trim(), Integer.parseInt(year.trim()), studios, producers, isWinner);
        this.movieService.saveMovie(movie);
    }

    private Studio insertStudio(String name) {
        Studio studio = this.studioService.findByName(name);
        if (studio == null) {
            studio = new Studio(name);
            this.studioService.saveStudio(studio);
        }
        return studio;
    }
    
    private Producer insertProducer(String name) {
        Producer producer = this.producerService.findByName(name);
        if (producer == null) {
            producer = new Producer(name);
            this.producerService.saveProducer(producer);
        }
        return producer;
    }

    
}
