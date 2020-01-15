(function () {
    'use strict';

    angular.module('tm').controller('PesqTestController', function ($scope, $timeout, TestService, ngIntroService) {

    	function init () {

            $scope.tests = [];
            $scope.searchText = '';
            ngIntroService.refresh();

    	};
    	
        $scope.IntroOptions = {
    			steps : [{
    	    		intro: 'Esta é a pesquisa de provas, aqui você pode buscar as provas criadas previamente.'
    	    	},
    	    	{
    	    		element: document.querySelector('#test-step2'),
    	    		intro: 'Basta utilizar esta barra de busca com qualquer termo relacionado a prova desejada, como descrição ou categorias',
    	    		position: 'bottom'
    	    	},
    	    	{
    	    		element: document.querySelector('#test-step3'),
    	    		intro: 'Clique aqui para criar uma nova prova.',
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

    	$scope.clearForm = function () {

            $scope.tests = [];
            $scope.searchText = '';

        };

        $scope.buscar = function () {

			TestService.query({searchText: $scope.searchText}, function (data) {
	
				$scope.tests = data;
	
				noResultforSearch($scope, data);
	
			}, function (err) {
	
				console.error('Ocorreu um erro ao pesquisar. Tente novamente.', err);
				Materialize.toast('Ocorreu um erro ao pesquisar. Tente novamente.', 6000, 'rounded');
	
			});

        };
        
        $scope.desativar = function (test, index) {
        	
        	console.log('id to delete: ', test.id);
        	
        	test.active = false;
        	
        	TestService.update(test, function (data) {
        		
        		Materialize.toast('Prova desativada com sucesso.', 6000, 'rounded');
        		$scope.tests.splice(index, 1);
        		
        	}, function (err) {
        		
        		console.error('Erro ao desativar prova.', err);
        		Materialize.toast('Erro ao desativar prova. Tente novamente.', 6000, 'rounded');
        		
        	});
        	
        };
        
        function remove (t, index) {

        	TestService.delete({'id': t.id}, function (data) {

        		Materialize.toast('Prova excluída com sucesso.', 6000, 'rounded');
        		$scope.tests.splice(index, 1);

        	}, function (err) {

        		console.error('Erro ao excluir prova.', err);
        		Materialize.toast('Erro ao excluir prova. Tente novamente.', 6000, 'rounded');

        	});

        };

        $scope.validateRemove = function (t, index) {

        	if (t.validate) {
        		remove(t, index);
        	} else {
        		t.validate = true;
        	}

        };

        init();

    });

})();