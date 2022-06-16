package com.task.Task2.model

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DataBaseConnector {

    private val jdbcUrl = "jdbc:mysql://localhost:3306/task2"
    private val user = "admin"
    private val pass = "admin"
    fun connect(): Connection {
        lateinit var connection:Connection
        try{
            connection = DriverManager.getConnection(jdbcUrl,user,pass)
        }
        catch (e: SQLException){
            println("Error: $e")
        }
        return connection
    }

}