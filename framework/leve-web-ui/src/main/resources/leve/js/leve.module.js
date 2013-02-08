$(function() {
	Leve = window.parent.Leve;

	LeveModule = {
		submitForm : function(f) {
			// alert('Error! implement LeveModule.submitForm in your page');
		},
		loadModule : function(obj) {
			if (obj) {
				if ($.type(obj) == 'object') {
					var id_obj = obj[$('form.leve_module_form').data(
							"id-property")];

					$.getJSON($('form.leve_module_form').attr("action") + "/"
							+ id_obj, function(data) {
						if (!reciveError(data)) {
							if (LeveModule.frame_type == Leve.FT_LOOKUP) {
								Leve.return_value(data);
							} else {
								js2form($('form')[0], data);
								$('.leve-decimal').maskMoney('mask');
							}
						}
					});
				}
				// alert('Error! implement LeveModule.openModule in your page');
			} else {
				$('form.leve_module_form').each(function() {
					this.reset();
					$(this).find(":hidden:not(option)").val('');
				});
			}
		},
		remove : function() {
			var id_obj = $('#' + $('form.leve_module_form').data("id-property"))
					.val();

			$.getJSON(
					$('form.leve_module_form').attr("action") + "/delete/"
							+ id_obj,
					function(data) {
						if (!data) {
							LeveModule.loadModule();
							showSuccessMsg($('form.leve_module_form').data(
									"success-delete-msg"));
						} else {
							!reciveError(data);
						}
					}).complete(
					function(data, status) {
						if (data.status == 204) { // no content
							LeveModule.loadModule();
							showSuccessMsg($('form.leve_module_form').data(
									"success-delete-msg"));
						}
					});
			;
		},
		lookup_return : function(obj) {
			// alert('Error! implement LeveModule.lookup_return in your page');
		},
		save_callback : function(obj) {
			// alert('Error! implement LeveModule.lookup_return in your page');
		},
	};

	$('input.leve-uppercase').keyup(function() {
		this.value = this.value.toLocaleUpperCase();
	});

	$("#new").click(function() {
		if (LeveModule.frame_type == Leve.FT_CAD) {
			LeveModule.loadModule();
		} else if (LeveModule.frame_type == Leve.FT_FIND) {
			Leve.removeFrame();
			Leve.get_top_frame().el[0].contentWindow.LeveModule.loadModule();
		}

	});
	$("#save").click(function() {
		$('form').submit();
	});
	$("#delete").click(function() {
		LeveModule.remove();
	});
	$(".leve-command-bar #find").click(function() {
		Leve.find($(document).getUrlParam("frame_id"));
	});

	$(".leve-action-bar #find").click(function() {
		$('.datagrid .search').click();
	});
	$("#close").click(function() {
		Leve.removeFrame();
	});

	$("#closeall").click(function() {
		Leve.removeAllFrames();
	});

	// limpa
	$('.leve-lookup input')
			.keydown(
					function(e) {
						var pattern = /\w/;
						if (pattern.test(String.fromCharCode(e.which))
								|| e.keyCode == 8 || e.keyCode == 32
								|| e.keyCode == 27) {
							$(this).parents('.leve-lookup').find(
									'input[id!="' + $(this).attr('id') + '"]')
									.val('');
						}
						if (e.keyCode == 27) {
							$(this).val('')
						}
					});

	$('.leve-lookup input.leve-lookup-desc').blur(
			function() {
				if ($(this).parents('.leve-lookup')
						.find('input.leve-lookup-id').val() == '') {
					$(this).val('');
				}
			});

	$('.leve-lookup button.leve-lookup-find-btn').click(
			function() {
				var url = $(this).parents('div.leve-lookup').data('find-url');
				LeveModule.lookup_return = window[$(this).parents(
						'div.leve-lookup').data('function-fill')];
				Leve.openLookup(url, $(document).getUrlParam("frame_id"));
				return false;
			});

	$('.leve-lookup button.leve-lookup-cad-btn').click(
			function() {
				var url = $(this).parents('.leve-lookup').data('cad-url');
				LeveModule.lookup_return = window[$(this).parents(
						'div.leve-lookup').data('function-fill')];
				Leve.openLookup(url, $(document).getUrlParam("frame_id"));
				return false;
			});

	// load select
	$('select.leve-select-bean').each(function() {
		var select = $(this);
		var action = select.data('bean-action');
		$.getJSON(servlet_path + "/leve/" + action + "/list", function(resp) {
			$(resp).each(function(){
				select.append($('<option>', { value : this[select.data('bean-id')] }).text(this[select.data('bean-desc')]));
			});
		});
	});

	$('.datepicker').datepicker();

});
function loadTable(table, data) {

	var attributes_name = [];
	table.find('th').each(function(i, el) {
		attributes_name.push($(el).data('attribute'));
	});

	$.each(data, function(i, row) {
		var rowel = $('<tr>').appendTo(table);
		$.each(attributes_name, function(j, col) {
			$('<td>').text(row[col]).appendTo(rowel);
		});
	});

}