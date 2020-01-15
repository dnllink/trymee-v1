(function() {
    'use strict';

    angular.module('tm').controller('CorrectTestController', function($scope, $routeParams, AnswerService){
        
        var init = function () {

            $scope.message = '';
            $scope.answers = [];

            if ($routeParams.idCandidate && $routeParams.idProcess) {
            	
            	AnswerService.query({idCandidate: $routeParams.idCandidate, idProcess: $routeParams.idProcess}, function (data) {
            		
            		$scope.answers = data;
            		
            	}, function (err) {
            		
            		console.log(err);
            		
            	});

    		};

        };

        $scope.save = function () {

        	console.log($scope.answers);
        	
        	$scope.answers.forEach(function (a) {
        		
        		a.question = {id: a.question.id};
        		a.candidate = {id: a.candidate.id};
        		a.process = {id: a.process.id};
        		
        	});
        	
        	AnswerService.update($scope.answers, function (data) {
        		
        		$scope.message = 'Correçoes salvas com sucesso.';
        		$scope.answers = [];
        		
        	}, function (err) {
        		
        		$scope.message = 'Ocorreu um erro ao salvar as correções, tente novamente.';
        		console.log(err.data);
        		
        	});

        };

        init();

  });

})();