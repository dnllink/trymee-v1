(function () {
    'use strict';

    angular.module('tm').controller('TestController', function ($scope, $routeParams, $document, TestService, TestModule) {

    	function init () {

    		if (!$routeParams.token) {
    			console.error('Não foi informado token');
    			$scope.message = 'Erro ao procurar prova, link inválido.';
    			//TODO implementar redirecionamento de erro
    		}

			$scope.qtChanges = 0;

			var hidden, visibilityChange;
			if (typeof $document[0].hidden !== "undefined") {
				hidden = "hidden";
				visibilityChange = "visibilitychange";
			} else if (typeof $document[0].mozHidden !== "undefined") {
				hidden = "mozHidden";
				visibilityChange = "mozvisibilitychange";
			} else if (typeof $document[0].msHidden !== "undefined") {
				hidden = "msHidden";
				visibilityChange = "msvisibilitychange";
			} else if (typeof $document[0].webkitHidden !== "undefined") {
				hidden = "webkitHidden";
				visibilityChange = "webkitvisibilitychange";
			}

			$document[0].addEventListener(visibilityChange, log, false);

			function log() {
				if ($document[0].hidden) {
					$scope.qtChanges++;
					console.log('Mudanças de aba/janela: ' + $scope.qtChanges++);
				}
			}
    		
    		TestModule.t = $routeParams.token;

    		TestService.get({token: $routeParams.token}, function (data) {
    			
    			TestModule.q = data.questions;
    			$scope.qs = data.qs;
    			
    			TestModule.c = data.c;
    			$scope.c = data.c;

                if($scope.qs[0])
                    $scope.setaq($scope.qs[0]);

    		}, function (err) {

    			if (err.data == null) {
    				$scope.message = 'Ocorreu um erro ao carregar a prova, entre em contato com o recrutador.';
    			} else {
    				$scope.message = err.data;
    				console.error('Ocorreu um erro', err.data);
    			}    			
    			//TODO redirect com a mensagem de erro err.data 

    		});

    	};

        $scope.setaq = function (q) {
			$scope.aq = q;
		}

        $scope.save = function () {
        	
        	var valid = true;
        	
        	for (var i = 0; i < $scope.qs.length; i++) {
        		
        		var q = $scope.qs[i];
        		
        		if (q.t == 1) {
        			if (q.a == null || q.a == '') {
        				valid = false;
        				break;
        			}        				
        		} else if (q.t == 2) {
        			if (q.c == null || q.c == '') {
        				valid = false;
        				break;
        			}
        		}
        	}
        	
        	if (!valid) {
        		Materialize.toast('Ainda existem perguntas pendentes, responda todas para finalizar a prova.', 10000, 'rounded');
        		console.log('Ainda existem perguntas pendentes, responda todas para finalizar a prova.');
        		return;
        	}
        		

        	TestService.save({qs: $scope.qs, t: $routeParams.token}, function (data) {
        		
        		console.log('Teste enviado com sucesso');
        		$scope.message = 'Teste enviado com sucesso';
        		$scope.qs = [];
        		$scope.aq = null;
        		//TODO redirect para pagina de pós orientacao com dados de data

        	}, function (err) {
        		
        		console.log('Ocorreu um erro', err.data);
        		$scope.message = err.data;

        	});

        };

        init();

    });

})();