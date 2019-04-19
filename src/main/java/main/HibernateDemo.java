package main;

import entities.Customers;

import java.util.List;

import static dao.DbOperations.displayRecords;

public class HibernateDemo {
    public static void main(String[] args) {
        System.out.println("Hello Hibernate");

        List<Customers> data = displayRecords();

        for (Customers customer : data) {
            System.out.println(customer.toString());
        }

    }
}
