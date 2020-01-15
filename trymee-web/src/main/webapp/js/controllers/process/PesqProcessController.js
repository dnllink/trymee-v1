(function() {
    'use strict';

    angular.module('tm').controller('PesqProcessController', function($scope, $timeout, ProcessService, ngIntroService) {

        function init() {

        	$scope.processes = [];

        	ngIntroService.refresh();

            $scope.IntroOptions = {
        			steps : [{
        	    		intro: 'Esta é a pesquisa de processos, aqui você pode buscar os processos criados previamente.'
        	    	},
        	    	{
        	    		element: document.querySelector('#process-step2'),
        	    		intro: 'Basta utilizar esta barra de busca com qualquer termo relacionado ao processo desejado.',
        	    		position: 'bottom'
        	    	},
        	    	{
        	    		element: document.querySelector('#process-step3'),
        	    		intro: 'Clique aqui para criar um novo processo.',
        	    		position: 'top'
        	    	}],
        			showStepNumbers : false,
        			showBullets : false,
        			exitOnOverlayClick : true,
        			exitOnEsc : true,
        			nextLabel : '>>',
        			prevLabel : '<<',
        			skipLabel : 'sair',
        			doneLabel : 'ok'
        		};
            
            ProcessService.query({startPagination: 1, endPagination: 3}, function (data) {

        		$scope.processes = data;

        	}, function (err) {

        		console.log(err);

        	});

        };

        $scope.buscar = function () {

        	ProcessService.query({description: $scope.description}, function (data) {

        		$scope.processes = data;

        		noResultforSearch($scope, data);

        	}, function (err) {

        		console.log(err);

        	});

        };
        
        init();

  });

})();