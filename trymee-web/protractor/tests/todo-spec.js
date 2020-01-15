describe('Cadastro de candidato', function() {
	
	var qtCadastros = 151;
	
	it('deve logar na aplicação', function() {
	  
		browser.get('https://app.trymee.com.br/trymee-web/#/login');

		element(by.model('data.username')).sendKeys('dnllink@hotmail.com');
		element(by.model('data.password')).sendKeys('Senh@2017');

		element(by.className('btn')).click();

		expect(element(by.model('description')).isPresent()).toBe(true);

	});
  
	it('deve cadastrar 150 candidatos', function() {
	  
		browser.get('https://app.trymee.com.br/trymee-web/#/pesq-candidate');

		element(by.className('btn-floating')).click();

		for (var i = 1; i < qtCadastros; i++) {
			
			var nome = 'Candidato Teste' + i;
			var email = 'teste' + i + '@tm.com'
			
			element(by.model('candidate.name')).sendKeys(nome);
			element(by.model('candidate.email')).sendKeys(email);

			if (i != 1) {
				element(by.css("label[for='checkActive']")).click();
			}

			var form = element(by.name('form'));
			form.submit();
			
		}

		expect(element(by.className('card-panel')).getText()).toBe('Candidato salvo com sucesso.');

	});
});

