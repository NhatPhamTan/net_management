/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

import java.awt.GridLayout;
import javax.swing.*;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
     Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/"; 
        String dbName = "doan"; 
        String strUnicode = "?useUnicode=true&characterEncoding=utf8";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root"; 
        String password = "";
    public void CheckConnection(){
        try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                System.out.println("Trying to connect");
                conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            } catch (Exception e) {
                System.out.println("Unable to make connection with DB");
                JFrame jf = new JFrame();
                    jf.setLayout(null);
                    jf.setSize(0,0);
                    jf.setLocationRelativeTo(null);
                    jf.setVisible(true);
                    JOptionPane.showMessageDialog(jf, "Lỗi kết nối");
                    System.exit(0);        
                e.printStackTrace();
            }
    }
    public void ExecuteSQL(String sq){
         try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);   
            Statement s = conn.createStatement();
            s.execute(sq);
            s.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }
                }// Ket thuc khoi finally
    }
    //PC{
    public void UpdatePCTime(String pc_id){                      
        String sq = "UPDATE PC SET USING_TIME = USING_TIME + 1 WHERE PC_ID =" +pc_id;
        ExecuteSQL(sq);
    }
    public void ResetPCTime(String pc_id){                      
        String sq = "UPDATE PC SET using_time = 0 WHERE PC_ID =" +pc_id;
        ExecuteSQL(sq);
    }
    public int GetPCTime(String pc_id){
        String sq ="SELECT USING_TIME FROM PC WHERE PC_ID=" +pc_id;
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sq);
            int a = -1;
            while(rs.next()){
                a = rs.getInt(1);
            }
        //    System.out.println(a);
            s.close();
            conn.close();
            return a;
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }
                        
                }
        return 0;
    }
    public void SetPcState(String pc_id){
        String sq = "UPDATE PC SET pc_state = 1 WHERE PC_ID =" +pc_id;
        ExecuteSQL(sq);
    }
    public void ResetPcState(String pc_id){
        String sq = "UPDATE PC SET pc_state = 0 WHERE PC_ID =" +pc_id;
        ExecuteSQL(sq);
    }
    public boolean GetPCState(String pc_id){
         String sq ="SELECT pc_state FROM PC WHERE PC_ID=" +pc_id;
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sq);
            boolean a = false;
            while(rs.next()){
                a = rs.getBoolean(1);
            }
       //     System.out.println(a);
            s.close();
            conn.close();
            return a;
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
        return false;
    }
    public String GetPCUid(String pc_id){
         String sq ="SELECT uid FROM PC WHERE PC_ID=" +pc_id;
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sq);
            String a = "" ;
            while(rs.next()){
                a = rs.getString(1);
            }
            s.close();
            rs.close();
            conn.close();
            return a;
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
        return null;
    }
    public void UpdatePCUid(String pc_id,String uid){
        
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "UPDATE PC SET uid =? WHERE PC_ID =?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            pre.setString(2, pc_id);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
    }
    public void ResetPCUid(String pc_id){
        String sq = "UPDATE PC SET uid = NULL WHERE PC_ID ="+pc_id;
        ExecuteSQL(sq);
    }
    public List<Integer> GetAllPCid(){
        String sq ="SELECT pc_id FROM pc";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sq);
            List<Integer> list = new ArrayList<Integer>();
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            s.close();
            rs.close();
            conn.close();
            return list;
        }catch(SQLException se)
        {
            se.printStackTrace();
        }catch(Exception e){
            // Xu ly cac loi cho Class.forName
            e.printStackTrace();
        }finally{
            // Khoi finally duoc su dung de dong cac resource
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return null;
    }
    public boolean AddPC(String pcid){
        if(GetPCTime(pcid) != -1){
            return false;
        }
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "INSERT INTO PC VALUES(?,null,0,0)";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, pcid);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
        return true;
    }
    public boolean DelPC(String pcid){
        if(GetPCTime(pcid) == -1){
            return false;
        }
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "DELETE FROM PC WHERE PC_ID =?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, pcid);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
        {
            se.printStackTrace();
        }catch(Exception e){
            // Xu ly cac loi cho Class.forName
            e.printStackTrace();
        }finally{
            // Khoi finally duoc su dung de dong cac resource
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return true;
    }
    //}PC
    //AC
    public boolean CreateAcc(String uid,String u_pass,int time){
        if(GetUserTime(uid) != - 1){
            return false;
        }
        try {                      
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "INSERT INTO ACCOUNT(UID,U_PWD,TIME_REMAINING,U_STATE) VALUES(?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            pre.setString(2, u_pass);
            pre.setInt(3, time);
            pre.setBoolean(4, false);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }
                }// Ket thuc khoi finally
        return true;
    }
    public int GetUserTime(String uid){        
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="SELECT TIME_REMAINING FROM ACCOUNT WHERE UID=?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            ResultSet rs = pre.executeQuery();
            int a = -1;
            while(rs.next()){
                a = rs.getInt(1);
            }
            pre.close();
            rs.close();
            conn.close();
            return a;
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                                    
                            }                       
                }
        return -1;
    }
    public boolean CheckUPass(String uid,String pw){
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="SELECT U_PWD FROM ACCOUNT WHERE UID=?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            ResultSet rs = pre.executeQuery();
            String a = "";
            while(rs.next()){
                a = rs.getString(1);
            }
            pre.close();
            rs.close();
            conn.close();           
            return pw.equals(a);
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
        return false;
    }
    public boolean GetUState(String uid){
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="SELECT u_state FROM ACCOUNT WHERE UID=?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            ResultSet rs = pre.executeQuery();
            boolean a = false;
            while(rs.next()){
                a = rs.getBoolean(1);
            }
            pre.close();
            rs.close();
            conn.close();
            return a;
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
      return false;
    }
    public boolean UpdatePassword(String uid,String pw){
        if(GetUserTime(uid) == -1){
            return false;
        }
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="UPDATE ACCOUNT SET U_PWD =? WHERE UID=?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, pw);
            pre.setString(2, uid);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
        return true;
    }
    public boolean UpdateUTime(String uid){
        if(GetUserTime(uid) == -1){
            return false;
        }
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="UPDATE ACCOUNT SET TIME_REMAINING = TIME_REMAINING - 1 WHERE UID=?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
        return true;
    }
    public void SetUState(String uid){
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="UPDATE ACCOUNT SET U_STATE = 1 WHERE UID=?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
    }
    public void ResetUState(String uid){
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="UPDATE ACCOUNT SET U_STATE = 0 WHERE UID=?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
    }
    public boolean AddTimeU(String uid,int time){
        if(GetUserTime(uid) == -1){
            return false;
        }
         try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="UPDATE ACCOUNT SET TIME_REMAINING = TIME_REMAINING + ? WHERE UID=?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setInt(1, time);
            pre.setString(2, uid);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
         return true;
    }
    public List<String> GetAllUid(){
        String sq ="SELECT uid FROM account";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sq);
            List<String> list = new ArrayList<String>();
            while(rs.next()){
                list.add(rs.getString(1));
            }
            s.close();
            rs.close();
            conn.close();
            return list;
        }catch(SQLException se)
        {
            se.printStackTrace();
        }catch(Exception e){
            // Xu ly cac loi cho Class.forName
            e.printStackTrace();
        }finally{
            // Khoi finally duoc su dung de dong cac resource
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return null;
    }
    public boolean SetStartDate(String uid,String date){
        try {                      
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "UPDATE ACCOUNT SET start_date = ? WHERE UID = ?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, date);
            pre.setString(2, uid);                  
            pre.executeUpdate();
            pre.close();
            conn.close();
            return true;
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }
                }// Ket thuc khoi finally
        return false;
    }
    public boolean SetEndDate(String uid,String date){
        try {                      
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "UPDATE ACCOUNT SET end_date = ? WHERE UID = ?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, date);
            pre.setString(2, uid);                  
            pre.executeUpdate();
            pre.close();
            conn.close();
            return true;
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }
                }// Ket thuc khoi finally
        return false;
    }
    public String GetStartDate(String uid){
        try {            
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="SELECT start_date FROM ACCOUNT WHERE UID = ?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            ResultSet rs = pre.executeQuery();
            String date = "";
            while(rs.next()){
                date = rs.getString(1);
            }
            pre.close();
            rs.close();
            conn.close();
            return date;
        }catch(SQLException se)
        {
            se.printStackTrace();
        }catch(Exception e){
            // Xu ly cac loi cho Class.forName
            e.printStackTrace();
        }finally{
            // Khoi finally duoc su dung de dong cac resource
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return null;
    }
    public String GetEndDate(String uid){
        try {    
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq ="SELECT end_date FROM ACCOUNT WHERE UID = ?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, uid);
            ResultSet rs = pre.executeQuery();
            String date = "";
            while(rs.next()){
                date = rs.getString(1);
            }
            pre.close();
            rs.close();
            conn.close();
            return date;
        }catch(SQLException se)
        {
            se.printStackTrace();
        }catch(Exception e){
            // Xu ly cac loi cho Class.forName
            e.printStackTrace();
        }finally{
            // Khoi finally duoc su dung de dong cac resource
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return null;
    }
   //}AC
    
    //Stastical{
    public void AddIncome(float income,String date){
     try {                      
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "INSERT INTO STASTICAL(DATE,INCOME) VALUES(?,?)";
            PreparedStatement pre = conn.prepareStatement(sq);
         //   String date =  java.time.LocalDate.now().toString();
            pre.setString(1, date);
            pre.setDouble(2, income);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }
                }// Ket thuc khoi finally
    }
    public void UpdateIncome(float money,String date){
        try {                      
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "UPDATE STASTICAL SET INCOME = INCOME + ? WHERE DATE = ?";
            PreparedStatement pre = conn.prepareStatement(sq);
          //  String date =  java.time.LocalDate.now().toString();
            pre.setDouble(1, money);
            pre.setString(2,date);
            pre.executeUpdate();
            pre.close();
            conn.close();
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }
                }// Ket thuc khoi finally
    }
    public boolean CheckDateExist(String date){
        try {                      
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "SELECT DATE FROM STASTICAL";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sq);
            boolean b = false;
            while(rs.next()){
                if (date.equals(rs.getString(1))){
                    b = true;
                    break;
                }
            }
            s.close();
            rs.close();
            conn.close();
            return b;
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }
                }
        return false;
    }
    public void TinhTien(float money,String date){
        if( CheckDateExist(date)){
            UpdateIncome(money,date);
        }else{
            AddIncome(money,date);
        }
    }
    public List<String> GetAllDate(){
        String sq ="SELECT date FROM stastical";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sq);
            List<String> list = new ArrayList<String>();
            while(rs.next()){
                list.add(rs.getString(1));
            }
            s.close();
            rs.close();
            conn.close();
            return list;
        }catch(SQLException se)
        {
            se.printStackTrace();
        }catch(Exception e){
            // Xu ly cac loi cho Class.forName
            e.printStackTrace();
        }finally{
            // Khoi finally duoc su dung de dong cac resource
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return null;
    }
    public double GetIncome(String date){
        try {           
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName+strUnicode,userName,password);
            String sq = "SELECT INCOME FROM STASTICAL WHERE DATE = ?";
            PreparedStatement pre = conn.prepareStatement(sq);
            pre.setString(1, date);
            ResultSet rs = pre.executeQuery();
            double money = -1;
            while(rs.next()){
                money= rs.getDouble(1);
            }
            rs.close();
            pre.close();
            conn.close();
            return money;
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                        try{
                                if(conn!=null)
                                    conn.close();
                            }catch(SQLException se){
                                    se.printStackTrace();
                            }                       
                }
        return 0;
    }

    //}Stastical
}
