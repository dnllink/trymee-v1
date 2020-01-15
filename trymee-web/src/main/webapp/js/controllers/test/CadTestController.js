(function () {
    'use strict';

    angular.module('tm').controller('CadTestController', function ($scope, $routeParams, LevelService, CategoryService, TestService, QuestionService, ngIntroService) {
    	
    	var index;
    	
    	function init () {

            $scope.test = {};
            
            ngIntroService.refresh();
            
            $scope.availableCategories = [];
            $scope.selectedCategories = [];
            
            $scope.selectedQuestions = [];
            $scope.availableQuestions = [];
            
            $scope.searchCategoryText = '';
            $scope.searchQuestionText = '';

            LevelService.query({},function (data) {
            	$scope.levels = data;
            }, function (err) {
            	console.error('Erro ao carregar niveis.', err);
            });
            
            if ($routeParams.process) {
            	console.log('processo: ' + $routeParams.process);
            	$scope.test.idProcess = $routeParams.process;
            }

    		if ($routeParams.id) {

    			TestService.get({id: $routeParams.id}, function (data) {

    				if(data){

    					$scope.test = data;
    					$scope.selectedQuestions = data.questions;
    					$scope.selectedCategories = data.categories;

    				} else {

    					Materialize.toast('Erro ao carregar a prova. Tente novamente.', 6000, 'rounded');

    				}

    			}, function (err) {

    				Materialize.toast('Erro ao carregar a prova. Tente novamente.', 6000, 'rounded');
    				console.error('Erro ao carregar a prova', err);

    			});

    		};

    	};
    	
        $scope.IntroOptions = {
    			steps : [{    	    		
    	    		intro: 'Aqui você pode criar ou alterar uma prova, preenchendo seus dados e associando as questões desejadas.'
    	    		
    	    	},{
    	    		element: document.querySelector('#cad-test-step2'),
    	    		intro: 'Preencha aqui o nome desejado para a prova, um nome curto que remeta ao tipo de profissional desejado. Exemplo: "Analista de Sistemas Pleno" ou "Desenvolvedor Java Junior".',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-test-step3'),
    	    		intro: 'Aqui você pode ter um descritivo um pouco mais detalhado da prova. Por exemplo "Foco em determinado framework, vivência com tal metodologia".',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-test-step4'),
    	    		intro: 'Aqui você escolhe qual o nível de conhecimento esperado para o conjunto de questões a ser selecionado.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-test-step5'),
    	    		intro: 'Aqui você pode selecionar diversas categorias para sua prova, ou seja, os assuntos abordados nas questões.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-test-step6'),
    	    		intro: 'Aqui você digitará os termos para a busca das questões a serem selecionadas para a prova.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-test-step7'),
    	    		intro: 'Aqui você pode verificar todas as questões já selecionadas para a prova, e excluir alguma questão da lista clicando no ícone do do lado direito da questão desejada.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-test-step8'),
    	    		intro: 'Após o preenchimento das informações necessárias, clique aqui para salvar sua prova.',
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

        $scope.clearForm = function () {

            $scope.test = {};

        };

        $scope.save = function () {
            
            if($scope.form.$valid){
            	
            	/*
            	if ($scope.selectedCategories.length > 0) {
            		
            		$scope.test.categories = $scope.selectedCategories; 
            		
            	} else {
            		
            		Materialize.toast('Selecione pelo menos uma categoria para a prova.', 6000, 'rounded');
            		return;
            		
            	}
            	*/
            	
            	$scope.test.questions = $scope.selectedQuestions; 

            	if ($scope.test.id) {
            		
                	TestService.update($scope.test, function (data) {
                		
                		Materialize.toast('Prova atualizada com sucesso.', 6000, 'rounded');
                        $scope.test = {};

                        $scope.availableCategories = [];
                        $scope.selectedCategories = [];
                        
                        $scope.selectedQuestions = [];
                        $scope.availableQuestions = [];
                        
                        $scope.searchCategoryText = '';
                        $scope.searchQuestionText = '';

                	}, function (err) {
                		console.error('Ocorreu um erro ao gravar a alteracao.', err);
                		Materialize.toast('Ocorreu um erro, tente novamente.', 6000, 'rounded');
                	});
            		
            	} else {
            		
                	TestService.save($scope.test, function (data) {
                		
                		Materialize.toast('Prova salva com sucesso.', 6000, 'rounded');
                        $scope.test = {};

                        $scope.availableCategories = [];
                        $scope.selectedCategories = [];
                        
                        $scope.selectedQuestions = [];
                        $scope.availableQuestions = [];
                        
                        $scope.searchCategoryText = '';
                        $scope.searchQuestionText = '';

                	}, function (err) {
                		console.error('Ocorreu um erro ao gravar a alteracao.', err);
                		Materialize.toast('Ocorreu um erro, tente novamente.', 6000, 'rounded');
                	});
            		
            	};
            	
            };

        };
        
        $scope.searchCategories = function () {

            CategoryService.query({searchText: $scope.searchCategoryText},function (data) {
            	$scope.availableCategories = data;
            }, function (err) {
            	console.error('Erro ao carregar categorias.', err);
            	Materialize.toast('Ocorreu um erro, tente novamente.', 6000, 'rounded');
            });

        };
        
        $scope.searchQuestions = function () {

        	QuestionService.query({searchText: $scope.searchQuestionText}, function (data) {
        		$scope.availableQuestions = data;
        	}, function (err) {
        		console.error('Erro ao carregar questoes.', err);
        		Materialize.toast('Ocorreu um erro, tente novamente.', 6000, 'rounded');
        	});

        };
        
        $scope.selectQuestion = function (question) {
        	
        	moveElement(question, $scope.availableQuestions, $scope.selectedQuestions);
        	
        };

        $scope.selectCategory = function (category) {

            moveElement(category, $scope.availableCategories, $scope.selectedCategories);

        };

        $scope.removeCategory = function (category) {

            moveElement(category, $scope.selectedCategories, $scope.availableCategories);

        };
        
        $scope.removeQuestion = function (question) {
        	
        	moveElement(question, $scope.selectedQuestions, $scope.availableQuestions);
        	
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