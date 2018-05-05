package com.example.christian.acnoride;

import android.preference.Preference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Christian on 5/5/2018.
 */


    public class Player {
        private int id;
        private String username;
        private String password;
        private String score;
        private String rank;


        public Player()
        {
            //constructor blank user with id -1
            this.setId(-1);
            this.setUsername("");
            this.setPassword("");
            this.setScore("");
            this.setRank("");
        }


        public boolean equals(Player player2)
        {
            if(this.username.equals(player2.getUsername())
                    && this.password.equals(player2.getPassword())
                    )/*&& this.prank.equals(player2.getPrank())
                    && this.rankings.equals(player2.getRankings())
                    && this.first_name.equals(player2.getFirst_name())
                    && this.last_name.equals(player2.getLast_name())
                    && this.email.equals(player2.getEmail())
                    && this.phone.equals(player2.getPhone())
                    && this.address.equals(player2.getAddress())
                    && this.city.equals(player2.getCity())
                    && this.state.equals(player2.getState())
                    && this.zip.equals(player2.getZip()))*/
                return true;
            else
                return false;
        }


        public String toString()
        {
            return  "User ID:\t" + this.id + "\n" +
                    "Username:\t" + this.username + "\n" +
                    "Password:\t" + this.password + "\n"  +
                    "Score:\t" + this.score + "\n" +
                    "Rank:\t" + this.rank + "\n";
        }

        //can change to scoreComparator
//        public static Comparator prankComparator = new Comparator<User>() {
//            @Override
//            public int compare(User user1, User user2) {
//                if (Integer.getInteger(user1.getPrank()) > Integer.getInteger(user2.getPrank()))
//                    return 1;
//                if (Integer.getInteger(user1.getPrank()) < Integer.getInteger(user2.getPrank()))
//                    return -1;
//                return 0;
//            }
//        };


        //+++++++++++++++++GETTERS+++++++++++++++++


        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getScore() {
            return score;
        }

        public String getRank() {
            return rank;
        }




        //+++++++++++++++++SETTERS+++++++++++++++++

        public void setId(int id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

    }

