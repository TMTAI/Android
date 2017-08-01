package controllers;

import ninja.Result;
import ninja.Results;

public class LoginLogoutController {
    public Result login(){

        return Results.html();
    }

    public Result logout(){

        return Results.redirect("/login");
    }
}
