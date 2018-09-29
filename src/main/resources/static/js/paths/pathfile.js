var app = angular.module('demoApp', ['ui.router']);
app.constant('urls', {
    BASE: 'http://localhost:8080/',
    USER_SERVICE_API : 'http://localhost:8080/SpringBootCRUDApp/api/user/'
});

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('header.home');
    $stateProvider
        .state("login", {
            url: "/login",
            templateUrl: "../views/login.html",
            controller : "logincontroller"
        })
          .state("header", {
            url: "/header",
            templateUrl: "../views/header.html",
            controller : "headerContrl"
        })
        .state("header.home", {
            url: "/home",
            templateUrl: "../views/home.html",
            controller : "homeController"
        })
        .state("header.jobs", {
            url: "/jobdetails",
            templateUrl: "../views/jobDeatials.html",
            controller : "jobsController"
        })
         .state("header.admin", {
            url: "/admin",
            templateUrl: "../views/admin.html",
            controller : "adminContrl"
        })
            .state("header.cources", {
            url: "/cources",
            templateUrl: "../views/cources.html",
            controller : "courcesCntrl"
        })

});










    

    