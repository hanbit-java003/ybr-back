$(function() {
	var currentPage = 1;
	var rowsPerPage = 5;
	var pagesToShow = 2;
	var currentNo;
	
	function changeSection(section, init, data) {
		$('.board-section').hide();
		$('.board-' + section).show();
		
		if (init) {
			history.replaceState({}, null, '#' + section);
		}
		else {
			history.pushState({}, null, '#' + section);
		}
		
		if (section === 'list') {
			var page = data ? data : currentPage;
			
			requestList(page);
		}
		else if (section === 'edit') {
			if (data) {
				var no = data;
				
				requestDetail(no);
			}
			else {
				currentNo = null;
				clearEdit();
			}
		}
		else if (section === 'detail') {
			var no = data;
			
			clearReplyInput();
			requestDetail(no, true);
		}		
	}
	
	function requestDetail(no, isDetail) {
		$.ajax({
			url: '/detail',
			data: {
				no: no
			},
			success: function(result) {
				currentNo = no;
				
				if (isDetail) {
					setDetail(result);
					requestReplies();
				}
				else {
					setEdit(result);
				}
			}
		});
	}
	
	function requestReplies() {
		$.ajax({
			url: '/reply/list',
			data: {
				no: currentNo
			},
			success: function(result) {
				setReplies(result);
			}
		});
	}
	
	function setReplies(replies) {
		$('.replies').empty();
		
		for (var i=0; i<replies.length; i++) {
			var reply = replies[i];
			var rno = reply.rno;
			var writer = reply.writer;
			var contents = reply.contents.replace(/\n/g, '<br>');
			
			var replyHtml = '';
			replyHtml += '<li>';
			replyHtml += '<div class="reply-writer">' + writer + '</div>';
			replyHtml += '<div class="reply-contents">' + contents + '</div>';
			replyHtml += '<div class="reply-actions">';
			replyHtml += '<button rno="' + rno + '" class="reply-delete btn btn-danger btn-xs">삭제</button>';
			replyHtml += '</div>';
			replyHtml += '</li>';
			
			$('.replies').append(replyHtml);
		}
		
		handleReplyEvent();
	}
	
	function handleReplyEvent() {
		$('.reply-delete').on('click', function() {
			var no = currentNo;
			var rno = $(this).attr('rno');
			
			requestReplyDelete(no, rno);
		});
	}
	
	function requestReplyDelete(no, rno) {
		$.ajax({
			url: '/reply/delete',
			data: {
				no: no,
				rno: rno
			},
			success: function(result) {
				requestReplies();
			}
		});
	}
	
	function setDetail(article) {
		var title = article.title;
		var writer = article.writer;
		var contents = article.contents;
		
		contents = contents.replace(/\n/g, '<br>');
		
		$('#detail-title').html(title);
		$('#detail-writer').html(writer);
		$('#detail-contents').html(contents);
	}
	
	function setEdit(article) {
		var title = article.title;
		var writer = article.writer;
		var contents = article.contents;
		
		$('#edit-title').val(title);
		$('#edit-writer').val(writer);
		$('#edit-contents').val(contents);
	}
	
	function requestList(page) {
		$.ajax({
			url: '/list',
			data: {
				page: page
			},
			success: function(result) {
				currentPage = page;
				$('#board-list > tbody').fadeOut(200, function() {
					setList(result);
				});
			}
		});
	}
	
	function setList(rows) {
		$('#board-list > tbody').empty();
		
		for (var i=0; i<rows.length; i++) {
			var row = rows[i];
			
			var rowHtml = '<tr no="' + row.no + '">';
			rowHtml += '<td>' + row.no + '</td>';
			rowHtml += '<td>' + row.title;
			
			if (row.replyCount) {
				rowHtml += ' [' + row.replyCount + ']';
			}
			
			rowHtml += '</td>';
			rowHtml += '<td>' + row.writer + '</td>';
			rowHtml += '<td>' + row.views + '</td>';
			rowHtml += '</tr>';
			
			$('#board-list > tbody').append(rowHtml);
		}
		
		$('#board-list > tbody').fadeIn(200);
		
		handleRowEvent();
		requestPaging();
	}
	
	function handleRowEvent() {
		$('#board-list tbody tr').on('click', function() {
			var no = $(this).attr('no');
			
			changeSection('detail', false, no);
		});
	}
	
	function requestPaging() {
		$.ajax({
			url: '/total',
			success: function(result) {
				var total = result.total;
				
				setPaging(total);
			}
		});
	}
	
	function setPaging(total) {
		var rowsPerPage = 5;
		var totalPages = total / rowsPerPage
			+ (total % rowsPerPage === 0 ? 0 : 1);
		var firstPage = 1;
		var lastPage = totalPages;
		var startPage = parseInt((currentPage - 1) / pagesToShow)
			* pagesToShow + 1;
		var endPage = Math.min(startPage + pagesToShow - 1, lastPage);
		var prevPage = startPage - 1;
		var nextPage = endPage + 1;
		
		$('.board-list .pagination').empty();
		
		var pagingHtml = '';
		pagingHtml += '<li';
		
		if (prevPage < firstPage) {
			pagingHtml += ' class="disabled"';
		}
		
		pagingHtml += '>';
		pagingHtml += '<a class="board-page"';
		pagingHtml += ' page="' + prevPage + '"';
		pagingHtml += ' href="#" aria-label="Previous">';
		pagingHtml += '<span aria-hidden="true">&laquo;</span>';
		pagingHtml += '</a>';			
		pagingHtml += '</li>';
		
		for (var i=startPage; i<=endPage; i++) {
			pagingHtml += '<li';
			
			if (i === currentPage) {
				pagingHtml += ' class="active"';
			}
			
			pagingHtml += '><a class="board-page"';
			pagingHtml += ' page="' + i + '"';
			pagingHtml += ' href="#">' + i + '</a></li>';
		}
		
		pagingHtml += '<li';
		
		if (nextPage > lastPage) {
			pagingHtml += ' class="disabled"';
		}
		
		pagingHtml += '>';
		pagingHtml += '<a class="board-page"';
		pagingHtml += ' page="' + nextPage + '"';
		pagingHtml += ' href="#" aria-label="Next">';
		pagingHtml += '<span aria-hidden="true">&raquo;</span>';
		pagingHtml += '</a>';
		pagingHtml += '</li>';
		
		$('.board-list .pagination').html(pagingHtml);
		
		handlePagingEvent();
	}
	
	function handlePagingEvent() {
		$('.board-page').on('click', function(event) {
			event.preventDefault();
			
			if ($(this).parent('li').hasClass('disabled')) {
				return;
			}
			
			var page = parseInt($(this).attr('page'));
			
			requestList(page);
		});
	}
	
	function requestSave(params) {
		$.ajax({
			url: '/save',
			method: 'POST',
			data: params,
			success: function(result) {
				completeSave();
			}
		});
	}
	
	function clearEdit() {
		$('#edit-title').val('');
		$('#edit-writer').val('');
		$('#edit-contents').val('');
	}
	
	function completeSave() {
		if (currentNo) {
			changeSection('detail', false, currentNo);
		}
		else {
			changeSection('list', false, 1);
		}
	}
	
	$(window).on('popstate', function() {
		var section = location.hash.replace('#', '');
		
		changeSection(section);
	});
	
	$('#board-save').on('click', function() {
		var title = $('#edit-title').val().trim();
		var writer = $('#edit-writer').val().trim();
		var contents = $('#edit-contents').val().trim();
		
		if (!title) {
			alert('제목을 입력하세요.');
			$('#edit-title').val('');
			$('#edit-title').focus();
			return;
		}
		else if (!writer) {
			alert('작성자를 입력하세요.');
			$('#edit-writer').val('');
			$('#edit-writer').focus();
			return;
		}
		else if (!contents) {
			alert('내용을 입력하세요.');
			$('#edit-contents').val('');
			$('#edit-contents').focus();
			return;
		}
		
		requestSave({
			no: currentNo,
			title: title,
			writer: writer,
			contents: contents
		});
	});
	
	$('#board-delete').on('click', function() {
		requestDelete();
	});
	
	function requestDelete() {
		$.ajax({
			url: '/delete',
			data: {
				no: currentNo
			},
			success: function(result) {
				changeSection('list', false, 1);
			}
		});
	}
	
	$('#board-edit').on('click', function() {
		changeSection('edit', false, currentNo);
	});
	
	$('#reply-save').on('click', function() {
		var writer = $('#reply-writer').val().trim();
		var contents = $('#reply-contents').val().trim();
		
		if (!writer) {
			alert('댓글 작성자를 입력하세요.');
			$('#reply-writer').val('');
			$('#reply-writer').focus();
			return;
		}
		else if (!contents) {
			alert('댓글 내용을 입력하세요.');
			$('#reply-contents').val('');
			$('#reply-contents').focus();
			return;
		}
		
		clearReplyInput();
		
		requestReplySave(writer, contents);
	});
	
	function clearReplyInput() {
		$('#reply-writer').val('');
		$('#reply-contents').val('');
	}
	
	function requestReplySave(writer, contents) {
		$.ajax({
			url: '/reply/save',
			method: 'POST',
			data: {
				writer: writer,
				contents: contents,
				no: currentNo
			},
			success: function(result) {
				requestReplies();
			}
		});
	}
	
	$('.board-link').on('click', function() {
		var link = $(this).attr('link');
		
		changeSection(link);
	});
	
	changeSection('list', true);
});
