$(function() {
	Leve = window.parent.Leve;

	LeveModule = {
		frameId: $(document).getUrlParam("frame_id"), 
		submitForm : function(f) {
			// alert('Error! implement LeveModule.submitForm in your page');
		},
		loadModule : function(obj) {
			// clear many to many
			$('.leve-select-many .leve-select-right button').each(function(){
				var opts = $(this).parents('.leve-select-right').find('select option').remove();
				$(this).parents('.leve-select-many').find('.leve-select-left select').append(opts);
			});
			
			if (obj) {
				var loadFinction = function(data) {
					if (!reciveError(data)) {
						if (LeveModule.frame_type == Leve.FT_LOOKUP) {
							Leve.return_value(data);
						} else {
							js2form($('form')[0], data);
							$('.leve-decimal').maskMoney('mask');
						}
					}
				}
				
				var id_obj = null;
				if ($.type(obj) == 'object') {
					id_obj = obj[$('form.leve_module_form').data(
							"id-property")];
				}else {
					id_obj = obj;
				}
				$.getJSON($('form.leve_module_form').attr("action") + "/"
						+ id_obj, loadFinction);
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
		if (LeveModule.frame_type == Leve.FT_FIND) {
			Leve.removeFrame();
			Leve.get_top_frame().el[0].contentWindow.LeveModule.loadModule();
		} else {
			LeveModule.loadModule();
		}

	});
	$("#save").click(function() {
		$('form').submit();
	});
	$("#delete").click(function() {
		LeveModule.remove();
	});
	$(".leve-command-bar #find").click(function() {
		Leve.find(LeveModule.frameId);
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
				Leve.openLookup(url, LeveModule.frameId);
				return false;
			});

	$('.leve-lookup button.leve-lookup-cad-btn').click(
			function() {
				var url = $(this).parents('.leve-lookup').data('cad-url');
				LeveModule.lookup_return = window[$(this).parents(
						'div.leve-lookup').data('function-fill')];
				Leve.openLookup(url, LeveModule.frameId);
				return false;
			});
	
	
	
	//evento para o key
	$('.leve-lookup .leve-lookup-key').blur(function() {
		var $divLookup = $(this).parents('.leve-lookup:first');
		Leve.completeByKey(this, $divLookup.data('find-key-url'), window[$divLookup.data('function-fill')]);
	});
	
	

	$('.leve-lookup .leve-lookup-desc').typeahead({
	    items: 10,
	    highlighter : function(item){
	      var itemVal = $.parseJSON(item);
	      var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\$&');
	      
	      var $divLookup =  this.$element.parents('.leve-lookup:first');
	      
	      var key_att = $divLookup.data('field-key');
	      var desc_att = $divLookup.data('field-desc');
	      
	      var key_val = key_att ? itemVal[key_att] : null;
	      var desc_val = itemVal[desc_att];
	      var label = key_val;
	      label = label ? label + ' - ' + desc_val : desc_val;
	      return ( label ).replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
	        return '<strong>' + match + '</strong>';
	      })
	    },
	    matcher: function (item) {return 1;},
	    sorter: function (items) {return items},
	    updater: function (item) { 
	    	var data = $.parseJSON(item);
	    	
	    	var $divLookup =  this.$element.parents('.leve-lookup:first');
	    	window[$divLookup.data('function-fill')](data)
	    	return data[$divLookup.data('field-desc')]; 
	    }, 
	    source: function (query, callback) {
	    	var $divLookup = this.$element.parents('.leve-lookup:first');
	    	
	    	//use jquery parent window. cant block ui
	        return window.parent.$.getJSON($divLookup.data('autocomplete-url')+'?desc='+query, function (data) {
	    		var return_list = [];
	    		$.each(data, function(key, val) {
	    			return_list.push( $.stringifyJSON(val) );
	    		});
	            return callback(return_list);
	        });
	    }
	});

	$('.datepicker').datepicker();
	
	
	//grid start
	$('.leve-tablecolldef').each(function(){
		$(this).appendTo('#leve-findmodal_' + $(this).data('tableid') +' .modal-body');
	});
	
	$('.leve-modalfind .leve-filter').click(function(){
		$('#'+$(this).data('tableid') + ' .search').click();
		$(this).prev().click();
	});
	//grid end
	
	
	//many to many start
	//add
	$('.leve-select-many .leve-select-left button').click(function(){
		var opts = $(this).parents('.leve-select-left').find('select option:selected').remove();
		$(this).parents('.leve-select-many').find('.leve-select-right select').append(opts);
		return false;
	});
	//remove
	$('.leve-select-many .leve-select-right button').click(function(){
		var opts = $(this).parents('.leve-select-right').find('select option:selected').remove();
		$(this).parents('.leve-select-many').find('.leve-select-left select').append(opts);
		return false;
	});
	//many to many end
	
	// mount goto start
	var listgoto = $('form .leve-goto-item').detach();
	if(listgoto.length){
		var btnGroup = $('.leve-command-bar').append('<button class="btn dropdown-toggle" data-toggle="dropdown">'+gotoLabel+' <span class="caret"></span></button><ul class="dropdown-menu"></ul>');
		btnGroup.find('ul').append(listgoto);
		listgoto.each(function(){
			$(this).find('a').click(function(){
				var url = $(this).data('page');
				var idVal = $(document.getElementById($(this).data('id'))).val();
				if(idVal){
					Leve.open_goto(url, idVal, LeveModule.frameId);
				} else {
					console.log('no data!!');
				}
			});
		});
	}
	// mount goto end
	
	//block ajax
	
	$(document).ajaxStart(function(a,b,c,d){
		$.blockUI({ message: " ",
			css: {
				padding:	0,
				margin:		0,
				width:		'16px',
				height:		'16px',
				top:		'40%',
				left:		'35%',
				textAlign:	'center',
				cursor:		'wait',
				border:		'none',
				backgroundColor:'none',
			},
			overlayCSS:  {
				backgroundColor:	'#fff',
				opacity:			0.6,
				cursor:				'wait'
			}
		});
	});
	
	$(document).ajaxStop($.unblockUI);
	//end block ajax

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