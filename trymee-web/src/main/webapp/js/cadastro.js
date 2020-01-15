$(document).ready(function() {

    $('#cadForm').submit(function(event) {
    	
    	if (validateForm()) {
    		
            var formData = {
                    'name': $('input[name=name]').val(),
                    'email': $('input[name=email]').val(),
                    'tel': $('input[name=tel]').val(),
                    'pass': $('input[name=pass]').val()
                };

                $.ajax({
                    type: 'POST',
                    url: '/trymee/api/users/free',
                    data: formData,
                    dataType: 'text',
                    encode: true,
                    success: function(result,status,xhr) {
                    	var data = result.split(' ');
                    	window.sessionStorage.setItem('localTokenTM', data[0]);
                    	window.location.href = '/trymee-web/#/summary-process/' + data[1] + '?faParam=true';
                    },
                    error: function (result,status,xhr) {
                    	Materialize.toast(result.responseText, 6000, 'rounded');
                    }
                });

                event.preventDefault();
    	} else {
    		event.preventDefault();
    	}

    });

});

function validateForm() {

	var aceite = $('#filled-in-box')[0].checked;
	var message = '';

	if (aceite) {
		
		var str = document.forms['cadForm']['pass'].value;
		message = checkPwd(str);		

	} else {
		
		message = 'É necessário aceitar os termos de uso para concluir o cadastro.';
		
	}
	
	if (message.length == 0) {
		return true;
	} else {
		Materialize.toast(message, 4000, 'rounded');
		return false;
	}
		


}