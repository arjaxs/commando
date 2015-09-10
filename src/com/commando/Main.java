package com.commando;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here


        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            System.out.println("/n");
        }

         File inputFile = new File("C:\\Users\\Development\\Downloads\\JavaTest\\JavaTest\\urls.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                readData(line);
                System.out.println("#############");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    private static void readData(String url) {
        Movie movie=new Movie();

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = doc.title();

        Elements content = null;
        try {
            content = doc.getElementsByClass("star-box-giga-star");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Element link : content) {
            String linkHref = link.attr("href");
            String linkText = link.text();

            System.out.println("/n" + link.text());
        }


        try {
            content = doc.getElementsByClass("itemprop");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Element link : content) {
            String linkHref = link.attr("itemprop");
            System.out.println(linkHref + "-" + link.text());
            break;
        }
        try {
            Elements contents = doc.getElementsByClass("nobr");

            for (Element c : contents) {
                Elements links = c.getElementsByTag("a");
                for (Element link : links) {

                    String linkHref = link.attr("href");
                    System.out.println(link.text());
                    break;
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
