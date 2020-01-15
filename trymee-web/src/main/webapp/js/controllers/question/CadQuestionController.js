(function () {
    'use strict';

    angular.module('tm').controller('CadQuestionController', function ($scope, $routeParams, LevelService, CategoryService, QuestionService, ngIntroService) {
    	
    	var index;
    	
    	function init () {
    		
            $scope.alternatives = [];
            $scope.question = {};
            index = 0;
            
            ngIntroService.refresh();

            CategoryService.query({},function (data) {
            	$scope.categories = data;
            }, function (err) {
            	console.error('Erro ao carregar categorias.', err);
            });

            LevelService.query({},function (data) {
            	$scope.levels = data;
            }, function (err) {
            	console.error('Erro ao carregar niveis.', err);
            });

            $scope.tipos = [{
                value: 'MULTIPLA_ESCOLHA',
                name: 'Múltipla Escolha'
            }, {
                value: 'DISCURSIVA',
                name: 'Discursiva'
            }];

    		if ($routeParams.id) {

    			QuestionService.get({id: $routeParams.id}, function (data) {

    				if(data){

    					$scope.question = data;
    					if(data.alternatives) {
    						
    						var altIndex = 1;
    						
    						data.alternatives.forEach(function (alt) {
    							
    							var alternative = {
    	    		            		description: alt.description,
    	    		            		correct: alt.correct,
    	    		            		id: alt.id,
    	    		            		idCheck: 'idCheck' + altIndex
    	    		            };
    							
    							$scope.alternatives.push(alternative);
    							
    							altIndex++;
    							
    						});

    					}

    				} else {

    					Materialize.toast('Erro ao carregar a questão. Tente novamente.', 6000, 'rounded');

    				}

    			}, function (err) {

    				Materialize.toast('Erro ao carregar a questão. Tente novamente.', 6000, 'rounded');
    				console.error('Erro ao carregar a questão', err);

    			});

    		};

    	};
    	
        $scope.IntroOptions = {
    			steps : [{
    	    		intro: 'Aqui você pode cadastrar ou alterar uma questão que será utilizada nas provas enviadas aos seus candidatos.'
    	    	},{
    	    		element: document.querySelector('#cad-question-step2'),
    	    		intro: 'Preencha aqui o enunciado da questão.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-question-step3'),
    	    		intro: 'Aqui você pode indicar se a questão é será do tipo "Múltipla escolha" - em que seu candidato escolhera dentre várias opções a correta. Ou "Dissertativa" - onde seu candidato digitará o texto da resposta. Lembre-se que as questões de "Múltipla escolha" são corrigidas automaticamente quando seu candidato finaliza a prova, enquanto que as "Dissertativas" terão de ser corrigidas manualmente após a finalização da prova.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-question-step4'),
    	    		intro: 'Selecione aqui o nível de dificuldade proposto pela questão. Mantenha um critério para essa seleção, pois facilitará a criação de provas consistentes e correções coerentes no decorrer de seu processo seletivo.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#cad-question-step5'),
    	    		intro: 'A categoria selecionada se refere ao tema da questão. Igualmente ao nível, é importante que essa seleção tenha um critério bem definido.',
    	    		position: 'bottom'
    	    	},{
    	    		intro: 'Caso sua questão seja do tipo "Múltipla escolha", você deve clicar no botão "Inserir" para adicionar as opções de resposta disponíveis para o candidato. Insira no mínimo 2 alternativas, e selecione apenas uma como a resposta correta. Recomendamos de 4 a 6 alternativas, para manter um nível de dificuldade coerente.'
    	    	},{
    	    		intro: 'Já se sua questão for do tipo "Discursiva", você pode inserir uma resposta esperada. Esta resposta esperada é uma base, uma opção correta, porém neste caso a correção é manual, portanto seu candidato pode responder de forma diferente e ainda assim acertar.'
    	    	},{
    	    		element: document.querySelector('#cad-question-step8'),
    	    		intro: 'Após o preenchimento das informações, basta clicar aqui para salvar sua questão.',
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

        $scope.addAlternative = function () {
        	
        	index++;

            var alternative = {
            		description: '',
            		correct: false,
            		idCheck: 'idCheck' + index
            		};

            $scope.alternatives.push(alternative);

        };

        $scope.clearForm = function () {

            $scope.alternatives = [];
            $scope.question = {};

        };

        $scope.save = function () {
            
            if($scope.form.$valid){
            	
            	if($scope.question.type == 'MULTIPLA_ESCOLHA') {
            		
            		var alts = [];
            		
            		$scope.alternatives.forEach(function (value) {
            			
            			var alt = {
            					description: value.description,
                        		correct: value.correct,
                        		id: value.id ? value.id : null
            			};
            			
            			alts.push(alt);
            			
            		});
            		
            		$scope.question.alternatives = alts;
            		
            		if ($scope.question.alternatives.length < 2) {
            			Materialize.toast('Preencha pelo menos 2 alternativas para sua questão.', 6000, 'rounded');
                    	return;
            		}

                    var error = {};

                    //valida alternativas válidas
                    if ($scope.question.alternatives && $scope.question.alternatives.length > 0) {

                        var haveIsCorrect = $scope.question.alternatives.filter(function (alt) {
                            return alt.correct == true;
                        });

                        if (haveIsCorrect.length == 0) {

                        	Materialize.toast('Marque pelo menos uma das alternativas como correta.', 6000, 'rounded');
                            return;

                        } else if (haveIsCorrect.length > 1) {
                        	
                        	Materialize.toast('Marque apenas uma das alternativas como correta.', 6000, 'rounded');
                            return;
                        	
                        }

                        var allHaveText = $scope.question.alternatives.filter(function (alt) {
                            return alt.description == null || alt.description == '';
                        });

                        if (allHaveText.length > 0) {

                        	Materialize.toast('Preencha os textos de todas as alternativas.', 6000, 'rounded');
                            return;

                        }

                    }


            	} else {
            		
            		if (!$scope.question.answer ||  $scope.question.answer == '') {
            			
            			Materialize.toast('Preencha o texto da resposta.', 6000, 'rounded');
            			return;
            			
            		}
            		
            	}
            	
            	if ($scope.question.id) {
            		
                	QuestionService.update($scope.question, function (data) {
                		
                		Materialize.toast('Questão atualizada com sucesso.', 6000, 'rounded');
                        $scope.alternatives = [];
                        $scope.question = {};
                        index = 0;
                		
                	}, function (err) {
                		console.error('Ocorreu um erro ao gravar a alteracao.', err);
                		Materialize.toast('Ocorreu um erro, tente novamente.', 6000, 'rounded');
                	});
            		
            	} else {
            		
                	QuestionService.save($scope.question, function (data) {
                		
                		Materialize.toast('Questão salva com sucesso.', 6000, 'rounded');
                        $scope.alternatives = [];
                        $scope.question = {};
                        index = 0;
                		
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