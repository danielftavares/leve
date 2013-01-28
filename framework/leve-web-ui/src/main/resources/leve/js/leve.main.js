Leve = {
	frames: [],
	frameCount : 1,
	FT_CAD : 1,
	FT_FIND: 2,
	FT_LOOKUP: 3,
	openFrame: function(url, frame_type, data, load_callback_function){
		$('iframe.sm_frames').hide();
		this.frameCount++;
		var frameId = 'leve-main-frame-'+this.frameCount ;
			
		var f = $('<iframe />', {
		   'src': url+"?frame_id="+frameId+"&frameType="+frame_type,
		   'frameborder': 0,
		   'id': frameId,
		   'class': 'sm_frames row-fluid'
		});
		
		var frameData = {
			el: f,
			id: frameId,
			frame_type: frame_type
		};
		this.frames.push(frameData);
		
		f.appendTo('#sm_content');
		f.load(function(e) {
			e.target.contentWindow.LeveModule.loadModule(data);
			e.target.contentWindow.LeveModule.frame_type = frame_type;
			if(load_callback_function){
				var fname = "leve_temp_function_"+Math.round (Math.random()*100000);
				e.target.contentWindow[fname] = load_callback_function;
				e.target.contentWindow[fname](data);
			}
		});
	},
	get_frame_by_id: function(frame_id){
		return jQuery.grep( this.frames, function(f){
			return f.id = frame_id
		})[0];
	},
	removeFrame : function(frame_id){
		if(frame_id){
			var last_frame_data = this.get_frame_by_id(frame_id);
			
			
			this.frames = jQuery.grep(this.frames, function(frame) {
					return frame != last_frame_data;
			 });
			
			last_frame_data.el.remove();
			this.frames[this.frames.length - 1].el.show();
			
		} else {
			var last_frame_data = this.frames.pop();
			last_frame_data.el.remove();
			if(this.frames.length > 0){
				this.frames[this.frames.length - 1].el.show();
			}
		}
	},
	removeAllFrames : function(){
		while(this.frames.length > 0){
			this.removeFrame();
		}
	},
	return_value : function(data){
		var topframe = this.get_top_frame();
		
		// return a find register, then open edit register
		if(topframe.frame_type == this.FT_FIND){
			// TODO melhorar esse replace.
//			this.openFrame( topframe.el.contents()[0].location.href.replace('find','cad'), this.FT_CAD, data);
			this.removeFrame();
			var topframe = this.get_top_frame();
			topframe.el[0].contentWindow.LeveModule.loadModule(data);
		} else if (topframe.frame_type == this.FT_CAD){
			this.removeFrame();
		} else if (topframe.frame_type == this.FT_LOOKUP){
			this.removeFrame();
			var new_top_frame = this.get_top_frame();
			new_top_frame.el[0].contentWindow.LeveModule.lookup_return(data);
		}
	
	
	/*
	 * var frame = this.get_frame_by_id(frame_id); this.removeFrame(frame_id);
	 * if(for_frame_id){ var for_frame = this.get_frame_by_id(for_frame_id);
	 * if(for_frame.frame_type == this.FT_FIND){ for_frame.entity = entity; var
	 * $jqFrame = for_frame.el.contents()[0].defaultView.$; $jqFrame.each(val,
	 * function(name,value){ $jqFrame('#'+name).val(value); }); //AQUI!!!!!!! }
	 * else if(for_frame.frame_type == this.FT_LOOKUP){ } }
	 */
	},
	
	find: function(frame_id){
		var frame = this.get_frame_by_id(frame_id);
		this.openFrame(frame.el.contents()[0].location.href.split("?").slice(0,-1).toString().replace("Cad", "Find"), 
						Leve.FT_FIND,'for='+frame_id);
	},
	openLookup: function(url){
		Leve.openFrame(url, Leve.FT_LOOKUP);
	},
	
	get_top_frame: function(){
		return this.frames[frames.length - 1];
	},
	// lookup functions
	completeByKey: function(keyEl, url, fillFunction){
		
		if($(keyEl).val()){
			$.getJSON(url+$(keyEl).val(), function(data) {
				fillFunction(data);
			})
			.complete(function(data, status) { 
				if(data.status == 204){ // no content
					fillFunction();
				}
			});
		} else {
			fillFunction();
		}
		
		
	}
	
};
	
$(function(){

	$("#sm_menu li a").click(function(event) {
		event.stopImmediatePropagation();
		Leve.openFrame($(this).attr('href'), Leve.FT_CAD);
		return false;
	});


});