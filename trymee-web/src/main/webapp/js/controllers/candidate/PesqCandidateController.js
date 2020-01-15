(function () {
    'use strict';

    angular.module('tm').controller('PesqCandidateController', function ($scope, $timeout, CandidateService, ngIntroService) {

    	function init () {

            $scope.candidates = [];
            $scope.searchText = '';
            ngIntroService.refresh();

    	};

    	$scope.clearForm = function () {

            $scope.candidates = [];
            $scope.searchText = '';

        };
        
        $scope.buscar = function () {
        	
    		CandidateService.query({searchText: $scope.searchText}, function (data) {

    			$scope.candidates = data;
    			
    			noResultforSearch($scope, data);

    		}, function (err) {

    			console.error('Ocorreu um erro ao pesquisar. Tente novamente.', err);
    			Materialize.toast('Ocorreu um erro ao pesquisar. Tente novamente.', 6000, 'rounded');

    		});

        };

        function remove (c, index) {

        	CandidateService.delete({'id': c.id}, function (data) {

        		Materialize.toast('Candidato excluído com sucesso.', 6000, 'rounded');
        		$scope.candidates.splice(index, 1);

        	}, function (err) {

        		console.error('Erro ao excluir candidato.', err);
        		Materialize.toast('Erro ao excluir candidato. Tente novamente.', 6000, 'rounded');

        	});

        };

        $scope.validateRemove = function (c, index) {

        	if (c.validate) {
        		remove(c, index);
        	} else {
        		c.validate = true;
        	}

        };
        
        $scope.IntroOptions = {
    			steps : [{
    	    		intro: 'Esta é a pesquisa de candidatos, aqui você pode buscar os candidatos criados previamente.'
    	    	},
    	    	{
    	    		element: document.querySelector('#candidate-step2'),
    	    		intro: 'Basta utilizar esta barra de busca inserindo partes do nome ou e-mail do candidato desejado.',
    	    		position: 'bottom'
    	    	},
    	    	{
    	    		element: document.querySelector('#candidate-step3'),
    	    		intro: 'Clique aqui para criar um novo candidato.',
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

        init();

    });

})();