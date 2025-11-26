/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.CustomerDAO;
import dao.BanhDAO;
import java.util.ArrayList;

/**
 *
 * @author huuduc
 */
public class TestConnect {
    public static void main(String[] args) {
        BanhDAO rd = new BanhDAO();
        ArrayList<Banh> arr = rd.getAllBanh();
//        System.out.println(1);
//        if(arr == null){
//            System.out.println(-1);
//        }
//        else{
//            for(Room x : arr){
//                System.out.println(x);
//            }
//        }
        String name = "Standard";
        String email = "@gmail.com";
        String sdt = "87345743";
        CustomerDAO cd = new CustomerDAO();
        
        
    }
}
