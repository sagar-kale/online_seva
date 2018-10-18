app.controller('logincontroller', ['$scope', '$state', 'apiLink', 'APIService', 'userService', function ($scope, $state, apiLink, APIService, userService) {
    console.log("inside logincontroller");
    $(document).scrollTop(0);
    $scope.userData = "";
    $scope.isLoginTab = true;
    $scope.settrue = function () {
        $scope.isLoginTab = true;
    }
    $scope.setfalse = function () {
        $scope.isLoginTab = false;
    }
    $scope.userData = "";

    $scope.checkCurrentUserSession = function(){

        
        var url = apiLink.currentUserSession;
                    APIService.httpGet(url).then(
                  function(res){
                    if(res.data.msgType !='error'){

                         userService.setUser(res.data.user);
                         $state.go("header.home");
                    }                  

                
                  
                  },
                  function(error) {
                    console.log(error);
                  });
    }
             $scope.checkCurrentUserSession();



    $scope.saveLoginForm = function () {
        var data = {
            "username": $scope.login.username,
            "password": $scope.login.password

        }
        var url = apiLink.login;

        APIService.httpPostJson(url, data).then(
            function (res) {
                $scope.userData = res.data.user;
                if (res.data.msgType == 'error') {

                    swal("", res.data.message, "error");
                    // $state.go("header.admin");
                }
                else {
                    console.log("success", res.data);
                    userService.setUser($scope.userData);
                    $state.go("header.home");

                }
            },
            function (error) {
                console.log(error);
            }); 
        // $state.go("header.admin");

    }

    $scope.regitserUser = function (formdata) {
        var data = {

            "name": formdata.fullname,
            "email": formdata.email,
            "password": formdata.EPassword,
            "passwordConfirm": formdata.ECPassword,
            "phone": formdata.contact,
            "centerName": formdata.CenterName,
            "dob": formdata.dob,
            "aadhar": formdata.Addhar,
            "state": formdata.State,
            "district": formdata.District,
            "city": formdata.City,
            "pincode": formdata.pincode
        }

        var url = apiLink.register;
        //alert(url);

        APIService.httpPostJson(url, data).then(
            function (res) {

                if (res.data.msgType == 'error') {
                    swal("", res.data.message, res.data.msgType);
                }
                else {
                    console.log("saved", res.data);
                    swal("", res.data.message, res.data.msgType);
                    
                    $scope.formData = {};
                    ;
                    $scope.myForm.$setPristine();
                    $("#tab-1").click();
                    $scope.isLoginTab = true;
                }

            },
            function (error) {
                console.log(error);
            });
       
    };

    $scope.options ={
   	     formatDay: 'd',
   	         showWeeks: false	
   }
  
     $scope.dt = new Date();
   
     $scope.open= function($event){
   	   $event.preventDefault();
          $event.stopPropagation();
          $scope.opened = true;  
     }
    
    $scope.districtList = [
        "Ahmednagar",
        "Akola",
        "Amravati",
        "Aurangabad",
        "Beed",
        "Bhandara",
        "Buldhana",
        "Chandrapur",
        "Dhule",
        "Gadchiroli",
        "Gondia",
        "Hingoli",
        "Jalgaon",
        "Jalna",
        "Kolhapur",
        "Latur",
        "Mumbai City",
        "Mumbai Suburban",
        "Nagpur",
        "Nanded",
        "Nandurbar",
        "Nashik",
        "Osmanabad",
        "Palghar",
        "Parbhani",
        "Pune",
        "Raigad",
        "Ratnagiri",
        "Sangli",
        "Satara",
        "Sindhudurg",
        "Solapur",
        "Thane",
        "Wardha",
        "Washim",
        "Yavatmal"
    ];
  

}]);