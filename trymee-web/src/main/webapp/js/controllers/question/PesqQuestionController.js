(function () {
    'use strict';

    angular.module('tm').controller('PesqQuestionController', function ($scope, $timeout, QuestionService, ngIntroService) {

    	function init () {

            $scope.questions = [];
            $scope.searchText = '';
            ngIntroService.refresh();

    	};

    	$scope.clearForm = function () {

            $scope.questions = [];
            $scope.searchText = '';

        };

        $scope.buscar = function () {

    		QuestionService.query({searchText: $scope.searchText}, function (data) {

    			$scope.questions = data;

    			noResultforSearch($scope, data);

    		}, function (err) {

    			console.error('Ocorreu um erro ao pesquisar. Tente novamente.', err);
    			Materialize.toast('Ocorreu um erro ao pesquisar. Tente novamente.', 6000, 'rounded');

    		});

        };

        function remove (q, index) {

        	QuestionService.delete({'id': q.id}, function (data) {

        		Materialize.toast('Questão excluida com sucesso.', 6000, 'rounded');
        		$scope.questions.splice(index, 1);

        	}, function (err) {

        		console.error('Erro ao excluir questão.', err);
        		Materialize.toast('Erro ao excluir questão. Tente novamente.', 6000, 'rounded');

        	});

        };

        $scope.validateRemove = function (q, index) {

        	if (q.validate) {
        		remove(q, index);
        	} else {
        		q.validate = true;
        	}

        };
        
        $scope.IntroOptions = {
    			steps : [{
    	    		intro: 'Esta é a pesquisa de questões, aqui você pode buscar as questões criadas previamente.'
    	    	},
    	    	{
    	    		element: document.querySelector('#question-step2'),
    	    		intro: 'Basta utilizar esta barra de busca com qualquer termo relacionado a questão desejada, como descrição ou categoria.',
    	    		position: 'bottom'
    	    	},
    	    	{
    	    		element: document.querySelector('#question-step3'),
    	    		intro: 'Clique aqui para criar uma nova questão.',
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