package com.task.Task2

import com.task.Task2.model.DataBase
import com.task.Task2.model.User
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/")
class HtmlController {

    var db = DataBase()

    @GetMapping("/")
    fun task2():String{

        return ""
    }

    @GetMapping("GET /user")
    fun task2UserGet(model: Model):String{
        var user = mutableListOf<User>()
        user = db.getListOfUsers()
        return user.toString()
    }

    @GetMapping("POST /user")
    fun task2UserPost(model: Model){
        val user = User(0,"none","none")
        db.addUser(user)
    }

    @GetMapping("PUT /user/{id}")
    fun task2UserPUT(id:Long){
        val user = User(id,"test","Test")
        db.editUser(user)
    }

    @GetMapping("DELETE /user/{id}")
    fun task2UserDelete(id:Long){
        db.deleteUser(id)
    }

}