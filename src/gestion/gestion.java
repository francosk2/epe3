/*

 * Cryptographic API.

 *

 * AES Cipher Algorithm.

 *

 *

 * Linux developers:

 *  Franco Monsalve

 *  Exequiel Rodriguez 

 *  Esteban Sanchez

 *

 * This program is free software; you can redistribute it and/or modify

 * it under the terms of the GNU General Public License as published by

 * the Free Software Foundation; either version 2 of the License, or

 * (at your option) any later version.

 *

 * ---------------------------------------------------------------------------

 * Copyright (c) 2002, Dr Brian Gladman <brg@gladman.me.uk>, Worcester, UK.

 * All rights reserved.

 *

 * LICENSE TERMS

 *

 * The free distribution and use of this software in both source and binary

 * form is allowed (with or without changes) provided that:

 *

 *   1. distributions of this source code include the above copyright

 *      notice, this list of conditions and the following disclaimer;

 *

 *   2. distributions in binary form include the above copyright

 *      notice, this list of conditions and the following disclaimer

 *      in the documentation and/or other associated materials;

 *

 *   3. the copyright holder's name is not used to endorse products

 *      built using this software without specific written permission.

 *

 * ALTERNATIVELY, provided that this notice is retained in full, this product

 * may be distributed under the terms of the GNU General Public License (GPL),

 * in which case the provisions of the GPL apply INSTEAD OF those given above.

 *

 * DISCLAIMER

 *

 * This software is provided 'as is' with no explicit or implied warranties

 * in respect of its properties, including, but not limited to, correctness

 * and/or fitness for purpose.

 * ---------------------------------------------------------------------------

 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
     package gestion;
/**
 *
 * @author Franco
 */
    import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;

    public class gestion {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        String nombrebd = "DESYC.sqlite";
        String URL = "jdbc:sqlite:"+nombrebd;
        String Driver = "org.sqlite.JDBC";
    
   
    public void connect(){
 try {
     conexion= DriverManager.getConnection(URL);
     if (conexion!=null) {
         System.out.println("Conectado");
     }
 }catch (SQLException ex) {
     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
 }}
    
    public void crearBD(){

        try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            JOptionPane.showMessageDialog(null, "Base de Datos creada","Creada",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }

    }//Fin metodo
    
    public void crearTabla(){
        
        try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "CREATE TABLE PROVEEDOR"+
                    "(ID_PROV INT PRIMARY KEY NOT NULL,"+
                    " NOMBRE TEXT NOT NULL,"+
                    " APELLIDO TEXT NOT NULL,"
                    + "EMAIL TEXT NOT NULL,"
                    + "SEXO TEXT NOT NULL)";
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Tabla  creada","Creada",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//fin metodo
    public void crearTablapro(){
        
        try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "CREATE TABLE PRODUCTO"+
                    "(ID_PRO INT PRIMARY KEY NOT NULL,"+
                    " NOMBRE_PRO TEXT NOT NULL,"+
                    " MARCA TEXT NOT NULL,"+
                    " PRECIO INT NOT NULL,"+
                    " ID_PROV INT NOT NULL,"+
                    " ID_TIPO INT NOT NULL,"+
                    " FOREIGN KEY (ID_PROV) REFERENCES LIBROS(ID_PROV),"+
                    " FOREIGN KEY (ID_TIPO) REFERENCES LIBROS(ID_TIPO))";
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Tabla  creada","Creada",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//fin metodo
    public void crearTablaTIP(){
        
        try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "CREATE TABLE TIPO"+
                    "(ID_TIPO INT PRIMARY KEY NOT NULL,"+
                    " TIPO TEXT NOT NULL,"+
                     " ANO INT NOT NULL )";
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Tabla  creada","Creada",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//fin metodo
        
    public void insertardatospro(int id_pro, String nombre_pro, String marca, int precio, int id_prov, int id_tipo){
    
         try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "INSERT INTO PRODUCTO "+
                    "(ID_PRO,NOMBRE_PRO,MARCA,PRECIO,ID_PROV,ID_TIPO) VALUES " +
                    "('"+id_pro+"','"+nombre_pro+"','"+marca+"','"+precio+"','"+id_prov+"','"+id_tipo+"')";
            
            
            
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Dato insertado","insertado",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void insertardatos(int id_prov, String nombre,String apellido, String email, String sexo){
    
         try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            
            
            String sql = "INSERT INTO PROVEEDOR "+
                    "(ID_PROV,NOMBRE,APELLIDO,EMAIL,SEXO) VALUES " +
                    "('"+id_prov+"','"+nombre+"','"+apellido+"','"+email+"','"+sexo+"')";
            

            
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Dato insertado","insertado",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void insertardatostip(int id_tipo, String tipo, int ano){
    
         try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            
            
            String sql = "INSERT INTO TIPO "+
                    "(ID_TIPO,TIPO,ANO) VALUES " +
                    "('"+id_tipo+"','"+tipo+"','"+ano+"')";
            

            
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Dato insertado","insertado",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void actualizardatos(int id, String nombre,String apellido, String email, String sexo){
    
         try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "UPDATE PROVEEDOR SET NOMBRE = '"+nombre+"' ,"+
                    " APELLIDO = '"+apellido+"' ," + 
                    " EMAIL = '"+email+"' ," + 
                    " SEXO = '"+sexo+"' " +
                    " WHERE ID = '"+id+"' ";
                    
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Dato actualizado","actualizado",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void actualizardatosnomPro(int id_pro, String nombre_pro){
 try{
    
        Class.forName(Driver);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE PRODUCTO SET NOMBRE_PRO ='"+nombre_pro+"' WHERE ID_PRO = '"+id_pro+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado el nombre del producto","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
    public void actualizardatosmarca(int id_pro, String marca){
 try{
    
        Class.forName(Driver);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE PRODUCTO SET MARCA ='"+marca+"' WHERE ID_PRO = '"+id_pro+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado la marca del producto","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
    public void actualizardatosprecio(int id_pro, int precio){
 try{
    
        Class.forName(Driver);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE PRODUCTO SET PRECIO ='"+precio+"' WHERE ID_PRO = '"+id_pro+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado la precio del producto","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
    
       
     public void eliminardatos(){
    
         try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
             String id = JOptionPane.showInputDialog(null," ingrese id de la columna que desea borrar");
            String sql = "DELETE FROM PROVEEDOR WHERE ID = '"+id+"'";
                    
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Dato eliminado","eliminado",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
     public void eliminardatosPRO(){
    
         try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String id_pro = JOptionPane.showInputDialog(null," ingrese id de la columna que desea borrar");
            String sql = "DELETE FROM PRODUCTO WHERE ID_PRO = '"+id_pro+"'";
                    
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Dato eliminado","eliminado",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
     public void eliminardatostip(){
    
         try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String id_tipo = JOptionPane.showInputDialog(null," ingrese id de la columna que desea borrar");
            String sql = "DELETE FROM TIPO WHERE ID_TIPO = '"+id_tipo+"'";
                    
            sentencia.executeUpdate(sql);
            sentencia.close();
            JOptionPane.showMessageDialog(null, "Dato eliminado","eliminado",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
      
      
    public void mostrarDatos(JTable Tablacliente){
    
        try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM PROVEEDOR";
            resultado = sentencia.executeQuery(sql);
            int fila=0;
            while(resultado.next()){
                Tablacliente.setValueAt(resultado.getInt("ID_PROV"),fila, 0);
                Tablacliente.setValueAt(resultado.getString("NOMBRE"),fila, 1);
                Tablacliente.setValueAt(resultado.getString("APELLIDO"),fila, 2);
                Tablacliente.setValueAt(resultado.getString("EMAIL"),fila,3);
                Tablacliente.setValueAt(resultado.getString("SEXO"),fila,4);
                fila++;
            }
            
            resultado.close();
            sentencia.executeUpdate(sql);
            sentencia.close();
            
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    
    
    
    }
    public void mostrarDatospro(JTable tablaproducto){
    
        try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM PRODUCTO";
            resultado = sentencia.executeQuery(sql);
            int fila=0;
            while(resultado.next()){
                tablaproducto.setValueAt(resultado.getInt("ID_PRO"),fila, 0);
                tablaproducto.setValueAt(resultado.getString("NOMBRE_PRO"),fila, 1);
                tablaproducto.setValueAt(resultado.getString("MARCA"),fila, 2);
                tablaproducto.setValueAt(resultado.getString("PRECIO"),fila,3);
                tablaproducto.setValueAt(resultado.getString("ID_PROV"),fila,4);
                tablaproducto.setValueAt(resultado.getString("ID_TIPO"),fila,5);
                fila++;
            }
            
            resultado.close();
            sentencia.executeUpdate(sql);
            sentencia.close();
            
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    
    }    
    public void mostrarDatostip(JTable tablatipo){
    
        try{
            Class.forName(Driver);
            conexion = DriverManager.getConnection(URL);
            sentencia = conexion.createStatement();
            String sql = "SELECT * FROM TIPO";
            resultado = sentencia.executeQuery(sql);
            int fila=0;
            while(resultado.next()){
                tablatipo.setValueAt(resultado.getInt("ID_TIPO"),fila, 0);
                tablatipo.setValueAt(resultado.getString("TIPO"),fila, 1);
                tablatipo.setValueAt(resultado.getString("ANO"),fila, 2);
                fila++;
            }
            
            resultado.close();
            sentencia.executeUpdate(sql);
            sentencia.close();
            
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    
    }  
     
    public void CONSULTAdostablas(JTable J){ 
    try{
 
     Class.forName(Driver);
     conexion = DriverManager.getConnection(URL);
     sentencia = conexion.createStatement();
     String sql ="select PRODUCTO.ID_PRO,NOMBRE,APELLIDO,NOMBRE_PRO\n" +
                        "FROM PROVEEDOR\n" +
                        "JOIN PRODUCTO\n" +
                        "ON PROVEEDOR.ID_PROV=PRODUCTO.ID_PROV\n" ;

     resultado = sentencia.executeQuery(sql);
     int fila=0;
     while(resultado.next()){
     J.setValueAt(resultado.getInt("ID_PRO"),fila,0);
     J.setValueAt(resultado.getString("NOMBRE"),fila,1);
     J.setValueAt(resultado.getString("APELLIDO"),fila,2);
     J.setValueAt(resultado.getString("NOMBRE_PRO"),fila,3);
     fila++;
     }
     
     sentencia.close();
     conexion.close();
       
 }catch(HeadlessException | ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);


}
}  

    public void CONSULTAtrestablas(JTable J){ 
    try{
 
     Class.forName(Driver);
     conexion = DriverManager.getConnection(URL);
     sentencia = conexion.createStatement();
     String sql ="select PROVEEDOR.ID_PROV,NOMBRE,NOMBRE_PRO,MARCA,PRECIO,TIPO\n" +
                        "FROM PRODUCTO\n" +
                        "JOIN PROVEEDOR\n" +
                        "ON PRODUCTO.ID_PROV=PROVEEDOR.ID_PROV\n" +
                        "JOIN TIPO\n" +
                        "ON PRODUCTO.ID_TIPO=TIPO.ID_TIPO\n";

     resultado = sentencia.executeQuery(sql);
     int fila=0;
     while(resultado.next()){
     J.setValueAt(resultado.getInt("ID_PROV"),fila,0);
     J.setValueAt(resultado.getString("NOMBRE"),fila,1);
     J.setValueAt(resultado.getString("NOMBRE_PRO"),fila,2);
     J.setValueAt(resultado.getString("MARCA"),fila,3);
     J.setValueAt(resultado.getString("PRECIO"),fila,4);
     J.setValueAt(resultado.getString("TIPO"),fila,5);
     fila++;
     }
     
     sentencia.close();
     conexion.close();
       
 }catch(HeadlessException | ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);


}
}  
    
    public static void main(String[] args) {
        gestion llamada = new gestion();
       llamada.crearBD();
       llamada.crearTabla();
       llamada.crearTablapro();
       llamada.crearTablaTIP();
     // llamada.connect();
          
        
    }
  
}
