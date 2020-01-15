(function() {
    'use strict';

    angular.module('tm').controller('CadProcessController', function($scope, $routeParams, $location, ProcessService, TestService, CandidateService, ngIntroService){
        
        var init = function () {

            $scope.searchTest = '';
            $scope.searchCandidate = '';
            
            ngIntroService.refresh();
            
            $scope.process = {
                active: true,
                test: null,
                candidates: [],
                links: []
            };

            $scope.selectedTest = null;
            $scope.availableTests = [];
            $scope.availableCandidates = [];
            $scope.selectedCandidates = [];
            
            if ($routeParams.id) {

    			ProcessService.get({id: $routeParams.id}, function (data) {

    				if(data){
    					
    					var dtProcess = data.startDate.split('-');
    					
    					var dtConverted = dtProcess[2] + '/' + dtProcess[1] + '/' + dtProcess[0];
    					
    					data.startDate = dtConverted; 

    					$scope.process = data;
    					$scope.selectedTest = data.test;
    					
    					if (data.candidates) {
    						$scope.selectedCandidates = data.candidates;
    					} else {
    						$scope.selectedCandidates = [];
    					}

    				} else {

    					Materialize.toast('Erro ao carregar o processo. Tente novamente.', 6000, 'rounded');

    				}

    			}, function (err) {

    				Materialize.toast('Erro ao carregar o processo. Tente novamente.', 6000, 'rounded');
    				console.error('Erro ao carregar a prova', err);

    			});

    		};

        };
        
        $scope.IntroOptions = {
    			steps : [{
    	    		//element: document.querySelector('#cad-process-step1'),
    	    		intro: 'Aqui você pode criar ou alterar um processo seletivo, selecionando a prova e os candidatos envolvidos.'//,
    	    		//position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-proc-step1'),
    	    		intro: 'Aqui você deve preencher um descritivo de seu processo seletivo, que contenha todas as informações necessárias para sua identificação posterior. Exemplo "Processo para vaga de desenvolvedor Java Jr para a equipe de desenvolvimento do cliente XPTO. Solicitado por Fulano, em 01/01/2017".',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-proc-step2'),
    	    		intro: 'Selecione aqui a data de início do processo seletivo, ou seja, quando efetivamente começou ou começará o processo. Usualmente é a data atual.',
    	    		position: 'top'
    	    	},{
    	    		element: document.querySelector('#cad-proc-step3'),
    	    		intro: 'Digite aqui termos para buscar a prova desejada para associar ao processo seletivo. Este campo não é obrigatório, você pode criar o processo sem prova e posteriormente editar para adicionar a prova.',
    	    		position: 'top'
    	    	},{
    	    		element: document.querySelector('#cad-proc-step4'),
    	    		intro: 'Digite aqui os termos para buscar os candidatos que participarão do processo. Este campo também não é obrigatório, você pode criar o processo sem candidatos e editar o mesmo para associar candidatos depois.',
    	    		position: 'top'
    	    	},{
    	    		element: document.querySelector('#cad-proc-step5'),
    	    		intro: 'Após o preenchimento das informações, basta clicar aqui para salvar seu processo seletivo.',
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

        $scope.save = function () {
        	
        	if ($scope.form.$valid) {
                
                if ($scope.selectedTest != null && $scope.selectedTest.id != null) {
                    $scope.process.test = $scope.selectedTest;
                };
                
                if ($scope.selectedCandidates != null && $scope.selectedCandidates.length > 0) {
                    $scope.process.candidates = $scope.selectedCandidates;
                };
                
                $scope.process.strStartDate = $scope.process.startDate;
                
                if ($scope.process.id){
                	
                    ProcessService.update($scope.process, function (data) {

                        Materialize.toast('Processo atualizado com sucesso.', 6000, 'rounded');
                        $scope.searchTest = '';
                        $scope.searchCandidate = '';
                        
                        $scope.process = {
                            active: true,
                            test: null,
                            candidates: [],
                            links: []
                        };

                        $scope.selectedTest = null;
                        $scope.availableTests = [];
                        $scope.availableCandidates = [];
                        $scope.selectedCandidates = [];

                    }, function(err){
                        
                        console.error('Erro ao salvar processo!', err);
                        Materialize.toast('Erro ao salvar processo, tente novamente.', 6000, 'rounded');
                        
                    });
                	
                } else {

                    ProcessService.save($scope.process, function (data) {

                        Materialize.toast('Processo salvo com sucesso.', 6000, 'rounded');
                        
                        $location.path('/summary-process/' + data.id);
                        
                        /*
                        $scope.searchTest = '';
                        $scope.searchCandidate = '';

                        $scope.process = {
                            active: true,
                            test: null,
                            candidates: [],
                            links: []
                        };

                        $scope.selectedTest = null;
                        $scope.availableTests = [];
                        $scope.availableCandidates = [];
                        $scope.selectedCandidates = [];
                        */

                    }, function(err){

                        console.error('Erro ao salvar processo!', err);
                        Materialize.toast('Erro ao salvar processo, tente novamente.', 6000, 'rounded');

                    });

                }

        	}

        };

        $scope.searchTests = function () {

        	TestService.query({searchText: $scope.searchTest}, function (data) {
                
                if (data != null && data.length > 0) {
                    
                    $scope.availableTests = data;
                    
                } else {
                    
                    Materialize.toast('Não foram encontradas provas com esse termo.', 6000, 'rounded');
                    $scope.availableTests = [];
                    
                };

        	}, function (err) {

        	});

        };

        $scope.searchCandidates = function () {

        	CandidateService.query({searchText: $scope.searchCandidate}, function (data) {

                if (data != null && data.length > 0) {

                    $scope.availableCandidates = data;

                } else {

                    Materialize.toast('Não foram encontrados candidatos com esse termo.', 6000, 'rounded');
                    $scope.availableCandidates = [];

                };

            }, function (err) {

        	});

        };
        
        $scope.selectTest = function (test) {
        	
        	$scope.selectedTest = test;
        	$scope.availableTests = [];
            
        };
        
        $scope.removeTest = function () {
        	
        	$scope.selectedTest = null;
        	
        };
        
        $scope.selectCandidate = function (candidate) {
        	
        	moveElement(candidate, $scope.availableCandidates, $scope.selectedCandidates);
            
        };
        
        $scope.removeCandidate = function (candidate) {
        	
        	moveElement(candidate, $scope.selectedCandidates, $scope.availableCandidates);
            
        };
        
        var moveElement = function(obj, from, to) {

            var i = 0;

            while(i < from.length) {

                var actual = from[i];

                if(obj.id == actual.id) {
                    
                    from.splice(i, 1);
                    to.push(actual);
                    break;
                }
                
                i++;
            }
        };
        
        init();

  });

})();