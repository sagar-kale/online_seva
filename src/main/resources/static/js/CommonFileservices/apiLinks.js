app.service('apiLink', function () {
    // var urlBase = 'http://localhost:8080/';
    var urlBase = 'https://online-seva.cfapps.io/';
    return {
        'register': urlBase + 'register',
        'login': urlBase + 'login',
        'getAllUsers': urlBase + '/users/all'
    }

});
    

 
  




