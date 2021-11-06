/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

/**
 *
 * @author nha
 */
public class Converter {
    private int sec,min,hour;
    public void SecToTime(int time){
        hour = time/3600;
        time = time % 3600;
        min = time/60;
        time = time % 60;
        sec = time;
    }
    public int GetSec(){
        return sec;
    }
    public int GetMin(){
        return min;
    }
    public int GetHour(){
        return hour;
    }
}
