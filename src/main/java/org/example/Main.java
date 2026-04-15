package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String sqlDepartamento;
        String sqlEmpleado;

        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()); Statement statement = conn.createStatement()){

            // Se intenta borrar las tablas si existen
            try {
                sqlDepartamento = "DROP TABLE departamento CASCADE CONSTRAINTS";
                statement.executeUpdate(sqlDepartamento);
            } catch (SQLException e1){
                System.out.println("La tabla Departamento no existe");
            }

            try {
                sqlEmpleado = "DROP TABLE empleado CASCADE CONSTRAINTS";
                statement.executeUpdate(sqlEmpleado);
            } catch (SQLException e1){
                System.out.println("La tabla Empleado no existe");
            }

            sqlDepartamento = "CREATE TABLE departamento (" +
                    "dep_id NUMBER PRIMARY KEY," +
                    "nombre VARCHAR2(50))";
            statement.executeUpdate(sqlDepartamento);
            System.out.println("Tabla Departamento creada correctamente");

            sqlEmpleado = "CREATE TABLE empleado (" +
                    "emp_id NUMBER PRIMARY KEY," +
                    "nombre VARCHAR2(50)," +
                    "salario NUMBER," +
                    "dep_id NUMBER," +
                    "CONSTRAINT FK_departamento FOREIGN KEY (dep_id) REFERENCES departamento (dep_id))";
            statement.executeUpdate(sqlEmpleado);
            System.out.println("Tabla empleado creada correctamente");

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}