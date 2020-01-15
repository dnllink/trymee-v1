(function () {
    'use strict';

    angular.module('tm').controller('CadUserController', function ($scope, $routeParams, SubscriptionService, ngIntroService) {

    	var index;

    	function init () {

    		$scope.message = '';
    		$scope.password = ''
    		$scope.passwordConfirm = '';
            $scope.user = {};
            $scope.subscription = {};
            
            ngIntroService.refresh();

			SubscriptionService.get({}, function (data) {
				
				if (data) {

					$scope.user = data.user;
    				$scope.subscription = data;

				} else {

					Materialize.toast('Erro ao carregar seu cadastro. Tente novamente.', 6000, 'rounded');

				};

			}, function (err) {

				console.error(err);
				Materialize.toast('Erro ao carregar seu cadastro. Tente novamente.', 6000, 'rounded');

			});

    	};
    	
        $scope.IntroOptions = {
    			steps : [{
    	    		element: document.querySelector('#cad-user-step1'),
    	    		intro: 'Aqui você pode verificar os dados do seu cadastro.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-user-step2'),
    	    		intro: 'Aqui você pode alterar sua senha de acesso.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-user-step3'),
    	    		intro: 'Aqui você pode verificar os dados do seu plano, como vigência, testes restantes e valor do plano.',
    	    		position: 'bottom'
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

        $scope.updatePass = function () {

        	if ($scope.password != '' && $scope.passwordConfirm != '') {

        		if ($scope.password == $scope.passwordConfirm) {

        			var passMessage = checkPwd($scope.password);

        			if (passMessage != '') {

        				Materialize.toast(passMessage, 6000, 'rounded');
        				return;

        			}

        			SubscriptionService.update({user: '', pass: $scope.password}, function (data) {

        				Materialize.toast('Senha atualizada com sucesso.', 6000, 'rounded');
        				$scope.password = ''
        		    	$scope.passwordConfirm = '';

        			}, function (err) {

        				$scope.message = err.data;

        			});

        		} else {

        			Materialize.toast('Ambos os campos de senha precisam ser iguais.', 6000, 'rounded');

        		}

        	} else {

        		Materialize.toast('Preencha ambos os campos de senha.', 6000, 'rounded');

        	}

        };
        
        $scope.resetIntros = function () {
        	resetAllIntros();
        	Materialize.toast('Tutoriais reiniciados, visite novamente as outras telas para visualizar.', 6000, 'rounded');
        };
        
        init();

    });

})();