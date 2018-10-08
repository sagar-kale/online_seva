app.controller('courcesCntrl', ['$scope', '$state', 'apiLink', 'APIService', function($scope, $state, apiLink, APIService) {

    $scope.listOfCources = ["MSCIT","DTP","CCC","TALLY","HTML","CSS","CPP","Java"]; 
    $scope.selectedDiv = "Register";
    
    $scope.toggleForms = function(str){
        $scope.selectedDiv = str;
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
    $scope.saveStudent = function(student){

        var data = 
        {
        "email": student.email,
        "username":$("#unamefrmserver").val(),
        "name": student.name,
        "phone": student.Contact,
        "address": student.Address,
        "dob": student.dob,
        "aadhar": student.addhar,
        "state": student.state,
        "district": student.district,
        "city": student.city,
        "pincode": student.pincode
        //"courseName":student.courseName,
        
        }
        console.log("studentInfo:::",data);
        var url = apiLink.saveStudent;   

        APIService.httpPostJson(url, data).then(
            function (res) {
                if (res.data.msgType == 'error') {
                    swal("", res.data.message, res.data.msgType);
                }
                else {
                    console.log("student saved", res.data);
                    swal("", res.data.message, res.data.msgType);                    
                    $scope.student = {};                   
                   // $scope.student.$setPristine();                   
                }

            },
            function (error) {
                console.log(error);
            });
    }
        
$scope.getAllApprovedStudentsList= function() {
        var url = apiLink.getAllStudents;
       // var data = {"username":$("#unamefrmserver").val()}
        APIService.httpPostJson(url,$("#unamefrmserver").val()).then(
            function(res) {
                $scope.stdList = res.data;
                console.log("jkhaksdkja", $scope.stdList);


            },
            function(error) {
                console.log(error);
            });
    }
   // $scope.getAllApprovedStudentsList();

    $('.userActionsDiv ul li').click(function(e) {
        $('.userActionsDiv ul li').removeClass('activeLi');     
        $(this).addClass('activeLi');
        
    });


    }]);
