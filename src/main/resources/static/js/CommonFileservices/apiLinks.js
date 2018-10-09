app.service('apiLink', function () {
    var urlBase = '/';
    // var urlBase = 'https://online-seva.cfapps.io/';
    return {
        'register': urlBase + 'register',
        'login': urlBase + 'login',
        'getAllUsers': urlBase + 'users/all',
        'logout': urlBase + 'logout',
        'getAllJobs': urlBase + 'jobs/all',
        'saveJobDetails': urlBase + 'jobs/save',
        'currentUserSession': urlBase + 'current/user',
        'updateUser': urlBase + 'user/update/status',
        'removeUser': urlBase + 'remove/user',
        "saveStudent": urlBase + "student/register",
        "getAllStudents": urlBase + "student/all",
        "updateStudentStatus": urlBase + "student/update/status"
    }
});
    

 
  




