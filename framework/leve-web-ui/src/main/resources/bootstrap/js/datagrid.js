/*
 * Fuel UX Datagrid
 * https://github.com/ExactTarget/fuelux
 *
 * Copyright (c) 2012 ExactTarget
 * Licensed under the MIT license.
 */

$(function(){


	// DATAGRID CONSTRUCTOR AND PROTOTYPE

	var Datagrid = function (element, options) {
		this.$element = $(element);
		this.$thead = this.$element.find('thead');
		this.$tfoot = this.$element.find('tfoot');
		this.$footer = this.$element.find('tfoot th');
		this.$footerchildren = this.$footer.children().show().css('visibility', 'hidden');
		this.$topheader = this.$element.find('thead th');
		this.$searchcontrol = this.$element.find('.search');
		this.$pagesize = this.$element.find('.grid-pagesize');
		this.$pageselect = this.$element.find('.grid-pager select');
		this.$prevpagebtn = this.$element.find('.grid-prevpage');
		this.$nextpagebtn = this.$element.find('.grid-nextpage');
		this.$pageslabel = this.$element.find('.grid-pages');
		this.$countlabel = this.$element.find('.grid-count');
		this.$startlabel = this.$element.find('.grid-start');
		this.$endlabel = this.$element.find('.grid-end');

		this.$tbody = $('<tbody>').insertAfter(this.$thead);
		this.$colheader = $('<tr>').appendTo(this.$thead);

		this.options = $.extend(true, {}, $.fn.datagrid.defaults, options);
		this.options.dataOptions.pageSize = parseInt(this.$pagesize.val(), 10);
		this.columns = this.options.dataSource.columns();

		this.$nextpagebtn.on('click', $.proxy(this.next, this));
		this.$prevpagebtn.on('click', $.proxy(this.previous, this));
		this.$searchcontrol.on('click', $.proxy(this.searchChanged, this));
		this.$colheader.on('click', 'th', $.proxy(this.headerClicked, this));
		this.$pagesize.on('change', $.proxy(this.pagesizeChanged, this));
		this.$pageselect.on('change', $.proxy(this.pageChanged, this));

		this.renderColumns();

		if (this.options.stretchHeight) this.initStretchHeight();

		this.renderData();
	};

	Datagrid.prototype = {

		constructor: Datagrid,

		renderColumns: function () {
			var self = this;

			this.$footer.attr('colspan', this.columns.length);
			this.$topheader.attr('colspan', this.columns.length);

			$.each(this.columns, function (index, column) {
				var $th = null;
				if(column.funccol){
					$th = $('<th>').width('3%' );
				} else {
					$th = $('<th>').width(  column.width+ '%' ).data('property', column.property).text(column.label);
					if (column.sortable) $th.addClass('sortable')
					if(column.filtrable){
						var $fa = $('<span class="leve-popover" >').append($('<i class="icon-filter">'))
						$fa.popover({
							placement: 'bottom',
							html: true,
							content: function(){
								var that = this;
								//remove outras pop up
								
								var $r = $('<span class="leve-colfilterform">');
								
								var $el = $('#colf-'+clearDotsJquey(column.property)).find('.control-group');
								var $clone = $el.clone(true, true);
								
								$r.append($clone);
								
								var btn = $el.parents('.modal').find('.leve-filter').clone(true, true).on('mousedown', function(){
									$clone.find('input, select').each(function(){
										$el.find('#'+clearDotsJquey(this.id)).val($(this).val())
									});
								});
								
								$r.append(btn);
								
								$r.on('keyup', function ( e ) {
						            if(e.which == 27){
						            	$(that).popover('hide');
						            } else if(e.which == 13){
						            	btn.mousedown();
						            	btn.click();
						            }
						          });
								
								$r.focusout(function() {
									setTimeout(function(){
										if(!($r.find(':focus').length)){
											$(that).popover('hide');
										}
									},200);
										
								});
								
								return $r;
							}
						});
						$fa.on('shown', function(){
							var table = $fa.parents('table:first');
							var popober = table.find('.popover:visible');
							if(popober.position()){
								// controla posicionamento
								if( (popober.position().left + popober.width()) > (table.position().left + table.width()) ){
									var delta = (popober.position().left + popober.width()) - (table.position().left + table.width());
									var arrow = popober.find('.arrow');
									popober.css('left', (popober.position().left - delta)+'px');
									var newArrowPos = arrow.position().left + delta - 11;
									if(newArrowPos < popober.width()){
										arrow.css('left', newArrowPos+'px');
									}
								}
								// foca
								$('.control-label:visible:first').click();
							}
						});
//						$fa.on('hidden', function(){alert(2)}); dispara varias vezes pq dispara mesmo que o elemente ja esteja esconfido. toodoo
						$fa.appendTo($th);
					}
				}
				
				self.$colheader.append($th);
			});
			
		},

		updateColumns: function ($target, direction) {
			var className = (direction === 'asc') ? 'order icon-chevron-up' : 'order icon-chevron-down';
			this.$colheader.find('i.order').remove();
			this.$colheader.find('th').removeClass('sorted');
			$('<i>').addClass(className).appendTo($target);
			$target.addClass('sorted');
		},

		updatePageDropdown: function (data) {
			var pageHTML = '';

			for (var i = 1; i <= data.pages; i++) {
				pageHTML += '<option>' + i + '</option>';
			}

			this.$pageselect.html(pageHTML);
		},

		updatePageButtons: function (data) {
			if (data.page === 1) {
				this.$prevpagebtn.attr('disabled', 'disabled');
			} else {
				this.$prevpagebtn.removeAttr('disabled');
			}

			if (data.pages == 0 || data.page === data.pages) {
				this.$nextpagebtn.attr('disabled', 'disabled');
			} else {
				this.$nextpagebtn.removeAttr('disabled');
			}
		},

		renderData: function () {
			var self = this;

			this.$tbody.html(this.placeholderRowHTML(this.options.loadingHTML));

			this.options.dataSource.data(this.options.dataOptions, function (data) {
				var itemdesc = (data.count === 1) ? self.options.itemText : self.options.itemsText;

				self.$footerchildren.css('visibility', 'visible');

				self.$pageselect.val(data.page);
				self.$pageslabel.text(data.pages);
				self.$countlabel.text(data.count + ' ' + itemdesc);
				self.$startlabel.text(data.start);
				self.$endlabel.text(data.end);

				self.updatePageDropdown(data);
				self.updatePageButtons(data);

				self.$tbody.empty();
				if(data.data.length){
					$.each(data.data, function (index, row) {
						var rowEl = $('<tr />')
						$.each(self.columns, function (index, column) {
							//coluna de botao
							if(column.funccol){
								var btn = $('<span />',{
									'class' : 'leve-col-btn',
								});
								btn.click(function(){
									var fvals = column.funccol.split('\.');
									var f = window;
									var fcalo = window;
									for(var i = 0; i < fvals.length ; i++){
										if(i > 0){
											fcalo = f;
										}
										f = f[fvals[i]];
									}
									f.call(fcalo, row);
								});
								btn.append('<i class="'+column.icon+'"></i>');
								$('<td />').width('3%').appendTo(rowEl).append(btn);
							} else {
								var colEl = $('<td>' + Object.byString(row, column.property) + '</td>').width(  column.width+ '%' )
								rowEl.append(colEl);
							}
						});
						self.$tbody.append(rowEl);
					});
				} else {
					self.$tbody.html(self.placeholderRowHTML('0 ' + self.options.itemsText));
				}
				
				self.stretchHeight();

				self.$pageselect.val(data.page);
				self.$element.trigger('loaded');
			});

		},

		placeholderRowHTML: function (content) {
			return '<tr><td style="text-align:center;padding:20px;border-bottom:none;" colspan="' +
				this.columns.length + '">' + content + '</td></tr>';
		},

		headerClicked: function (e) {
			var $target = $(e.target);
			if (!$target.hasClass('sortable')) return;

			var direction = this.options.dataOptions.sortDirection;
			var sort = this.options.dataOptions.sortProperty;
			var property = $target.data('property');

			if (sort === property) {
				this.options.dataOptions.sortDirection = (direction === 'asc') ? 'desc' : 'asc';
			} else {
				this.options.dataOptions.sortDirection = 'asc';
				this.options.dataOptions.sortProperty = property;
			}

			this.options.dataOptions.pageIndex = 0;
			this.updateColumns($target, this.options.dataOptions.sortDirection);
			this.renderData();
		},

		pagesizeChanged: function (e) {
			this.options.dataOptions.pageSize = parseInt($(e.target).val(), 10);
			this.options.dataOptions.pageIndex = 0;
			this.renderData();
		},

		pageChanged: function (e) {
			this.options.dataOptions.pageIndex = parseInt($(e.target).val(), 10) - 1;
			this.renderData();
		},

		searchChanged: function (e) {
			this.options.dataOptions.search = true;
			this.options.dataOptions.pageIndex = 0;
			this.renderData();
		},

		previous: function () {
			this.options.dataOptions.pageIndex--;
			this.renderData();
		},

		next: function () {
			this.options.dataOptions.pageIndex++;
			this.renderData();
		},

		reload: function () {
			this.options.dataOptions.pageIndex = 0;
			this.renderData();
		},

		initStretchHeight: function () {
			this.$gridContainer = this.$element.parent();

			this.$element.wrap('<div class="datagrid-stretch-wrapper">');
			this.$stretchWrapper = this.$element.parent();

			this.$headerTable = $('<table>').attr('class', this.$element.attr('class'));
			this.$footerTable = this.$headerTable.clone();

			this.$headerTable.prependTo(this.$gridContainer).addClass('datagrid-stretch-header');
			this.$thead.detach().appendTo(this.$headerTable);

			this.$sizingHeader = this.$thead.clone();
			this.$sizingHeader.find('tr:first').remove();

			this.$footerTable.appendTo(this.$gridContainer).addClass('datagrid-stretch-footer');
			this.$tfoot.detach().appendTo(this.$footerTable);
		},

		stretchHeight: function () {
			if (!this.$gridContainer) return;

			this.setColumnWidths();

			var targetHeight = this.$gridContainer.height();
			var headerHeight = this.$headerTable.outerHeight();
			var footerHeight = this.$footerTable.outerHeight();
			var overhead = headerHeight + footerHeight;

			this.$stretchWrapper.height(targetHeight - overhead);
		},

		setColumnWidths: function () {
			if (!this.$sizingHeader) return;

			this.$element.prepend(this.$sizingHeader);

			var $sizingCells = this.$sizingHeader.find('th');
			var columnCount = $sizingCells.length;

			function matchSizingCellWidth(i, el) {
				if (i === columnCount - 1) return;
				$(el).width($sizingCells.eq(i).width());
			}

			this.$colheader.find('th').each(matchSizingCellWidth);
			this.$tbody.find('tr:first > td').each(matchSizingCellWidth);

			this.$sizingHeader.detach();
		}
	};


	// DATAGRID PLUGIN DEFINITION

	$.fn.datagrid = function (option) {
		return this.each(function () {
			var $this = $(this);
			var data = $this.data('datagrid');
			var options = typeof option === 'object' && option;

			if (!data) $this.data('datagrid', (data = new Datagrid(this, options)));
			if (typeof option === 'string') data[option]();
		});
	};

	$.fn.datagrid.defaults = {
		dataOptions: { pageIndex: 0, pageSize: 10 },
		loadingHTML: '<div class="progress progress-striped active" style="width:50%;margin:auto;"><div class="bar" style="width:100%;"></div></div>',
		itemsText: '',
		itemText: ''
	};

	$.fn.datagrid.Constructor = Datagrid;


});