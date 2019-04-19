package dao;


import entities.Customers;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;


public class DbOperations {

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    public final static Logger logger = Logger.getLogger(DbOperations.class);

    // This method is used to create the hibernate's SessionFactory Object
    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & passing Hibernate configuration file
        Configuration configObj = new Configuration();
        configObj.addAnnotatedClass(entities.Customers.class);
        configObj.configure("hibernate.cfg.xml");

        // Since Hibernate version 4.x, Serviceregistry
        ServiceRegistry serviceRegistryObj =
                new StandardServiceRegistryBuilder().
                        applySettings(configObj.getProperties()).
                        build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    public static List displayRecords() {
        List<Customers> customerList = new ArrayList<>();
        Transaction transaction = null;
        try {
            // Getting Session Object from Session Factory
            sessionObj = buildSessionFactory().openSession();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        try {
            // Getting transaction Object from Session Object
            transaction = sessionObj.beginTransaction();

            customerList = sessionObj.createQuery("from Customers ").list();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger("con").info("Exception: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
        return customerList;
    }
}