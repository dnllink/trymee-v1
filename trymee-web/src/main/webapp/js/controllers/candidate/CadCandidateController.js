(function () {
    'use strict';

    angular.module('tm').controller('CadCandidateController', function ($scope, $routeParams, CandidateService, ngIntroService) {
    	
    	var index;
    	
    	function init () {

            $scope.candidate = {};
            
            if ($routeParams.process) {
            	console.log('processo: ' + $routeParams.process);
            	$scope.candidate.processes = [];
            	var process = {'id': $routeParams.process};
            	$scope.candidate.processes.push(process);
            }
            
            ngIntroService.refresh();

    		if ($routeParams.id) {

    			CandidateService.get({id: $routeParams.id}, function (data) {

    				if(data){

    					$scope.candidate = data;

    				} else {

    					Materialize.toast('Erro ao carregar o candidato. Tente novamente.', 6000, 'rounded');

    				}

    			}, function (err) {

    				Materialize.toast('Erro ao carregar a candidato. Tente novamente.', 6000, 'rounded');
    				console.error('Erro ao carregar a candidato', err);

    			});

    		} else {
    			$scope.candidate.active = true;
    		}

    	};
    	
        $scope.IntroOptions = {
    			steps : [{
    	    		intro: 'Aqui você pode cadastrar ou alterar um candidato, que participará dos processos seletivos.'
    	    	      },{
    	    		element: document.querySelector('#cad-cand-step2'),
    	    		intro: 'Basta preencher o nome.',
    	    		position: 'bottom'
    	    	      },{
    	    		element: document.querySelector('#cad-cand-step3'),
    	    		intro: 'E o e-mail do seu candidato.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-cand-step4'),
    	    		intro: 'E clicar em "Salvar". Pronto! Seu candidato foi inserido e já pode ser associado a processos através da tela de "Processos"',
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

            $scope.candidate = {};

        };

        $scope.save = function () {

        	if($scope.form.$valid){

            	$scope.candidate.active = true;

            	if ($scope.candidate.id) {

            		CandidateService.update($scope.candidate, function (data) {

                		Materialize.toast('Candidato atualizado com sucesso.', 6000, 'rounded');
                        $scope.candidate = {};

                	}, function (err) {
                		console.error('Ocorreu um erro ao gravar a alteracao.', err);
                		Materialize.toast('Ocorreu um erro, tente novamente.', 6000, 'rounded');
                	});

            	} else {

            		CandidateService.save($scope.candidate, function (data) {

                		Materialize.toast('Candidato salvo com sucesso.', 6000, 'rounded');
                		
                        $scope.candidate = {};
                        $scope.candidate.active = true;
                        
                        if ($routeParams.process) {
                        	console.log('processo: ' + $routeParams.process);
                        	$scope.candidate.processes = [];
                        	var process = {'id': $routeParams.process};
                        	$scope.candidate.processes.push(process);
                        }

                	}, function (err) {
                		console.error('Ocorreu um erro ao gravar a alteracao.', err);
                		Materialize.toast('Ocorreu um erro, tente novamente.', 6000, 'rounded');
                	});
            		
            	};
            	
            };

        };
        
        init();

    });

})();