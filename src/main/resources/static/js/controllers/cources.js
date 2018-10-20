app.controller('courcesCntrl', ['$scope', '$state', 'apiLink', 'APIService', function ($scope, $state, apiLink, APIService) {

    $scope.listOfCources = ["MSCIT", "DTP", "CCC", "TALLY", "HTML", "CSS", "CPP", "Java"];
    $scope.selectedDiv = "Register";

    $scope.toggleForms = function (str) {
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
    $scope.saveStudent = function (student) {

        var data =
            {
                "email": student.email,
                "username": $("#unamefrmserver").val(),
                "name": student.name,
                "phone": student.Contact,
                "address": student.Address,
                "dob": student.dob,
                "aadhar": student.addhar,
                "state": student.state,
                "district": student.district,
                "city": student.city,
                "pincode": student.pincode,
                "courseName":student.courseName,

            }
        console.log("studentInfo:::", data);
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
                    $scope.stdRegForm.$setPristine();
                }

            },
            function (error) {
                console.log(error);
            });
    }

    $scope.getAllStudentsList = function () {
        var url = apiLink.getAllStudents;
        // var data = {"username":$("#unamefrmserver").val()}
        APIService.httpPostJson(url, $("#unamefrmserver").val()).then(
            function (res) {
                if (res.data.msgType == 'error') {

                    swal("", res.data.message, res.data.msgType);
                    $state.go("login");
                }
                $scope.stdList = res.data;
                console.log("StudentList", $scope.stdList);

            },
            function (error) {
                console.log(error);
            });
    }
 

    $('.userActionsDiv ul li').click(function (e) {
        $('.userActionsDiv ul li').removeClass('activeLi');
        $(this).addClass('activeLi');

    });
    

    $scope.removeStudent = function (std) {
        var url = apiLink.removeStudent;
        //   var data = {"username":user.username};
        APIService.httpDelete(url, std.email).then(
            function (res) {

                if (res.data.msgType == 'success') {
                   
                    swal("", res.data.message,res.data.msgType);
                    $scope.getAllStudentsList();

                }
                else {
                    swal("", res.data.message, res.data.msgType);
                }

            },
            function (error) {
                console.log(error);
            });
    }


    
    $scope.uploadFile1 = function(filename,stdEmail){   
    	  var formData = new FormData();
    	  var fileId = '#' + filename;
    	  var file = $(fileId).get(0).files[0];
    	  formData.append('file', file);    	
    	  formData.append('email', stdEmail);
    	  console.log("file iss",file);
    	  $.ajax({    	   
    		  url: '/student/image/upload',
    		    data: formData,
    		    cache: false,
    		    contentType: false,
    		    processData: false,
    		    method: 'POST',
    		    type: 'POST',
    	    
    	    success: function (res) {
    	    	console.log(res);
    	    	swal(res.msgType, res.message,res.msgType);
              console.log("FileUloaded Successfully");
            },
            error: function (error) {
            	 console.log("Not able to upload file");
            },
    	  })
    	};
}]);
