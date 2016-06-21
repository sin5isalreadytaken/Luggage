$(function(){
	var initUrl = '../Initialize';
	var checkinUrl = '../Checkin';
	var positionUrl = '../Position';
	
	function generateCheckboxItem(i, idStr){
		return "<label><input name=\"ID\" type=\"checkbox\" value=\"" + idStr + "\"/>" + idStr + "</label><br/>";
	}
	
	function generateRadioItem(i, rfidStr){
		return "<tr><td align=\"left\"><label><input name=\"RFID\" type=\"radio\" value=\"" + rfidStr + "\"/>" + rfidStr + "</label></td></tr><br/>";
	}
	
	function transferToTable(str){
		var strs = str.split('&');
		var ids = strs[0].split('|');
		var des = strs[1].split('|');
		var rfids = strs[2].split('|');
		var tableStr = '';
		for (var i = 0; i < ids.length; i++){
			tableStr += '<tr><td>' + ids[i] + '</td><td>' + des[i] + '</td><td>' + rfids[i] + '</td></tr>';
		}
		return tableStr;
	}
	
	$.get(initUrl, function(data, status){
		var strs = data.split('&');
		var idStr = strs[0].split('|');
		var checkbox1Str = '';
		for (var i = 0; i < idStr.length; i++){
			checkbox1Str += generateCheckboxItem(i + 1, idStr[i]);
		}
		$('#checkbox1').html(checkbox1Str);
		var rfidStr = strs[1].split('|');
		var radio1Str = '';
		for (var i = 0; i < rfidStr.length; i++){
			radio1Str += generateRadioItem(i + 1, rfidStr[i]);
		}
		$('#radio1').html(radio1Str);
	});
	
	$('#submitform1').click(function(){
		$('#table1').show();
		var idList = '';
		$('input[name="ID"]:checked').each(function(){
			idList += $(this).val() + '|';
		})
		idList = idList.substring(0, idList.length - 1);
		$.get(checkinUrl + '?ID=' + idList, function(data, status){
			$('#tbody1').html(transferToTable(data));
		});
		setTimeout(function(){
			$.get(initUrl, function(data, status){
				var strs = data.split('&');
				var idStr = strs[0].split('|');
				var checkbox1Str = '';
				for (var i = 0; i < idStr.length; i++){
					checkbox1Str += generateCheckboxItem(i + 1, idStr[i]);
				}
				$('#checkbox1').html(checkbox1Str);
				var rfidStr = strs[1].split('|');
				var radio1Str = '';
				for (var i = 0; i < rfidStr.length; i++){
					radio1Str += generateRadioItem(i + 1, rfidStr[i]);
				}
				$('#radio1').html(radio1Str);
			});
		}, 1000);
	});
	
	function loading(data){
		$('#loading').html(data + '->&nbsp&nbsp');
		setTimeout(function(){
			$('#loading').html(data + '-->&nbsp');
		}, 500);
		setTimeout(function(){
			$('#loading').html(data + '--->');
		}, 1000);
	}

	function get_data() {
		var rfid = $('input[name="RFID"]:checked').val();
		$.ajax({
			url: positionUrl + '?RFID=' + rfid,
			type: 'GET',
			success: function(data) {
				if (data.charAt(data.length - 1) != '!'){
					loading(data);
					setInterval(loading(data), 1500);
				}
				else{
					$('#loading').text(data);
				}
			}
		});
	}
	
	$('#submitform2').click(function() {
		get_data();
		setInterval(get_data, 3000);
	});
});
