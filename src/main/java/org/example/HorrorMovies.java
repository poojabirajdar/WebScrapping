package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HorrorMovies {

        public static void main(String[] args)  {

            try {
                Document doc = Jsoup.connect("https://www.imdb.com/list/ls002939221/").get();

                System.out.printf("\nWebsite Title: %s\n\n", doc.title());

                Elements movieList = doc.select(".lister-list > div");
                int totalMovies = movieList.size();
                System.out.println("Total number of movies: " + totalMovies);

                // Get the number of horror movies and their ratings with year
                int numHorrorMovies = 0;
                for (Element movie : movieList) {
                    String genre = movie.select(".genre").text();
                    if (genre.contains("Horror")) {
                        numHorrorMovies++;
                        String title = movie.select(".lister-item-header > a").text();
                        String year = movie.select(".lister-item-year").text().replaceAll("[^\\d]", "");
                        String[] rating = movie.select(".ipl-rating-star__rating").text().trim().split(" ");
                        System.out.println("Title: " + title + " (" + year + ")");
                        System.out.println("Rating: " + rating[0]);
                    }
                }
                System.out.println("Number of horror movies: " + numHorrorMovies);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    

