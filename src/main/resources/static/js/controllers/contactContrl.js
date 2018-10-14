app.controller('contactCntrl', ['$scope', '$state', 'apiLink', 'APIService', function ($scope, $state, apiLink, APIService) {

$scope.questionSet = [
			{
			"id":11,
			"question":"Where is the correct place to insert a wwwss?",
			"options":
				[
					{"opt":"The <body> section1","b": false},
					{"opt":"The <head> section1","b": false},
					{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
					{"opt":"non1e", "b": false}
				],
				"isAnswered":false
				
			},			
	
			{
			"id":22,
			"question":"Where is the correct place to insert a JavaScript123131?",
			"options":
				[
					{"opt":"The <body> section1","b": false},
					{"opt":"The <head> section1","b": false},
					{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
					{"opt":"non1e", "b": false}
				],
				"isAnswered":false
			},
			{
				"id":3,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":4,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":5,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":6,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":7,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":8,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":9,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":10,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":1,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":12,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":13,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				},
			{
				"id":14,
				"question":"Where is the correct place to insert a JavaScript123131?",
				"options":
					[
						{"opt":"The <body> section1","b": false},
						{"opt":"The <head> section1","b": false},
						{"opt":"TBoth the <head> section and the <body> section are correct1","b": false},
						{"opt":"non1e", "b": false}
					],
					"isAnswered":false
				}
			
			
	];

	$scope.selectedOption="";
	$scope.quesId = "";
	$scope.cnt = 0;
	$scope.isAnswered = function(question){
		
//		 angular.forEach($scope.questionSet, function(value, key){
//		      if(key.isAnswered == true)
//		    	  $scope.cnt++; 
//		   });
		//$scope.cnt++;
		question.isAnswered = true;
		
	
	}
	
	
}]);
