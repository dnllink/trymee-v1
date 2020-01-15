function checkPwd(str) {
    if (str.length < 8) {
        return('A senha não contém 8 caracteres');
    } else if (str.length > 16) {
        return('A senha contém mais de 16 caracteres');
    } else if (str.search(/\d/) == -1) {
        return('A senha não contém números');
    } else if (str.search(/[a-zA-Z]/) == -1) {
        return('A senha não contém letras');
    } else if (str.search(/[^a-zA-Z0-9\!\@\#\$\%\^\&\*\(\)\_\+]/) != -1) {
        return('A senha contém caracteres inválidos');
    }
    return('');
}

function noResultforSearch(scope, data) {
	
	if (data.length < 1)
		Materialize.toast('Nenhum resultado encontrado.', 6000, 'rounded');
	
}