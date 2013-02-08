Leve = {
	frames: [],
	frameCount : 1,
	FT_CAD : 1,
	FT_FIND: 2,
	FT_LOOKUP: 3,
	openFrame: function(url, frame_type, options){
		options = options || {data: null, 
								load_callback_function: null, // callback -> excute after page load 
								parent: null //main frame
								};
		$('iframe.sm_frames').hide();
		
		var frameData = null;
		// if it`s a child frame, it will open anyway. 
		// If it`s not, i check if the frame url is open. 
		// if so it will show the last child of parent. if not the frame will open
		var breadcrumb = null;
		if(options.parent){
			frameData = this.__generate_frame__(url, frame_type);
			var parent = this.get_frame_by_id(options.parent);
			parent.child = frameData;
			frameData.parent = parent;
			breadcrumb = parent.el[0].contentWindow.$('.breadcrumb').html().replace('<h5>', '').replace('</h5>', '<span class="divider">/</span>')
		} else {
			var existing_frame = this.get_main_frame_by_url(url);
			if(existing_frame){
				this.move_frame_top(existing_frame);
				return;
			} else {
				frameData = this.__generate_frame__(url, frame_type);
				this.frames.push(frameData);
			}
		}
		
		frameData.el.appendTo('#sm_content');
		frameData.el.load(function(e) {
			if(breadcrumb){
				e.target.contentWindow.$('.breadcrumb li').before(breadcrumb);
			}
			e.target.contentWindow.LeveModule.loadModule(options.data);
			e.target.contentWindow.LeveModule.frame_type = frame_type;
			if(options.load_callback_function){
				var fname = "leve_temp_function_"+Math.round (Math.random()*100000);
				e.target.contentWindow[fname] = options.load_callback_function;
				e.target.contentWindow[fname](data);
			}
		});
	},
	__generate_frame__: function(url, frame_type){
		this.frameCount++;
		var frameId = 'leve-main-frame-'+this.frameCount ;
			
		var f = $('<iframe />', {
		   'src': url+"?frame_id="+frameId+"&frameType="+frame_type,
		   'frameborder': 0,
		   'id': frameId,
		   'class': 'sm_frames row-fluid'
		});
		
		var frameData = {
			url: url, 
			el: f,
			id: frameId,
			frame_type: frame_type,
			child: null,
			parent: null
		};
		return frameData;
	},
	move_frame_top: function(frame){
		this.frames.splice(this.frames.indexOf(frame),1);
		this.frames.push(frame);
		
		this.get_top_frame().el.show();
	},
	get_frame_by_id: function(frame_id){
		for(var i = 0; i<this.frames.length; i++){
			var f = this.frames[i];
			while(f){
				if(f.id == frame_id){
					return f;
				} else {
					f = f.child;
				}
			}
		}
		return null;
	},
	get_frame_by_url: function(frame_url){
		for(var i = 0; i<this.frames.length; i++){
			var f = this.frames[i];
			while(f){
				if(f.url == frame_url){
					return f;
				} else {
					f = f.child;
				}
			}
		}
		return null;
	},
	get_main_frame_by_url: function(frame_url){
		for(var i = 0; i<this.frames.length; i++){
			var f = this.frames[i];
			if(f.url == frame_url){
				return f;
			}
		}
		return null;
	},
	removeFrame : function(frame_id){
		var top_frame = this.get_top_frame();
		
		top_frame.el.remove();
		
		if(top_frame.parent){
			top_frame.parent.el.show();
			top_frame.parent.child = null;
		} else {
			this.frames = jQuery.grep(this.frames, function(frame) {
				return frame != top_frame;
			});
			if(this.frames.length > 0){
				this.get_top_frame().el.show();
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
						Leve.FT_FIND, {parent: frame_id});
	},
	openLookup: function(url,frame_id){
		Leve.openFrame(url, Leve.FT_LOOKUP,{parent: frame_id});
	},
	
	get_top_frame: function(){
		var top_frame = this.frames[this.frames.length - 1];
		while(top_frame.child){
			top_frame = top_frame.child;
		}
		return top_frame;
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