package com.example.myapplication.helpers;

import com.example.myapplication.models.TreasureHuntItemModel;
import com.example.myapplication.models.UserModel;
import com.here.sdk.core.GeoCoordinates;

import java.util.ArrayList;

public class DataStorageHelper {
    private static DataStorageHelper instance;
    private UserModel currentUser;

    public static DataStorageHelper getInstance()
    {
        if(instance == null)
        {
            instance = new DataStorageHelper();
        }
        return instance;
    }

    public void setCurrentUser(UserModel user){
        currentUser = user;
    }

    public UserModel getCurrentUser(){
        return currentUser;
    }

    public ArrayList<TreasureHuntItemModel> cultureHuntList(){
        ArrayList<TreasureHuntItemModel> list = new ArrayList<>();

        return list;
    }

    public ArrayList<TreasureHuntItemModel> outdoorHuntList(){
        ArrayList<TreasureHuntItemModel> list = new ArrayList<>();
        list.add(new TreasureHuntItemModel(100, "Timisoara is a city you can fall in love with. Here are some unique things you should know about it.", "First stop: Central Park", new GeoCoordinates(45.751611, 21.2200748)));
        list.add(new TreasureHuntItemModel(101, "Congratulations! Alley of Personalities is an open-air museum, where 24 bronze busts are exhibited: from Carol Robert de Anjou, who established his capital in Timisoara, to army commanders, mayors who contributed to the development of the city, as well as doctors , engineers, writers, musicians and visual artists.", "2.\tTimisoara was the first European city, and the second after New York globally, to introduce electric street lighting. It happened in 1884. Next stop: Roses Park", new GeoCoordinates(45.7503068, 21.2302278)));
        list.add(new TreasureHuntItemModel(102, "Congratulations! In 1944 the park was destroyed by bombing. After the Second World War, the park was rebuilt, with various outdoor shows.", "3.\tTimisoara was also the first city in Europe to have a tram. It was inaugurated on July 8, 1869, was made of wood in Vienna and operated horse-drawn. 15 people could move easily with his help. Next stop: Ion Creangă Children’s Park", new GeoCoordinates(45.7503068, 21.2302278)));
        list.add(new TreasureHuntItemModel(103, "Congratulations! According to some historical data, the development of this park began about 160 years ago. In 1987, a series of radical changes took place that lead to the fulfillment of the functions of recreation and play of children.", "4.\tTimisoara is the largest city in the west of the country, with a population of about 400,000 inhabitants. Next stop: Parcul Poporului (Regina Maria)", new GeoCoordinates(45.7567, 21.2424413)));
        list.add(new TreasureHuntItemModel(104, "Congratulations! Is the oldest park of Timișoara, in 1918 it took the name of the “Regina Maria” park, in honor of the queen, who had an important role in the accomplishment of the Union from 1918.", "5.\tThe Bega River was the first navigable canal in Romania, which led to the increase of trade in those times. Next stop: Botanic Park", new GeoCoordinates(45.7601963, 21.2231128)));
        list.add(new TreasureHuntItemModel(105, "Congratulations! Founded between 1986 and 1990 by the architect Silvia Grumeza, it is located in the immediate vicinity of the former fortress and currently includes 218 plant species.", "6.It was the first provincial town to win a national rugby title in 1972. Next stop: Parcul Civic", new GeoCoordinates(45.7540275, 21.2293708)));
        list.add(new TreasureHuntItemModel(106, "Congratulations! Tot în Parcul Civic se află unul dintre simbolurile Timișoarei - Ceasul floral, un ceas al cărui design se schimbă de două ori pe an.", "7.Timisoara is also called `the city of roses`. Two aristocratic families of florists, Mühle and Niemetz, ennobled the city by cultivating over 1400 varieties of roses exported throughout the Austro-Hungarian Empire and to royal houses throughout Europe - hence the name `city of roses`. Next stop: Parcul Justiției", new GeoCoordinates(45.7501741, 21.2252224)));
        list.add(new TreasureHuntItemModel(107, "Congratulations! In the middle of the park is the monument of deportees in Bărăgan between 1951–1956.", "Congratulations! You have successfully completed the Outdoor Module of the Treasure Hunt", new GeoCoordinates(45.7528703, 21.2333356)));
        return list;
    }

    public ArrayList<TreasureHuntItemModel> entertainmentHuntList(){
        ArrayList<TreasureHuntItemModel> list = new ArrayList<>();

        return list;
    }
}
