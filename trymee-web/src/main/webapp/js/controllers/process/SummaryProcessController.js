(function() {
    'use strict';

    angular.module('tm').controller('SummaryProcessController', function($scope, $routeParams, $http, $timeout, LocationService, TestGradeService, TestService, ProcessService, ngIntroService){
        
        var init = function () {

            $scope.process = {};
            $scope.testGrades = [];
            $scope.tab = 0;
            
            ngIntroService.refresh();

            if ($routeParams.id) {
            	
            	$scope.processId = $routeParams.id;

            	$http.get(LocationService.getHost() + '/processes/summary/' + $routeParams.id).then(function (data) {
            		
            		$scope.process = data.data;
            		
            	}, function (err) {
            		
            		console.error(err.data);
            		
            	});

    		};
    		
    		$('.tooltipped').tooltip({delay: 50});

    		$timeout(function () {

    			if ($routeParams.faParam) {

    				$scope.getCandidates();

    				ngIntroService.setOptions({
        	    			steps : [{
        	    	    		intro: 'Bem vindo ao Trymee! Vamos mostrar como você vai enviar provas para seus candidatos e acompanhar o andamento do seu processo seletivo com rapidez e facilidade na nossa plataforma.'
        	    	    	},
        	    	    	{
        	    	    		intro: 'Esta é a principal visão da plataforma, aqui você tem acesso a todos os dados do seu processo seletivo, como a prova, a lista de candidatos, e principalmente o desempenhho de cada um dos seus candidatos que já respondeu a prova.'
        	    	    	},
        	    	    	{
        	    	    		intro: 'Aqui nós já temos um processo de exemplo cadastrado junto com uma prova.'
        	    	    	},
        	    	    	{
        	    	    		intro: 'Neste processo de exemplo, criamos um candidato com os seus dados, assim você vai poder receber o link da prova em seu email, e entender como funciona todo o processo.'
        	    	    	},
        	    	    	{
        	    	    		intro: 'Logo abaixo, na lista de candidatos, clique em "ENVIAR LINK".'
        	    	    		//element: document.querySelector('.uo-send-class'),
        	    	    		//position: 'bottom'
        	    	    	},
        	    	    	{
        	    	    		intro: 'Após enviar, confira em seu email, você deve receber em instantes uma mensagem com o link da prova. Caso não receba, confira se a mensagem não foi para a caixa de spam, as vezes acontece :('
        	    	    	},
        	    	    	{
        	    	    		intro: 'Após responder a prova, retorne aqui e confira o resultado :)'
        	    	    	}],
        	    			showStepNumbers : false,
        	    			showBullets : true,
        	    			exitOnOverlayClick : false,
        	    			exitOnEsc : false,
        	    			nextLabel : '>>',
        	    			prevLabel : '<<',
        	    			skipLabel : 'sair',
        	    			doneLabel : 'ok'
        	    		});

    				ngIntroService.start();
    			};

    		}, 1000);    		

    	};

    	$scope.showIntro = function () {
    		
    		ngIntroService.setOptions({
    			steps : [{
    	    		//element: document.querySelector('#summary-step1'),
    	    		intro: 'Este é o sumário do seu processo seletivo.'//,
    	    		//position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#summary-step1'),
    	    		intro: 'Este painel exibe o status atual do seu processo, desde a quantidade de candidatos envolvidos até a nota média das provas já respondidas. Basta posicionar o mouse sobre cada ícone para uma descrição detalhada do conteúdo exibido.',
    	    		position: 'bottom'
    	    	},{
    	    		element: document.querySelector('#summary-step2'),
    	    		intro: 'Clicando aqui você pode verificar todas as notas dos candidatos que já responderam a prova. Caso haja alguma "Correção parcial", você pode clicar no próprio link para efetuar a correção manual das questões dissertativas e finalizar a correção.',
    	    		position: 'top'
    	    	},{
    	    		element: document.querySelector('#summary-step3'),
    	    		intro: 'Clicando aqui você pode visualizar a prova que os candidatos respondem, porém com as respostas corretas já indicadas.',
    	    		position: 'top'
    	    	},{
    	    		element: document.querySelector('#summary-step4'),
    	    		intro: 'Clicando aqui você pode visualizar a lista de candidatos envolvidos no processo, verificar o status da prova(se já foi ou não enviada e respondida), e enviar ou reenviar cada link para a prova indivudualmente.',
    	    		position: 'top'
    	    	},{
    	    		element: document.querySelector('#summary-step5'),
    	    		intro: 'Aqui você tem os atalhos para: Finalizar processo/Reativar processo  - Permite finalizar o processo/Reativar um processo já finalizado. Enviar links - Envia ou reenvia todos os links pendentes de resposta para todos os candidatos do processo. Novo candidato - Redireciona para o cadastro de candidato, já associando ao processo ao salvar. Editar processo -Permite editar o processo, alterando os dados e adicionando/removendo prova e candidatos.',
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
    		});
    		
    		ngIntroService.start();
    	};

        $scope.sendLinks = function (idCandidate) {
        	
        	var url = LocationService.getHost() + '/processes/links?idProcess=' + $scope.processId + '&resend=true';
        	
        	if (idCandidate){
        		
        		url = url.concat('&idCandidate=' + idCandidate);
        		
        	};
        	
        	$http.post(url, {}).then(function (data) {
        		
        		console.log('Links enviados com sucesso.');
        		Materialize.toast('Links enviados com sucesso.', 6000, 'rounded');
        		$scope.getCandidates();
        		
        	}, function (err) {

        		console.error(err.data);
        		Materialize.toast(err.data, 6000, 'rounded');

        	});

        };

        $scope.getGrades = function () {
        	
        	TestGradeService.query({idProcess: $scope.processId}, function (data) {
        		
        		$scope.tab = 1;
        		
        		$scope.testGrades = data;
        		
        	}, function (err) {
        		
        		console.log(err);
        		
        	});
        	
        };

        $scope.getTest = function () {
        	
        	TestService.get({id: $scope.process.idTest}, function (data) {
        		
        		$scope.tab = 2;
        		
        		$scope.test = data;
        		
        	}, function (err) {
        		
        		console.log(err);
        		
        	});
        	
        };

        $scope.getCandidates = function () {
        	
        	var url = LocationService.getHost() + '/processes/candidates/' + $scope.processId;
        	
        	$http.get(url, {}).then(function (data) {        		

        		$scope.tab = 3;
        		
        		$scope.candidates = data.data;
        		
        	}, function (err) {

        		console.error(err.data);

        	});     

//        	ProcessService.get({id: $scope.processId}, function (data) {
//        		
//        		$scope.tab = 3;
//        		
//        		$scope.candidates = data.candidates;
//
//        	}, function (err) {
//        		
//        		console.log(err);
//
//        	});

        };
        
        $scope.finalizeProcess = function () {
        	
        	ProcessService.delete({'id': $scope.processId}, function (data) {
        		
        		Materialize.toast('Processo finalizado com sucesso.', 6000, 'rounded');
        		init();
        		
        	}, function (err) {
        		
        		Materialize.toast('Ocorreu um erro ao finalizar o processo.', 6000, 'rounded');
        		
        	});
        	
        };
        
        $scope.activateProcess = function () {
        	
        	var url = LocationService.getHost() + '/processes/activate/' + $scope.processId;
        	
        	$http.post(url, {}).then(function (data) {        		

        		Materialize.toast('Processo reativado com sucesso.', 6000, 'rounded');
        		init();
        		
        	}, function (err) {

        		console.error(err.data);
        		Materialize.toast('Ocorreu um erro ao reativar o processo. Tente novamente.', 6000, 'rounded');

        	});        	
       	
        };
        
        $scope.openLinkModal = function (data) {
        	
        	$scope.modal = data;
        	$('#link-modal').modal();
        	$('#link-modal').modal('open');
        	
        };

        init();

  });

})();