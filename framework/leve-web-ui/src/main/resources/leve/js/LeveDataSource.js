var LeveDataSource = function (options) {
        this._formatter = options.formatter;
        this._columns = options.columns;
        this._delay = options.delay || 0;
        this._data = options.data;
        this._formid = options.formid;
};

LeveDataSource.prototype = {
        /**
         * Returns stored column metadata
         */
        columns: function () {
                return this._columns;
        },
        
        data: function (options, callback) {
        		if(!options.search){
        			callback({ data: [], start: 0, end: 0, count: 0, pages: 0, page: 0 });
        			return;
        		}
        		var beanForm = $('form#'+this._formid).toObject();
        		var s = {
        				'bean': beanForm,
        				'pageSize': 10,
        				'page': options.pageIndex };
        		
        		var b = $.stringifyJSON(s);
        		var self = this;
        		$.ajax({
        			type : 'POST',
        			url : $('form#'+this._formid).attr("action"),
        			data : b,
        			contentType : "application/json",
        			dataType : 'json',
        			complete : function(response, textStatus) {
        				var lr = jQuery.parseJSON(response.responseText);

        				if ( !reciveError(lr)) {
	                        var data = lr.data;
	                        var count = lr.rowCount;
	          				var startIndex = options.pageIndex * 10; // page size
	        				var endIndex = startIndex + 10;
	        				var end = (endIndex > count) ? count : endIndex;
	        				var pages = Math.ceil(count / 10);
	        				var page = options.pageIndex + 1;
	        				var start = startIndex + 1;
                          
                          
                          
                          // Allow client code to format the data
                          if (self._formatter) self._formatter(data);
  
                          // Return data to Datagrid
                          callback({ data: data, start: start, end: end, count: count, pages: pages, page: page });
        					
        				}
        			}
        		});
        		
                // TODO fazer por post
//                var url = 'http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=d6d798f51bbd5ec0a1f9e9f1e62c43ab&format=json';
//                var self = this;
//                url += '&tags=' + options.search;
//                url += '&per_page=' + options.pageSize;
//                url += '&page=' + (options.pageIndex + 1);

                // SORTING
//              if (options.sortProperty) {
//                      data = _.sortBy(data, options.sortProperty);
//                      if (options.sortDirection === 'desc') data.reverse();
//              }
                
//                $.ajax(url, {
//
//                        // Set JSONP options for Flickr API
//                        dataType: 'jsonp',
//                        jsonpCallback: 'jsonFlickrApi',
//                        jsonp: false,
//                        type: 'GET'
//
//                }).done(function (response) {
//                        
//                        // Prepare data to return to Datagrid
//                        var data = response.photos.photo;
//                        var count = response.photos.total;
//                        var startIndex = (response.photos.page - 1) * response.photos.perpage;
//                        var endIndex = startIndex + response.photos.perpage;
//                        var end = (endIndex > count) ? count : endIndex;
//                        var pages = response.photos.pages;
//                        var page = response.photos.page;
//                        var start = startIndex + 1;
//
//                        // Allow client code to format the data
//                        if (self._formatter) self._formatter(data);
//
//                        // Return data to Datagrid
//                        callback({ data: data, start: start, end: end, count: count, pages: pages, page: page });
//
//                });
        }
}