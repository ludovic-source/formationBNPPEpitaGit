package com.ludovic;

import java.util.Arrays;

public class App 
{
    private Humanoid peterParker = new Humanoid("Peter Parker", 15);
    private Humanoid tonyStark = new Humanoid("Tony Stark", 48);
    private Humanoid bruceBanner = new Humanoid("Bruce Banner", 48);
    private Humanoid thorOfAsgard = new Humanoid("Thor of Asgard", 1500);
    private Humanoid natashaRomanoff = new Humanoid("Natasha Romanoff", 33);
    private Humanoid steveRogers = new Humanoid("Steve Rogers", 106);
    private Humanoid clintonBarton = new Humanoid("Clinton Barton", 35);
    private Humanoid thanosTheTitan = new Humanoid("Thanos the Titan", 750000);

    private SuperHero spiderman = new SuperHero(peterParker, "Spider-man", Arrays.asList("Web Shooting", "Agility"));
    private SuperHero ironman = new SuperHero(tonyStark, "Iron Man", Arrays.asList("High-tech armor", "Rich"));
    private SuperHero hulk = new SuperHero(bruceBanner, "Hulk", Arrays.asList("Strong"));
    private SuperHero thor = new SuperHero(thorOfAsgard, null, Arrays.asList("Mjöllnir", "Immortal"));
    private SuperHero blackWidow = new SuperHero(natashaRomanoff, "Black Widow", Arrays.asList("Close Combat"));
    private SuperHero captainAmerica = new SuperHero(steveRogers, "Captain America", Arrays.asList("Vibranium Shield", "Super-soldier"));
    private SuperHero hawkEye = new SuperHero(clintonBarton, "Hawk Eye", Arrays.asList("Bow and arrows"));
    private SuperHero thanos = new SuperHero(thanosTheTitan, null, Arrays.asList("Strong", "Infinity Gauntlet"));


    public static void main( String[] args )    {

    }
}
