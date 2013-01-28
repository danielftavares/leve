/**
 * extrai valores dos campos de valor
 */
function getFloat(text) {
    var regex = new RegExp("\\d+|\\"+locale_decimal,"g"),
        matches,
        num = "";

    while(matches = regex.exec(text)) {
        num += matches[0];
    }
    num = num.replace(locale_decimal, "." );
    return parseFloat(num) || 0;
}

Object.byString = function(o, s) {
    s = s.replace(/\[(\w+)\]/g, '.$1'); // convert indexes to properties
    s = s.replace(/^\./, '');           // strip a leading dot
    var a = s.split('.');
    while (a.length) {
        var n = a.shift();
        if (n in o) {
            o = o[n];
        } else {
            return;
        }
    }
    return o;
}

function showSuccessMsg(msg){
	if($('#leve-success').length == 0){
		$('.leve-form-start').prepend('<div id=\'leve-success\' class="alert alert-block alert-success fade in"><a class="close" data-dismiss="alert" href="#">&times;</a><ul /></div>');
		$("#leve-success").alert();
	}
	$('#leve-success ul').append('<li>'+msg+'</li>');
	window.setTimeout(function() { $("#leve-success").alert('close'); }, 5000);
}

function showError(msg) {
	if($('#leve-errors').length == 0){
		$('.leve-form-start').prepend('<div id=\'leve-errors\' class="alert alert-block alert-error fade in"><a class="close" data-dismiss="alert" href="#">&times;</a><ul /></div>');
		$("#leve-errors").alert();
	}
	$('#leve-errors ul').append('<li>'+msg+'</li>');
}

function loadForm(bean){
	LeveModule.loadModule(bean);
}

function clearError() {
	$("#leve-errors").alert('close');	
}

function reciveError(lr){
	if (lr.leveError) {
		if (lr.errors) {
			$.each(lr.errors, function(index, value) {
				showError(value.localizedMessage);
				if(index == 0){
					$("#"+value.field).focus();
				}
			});
		} else {
			showError(lr.localizedMessage)
		}
		return true;
	} else {
		return false;
	}
}

function leve_base_submit(f, fcallback) {
	clearError();
	var fjson = $.stringifyJSON($(f).toObject());
	$.ajax({
		type : 'POST',
		url : $(f).attr("action"),
		data : fjson,
		contentType : "application/json",
		dataType : 'json',
		complete : function(response, textStatus) {
			var lr = jQuery.parseJSON(response.responseText);

			if ( !reciveError(lr)) {
				loadForm(lr);
				fcallback(lr);
				showSuccessMsg($(f).data('success-save-msg'));
			}
		}
	});
}
