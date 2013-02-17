var LeveDataSource = function (options) {
        this._formatter = options.formatter;
        this._columns = options.columns;
        this._delay = options.delay || 0;
        this._data = options.data;
        this._listAction = options.listAction;
        this._modalid = options.modalid;
};

LeveDataSource.prototype = {
        /**
         * Returns stored column metadata
         */
        columns: function () {
                return this._columns;
        },
        
        data: function (options, callback) {
        		var beanForm = $('#'+this._modalid+' .modal-body').toObject();
        		var s = {
        				'bean': beanForm,
        				'pageSize': 50,
        				'page': options.pageIndex };
        		
        		var b = $.stringifyJSON(s);
        		var self = this;
        		$.ajax({
        			type : 'POST',
        			url : this._listAction,
        			data : b,
        			contentType : "application/json",
        			dataType : 'json',
        			complete : function(response, textStatus) {
        				var lr = jQuery.parseJSON(response.responseText);

        				if ( !reciveError(lr)) {
	                        var data = lr.data;
	                        var count = lr.rowCount;
	          				var startIndex = options.pageIndex * 50; // page size
	        				var endIndex = startIndex + 50;
	        				var end = (endIndex > count) ? count : endIndex;
	        				var pages = Math.ceil(count / 50);
	        				var page = options.pageIndex + 1;
	        				var start = startIndex + 1;
                          
                          
                          
                          // Allow client code to format the data
                          if (self._formatter) self._formatter(data);
  
                          // Return data to Datagrid
                          callback({ data: data, start: start, end: end, count: count, pages: pages, page: page });
        					
        				}
        			}
        		});
        }
}