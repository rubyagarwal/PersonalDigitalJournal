<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="data.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="Source/mootools-core.js" type="text/javascript"></script>
<script src="Source/mootools-more.js" type="text/javascript"></script>
<script src="Source/Picker.js" type="text/javascript"></script>
<script src="Source/Picker.Attach.js" type="text/javascript"></script>
<script src="Source/Picker.Date.js" type="text/javascript"></script>

<link href="Source/datepicker_jqui/datepicker_jqui.css"
	rel="stylesheet">

<script>

var v;
	window.addEvent('domready', function() {
		mydatePicker = new Picker.Date($$(".datepickers"), {
			pickOnly : 'days',

			pickerClass : 'datepicker_jqui',

			onSelect : function(date) {
				var v = document.getElementById('aa').value;
			}
		});

		mymonthPicker = new Picker.Date($$(".monthpicker"), {
			pickOnly : 'months',
			pickerClass : 'datepicker_jqui',
			onSelect : function(date) {
				var v = document.getElementById('bb').value;
			}
		});

		myyearPicker = new Picker.Date($$(".yearpicker"), {
			pickOnly : 'years',
			pickerClass : 'datepicker_jqui',
			positionOffset : {
				x : 50,
				y : 0
			},
			onSelect : function(date) {
				v = document.getElementById('cc').value;
			}
		});

	});
	
</script>
</head>

<body>
	<form action="register" method="post">
        	<input type="text" id="aa" class="datepickers" value="30 April 2014" name="name">
	<input type="text" id="bb" class="monthpicker" value="April 2014" name="group">
	<input type="text" id="cc" class="yearpicker" value="2014" name="pass">
        
        <input type="submit" value="submit">            
    </form>

</body>
</html>
