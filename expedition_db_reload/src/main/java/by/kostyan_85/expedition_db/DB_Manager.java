package by.kostyan_85.expedition_db;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;


import static java.nio.charset.StandardCharsets.US_ASCII;


/**
 * Created by Zver on 10.03.2020.
 */
public class DB_Manager<T> {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        createDB();
        createTableDB(pathCarriers);
        createTableDB(pathCustomers);

    }
    private static DB_Manager instance;

    public static DB_Manager getInstance() {
        if (instance == null) {
            instance = new DB_Manager();
        }
        return instance;
    }
    private  Connection connection = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:expedition.db");
        System.out.println("Установлено соединение с БД.");
        return connection;
    }


    static String pathCarriers = "C:\\Users\\Zver\\Desktop\\expedition_db_reload\\src\\main\\java\\QuerySql\\carriersScript.sql";
    static String pathCustomers = "C:\\Users\\Zver\\Desktop\\expedition_db_reload\\src\\main\\java\\QuerySql\\customersScript.sql";


    static void createDB() {
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:expedition.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
   static void alterTable()throws SQLException, ClassNotFoundException, IOException, IllegalAccessException, InstantiationException{
//        String query = "ALTER TABLE carriers  UNIQUE  contacts;";
//        String query = "CREATE UNIQUE INDEX   carriers (contacts)";
        Connection con;
        Statement stmt;
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:expedition.db");
        System.out.println("Opened database successfully");
        stmt = con.createStatement();
//        stmt.execute(readFile(path, US_ASCII));
//        stmt.execute(query);
        stmt.close();
        con.close();
    }

    static void createTableDB(String path) throws SQLException, ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        Connection con;
        Statement stmt;
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:expedition.db");
        System.out.println("Opened database successfully");
        stmt = con.createStatement();
        stmt.execute(readFile(path, US_ASCII));
        stmt.close();
        con.close();
    }

    public void addToTable(String table,String nameInput, String contactsInput, String commentsInput) throws ClassNotFoundException, SQLException {
        Connection con;
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:expedition.db");
        System.out.println("Opened database successfully");

        String query = "INSERT INTO " + table +"  (name, contacts, comments) VALUES  (?, ?, ?)";

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, nameInput);
        stmt.setString(2, contactsInput);
        stmt.setString(3, commentsInput);
        stmt.executeUpdate();
        System.out.println("запись выполнена");
        stmt.close();
        con.close();
    }

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    }





