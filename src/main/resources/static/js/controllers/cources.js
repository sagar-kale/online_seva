app.controller('courcesCntrl',['$scope', '$state' ,function($scope, $state) {

	$scope.listOfCources = ["MSCIT","DTP","CCC","TALLY","HTML","CSS","CPP","Java"];	
	$scope.selectedDiv = "Register";
	
	$scope.toggleForms = function(str){
		$scope.selectedDiv = str;
	}

	jobData = 
	[


	{
	"JobTitle": "",
	"totalPosts": "",
	"Sector": "",
	"Qualfication": "",
	"lastDate": "",


	"subDetails": [{
		"startDate": "",
		"lastDate": "",
		"jobDescription": "",
		"postName": "",
		"totalVacancies": "",
		"salary/payscale": "",
		"jobLocation": "",

		"EligibilityCriteria": [{
			"educationalQualification": "",
			"AgeLimit": [{
				"open": "",
				"cast": ""
			}]
			
		}],
		"selectionProcess": "",
		"ApplicationFee": "",
		"HowtoApply": "",
		"impLinks": ""


	}

	]
}]
}]);