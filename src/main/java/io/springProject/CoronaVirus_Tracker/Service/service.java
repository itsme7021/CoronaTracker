package io.springProject.CoronaVirus_Tracker.Service;


import io.springProject.CoronaVirus_Tracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

//gonna make request to the url to engage with the data
@Service
public class service {

    private String url_data= "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * * * * *")
    public void fetchVirusData() throws InterruptedException , IOException
    {
        /*1.Client object is made
        2. Client makes a request to the url and the url is converted to uri
        3. Then the response is stored that we get when client sends the request and handles the response as string
        4. Then we have to store the entries of each day in out application
        5. We create a class models.stats that will contain the stats of each day
        6. we gonna store those stats in a list
        7. With the help of a for loop we are gonna store them and to avoid concurrency we are gonna make an another List and later make it equal to the main list
        8.
         */

        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url_data)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats s = new LocationStats();
            s.setState(record.get("Province/State"));
            s.setCountry(record.get("Country/Region"));
            int today = Integer.valueOf(record.get(record.size() - 1));
            int yester = Integer.valueOf(record.get(record.size() - 2));
            s.setRecordstillnow(today);  //will add the last column that is the latest date
            s.setDiffFromLastDay(today-yester);
            //System.out.println(s);
            newStats.add(s);
        }
        this.allStats = newStats;

    }
}
