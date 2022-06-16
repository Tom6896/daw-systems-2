package com.task.Task2.model

import java.sql.DriverManager
import com.task.Task2.model.DataBaseConnector
import java.sql.Connection

data class User(val id: Long, val name: String, val surname: String)

class DataBase() {

    private var usersMutableList = mutableListOf<User>()
    private lateinit var connection: Connection
    private fun setConnection(){
        val conn = DataBaseConnector()
        connection = conn.connect()

    }

    init {
        //set database connection
        setConnection()
    }

    fun getListOfUsers(): MutableList<User>{
        val query = connection.prepareStatement("SELECT * FROM users;")
        val result = query.executeQuery()
        //prevent refresh duplication
        usersMutableList = mutableListOf()
        if (result != null){
            while (result.next()) {
                val user = User(result.getLong("id"),
                    result.getString("name"),
                    result.getString("surname"))
                usersMutableList.add(user)
            }
        }


        return usersMutableList
    }

    fun getUser(_user: User): User{
        val query = connection.prepareStatement("SELECT * FROM users WHERE id=${_user.id};")
        val result = query.executeQuery()
        //prevent refresh duplication
        var user = User(0,"none","none")
        if (result != null){
            while (result.next()) {
                val userQ = User(result.getLong("id"),
                    result.getString("name"),
                    result.getString("surname"))
                user = userQ
            }
        }

        return user
    }

    fun deleteUser(rowId:Long){
        val query = connection.prepareStatement("DELETE FROM users WHERE id=$rowId ;")
        query.execute()

    }

    fun editUser(user: User){
        val query = connection.prepareStatement("UPDATE users SET name='${user.name}', surname='${user.surname}' WHERE id=${user.id} ;")
        query.execute()

    }

    fun addUser(user: User){
        val query = connection.prepareStatement("INSERT INTO users (id, name, surname) VALUES (default , '${user.name}', '${user.surname}') ;")
        query.execute()

    }

}
