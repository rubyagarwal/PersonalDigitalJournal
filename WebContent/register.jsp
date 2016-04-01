<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				//alert(v);
			}
		});

		mymonthPicker = new Picker.Date($$(".monthpicker"), {
			pickOnly : 'months',
			pickerClass : 'datepicker_jqui',
			
			onSelect : function(date) {
				var v = document.getElementById('bb').value;
				//alert(v);
			}
		});

		myyearPicker = new Picker.Date($$(".yearpicker"), {
			pickOnly : 'years',
			pickerClass : 'datepicker_jqui',
			onSelect : function(date) {
				var s = document.getElementById('cc').value;
				//alert(s);
			}
		});

	});
</script>
</head>
<body background="images/b1.jpg">
	<center>
		<br> <br> <br>
		<div style="color: orange; font-size: 40px">Personal Digital
			Journal</div>
		<br> <br>
		<form method="post" action="getServlet">
			<table>
				<tr>
					<td><input
						style="border: 1px solid #848484; border-radius: 30px; outline: 0; height: 25px; width: 150px; padding-left: 10px; padding-right: 10px;"
						type="text" id="aa" class="datepickers" value="1 May 2014"
						name="name"> <br>
					<br>
					<br>
					<br>
					<input
						style="border: 1px solid #848484; border-radius: 30px; outline: 0; height: 25px; width: 150px; padding-left: 10px; padding-right: 10px;"
						type="text" id="bb" class="monthpicker" value="May 2014"
						name="group"><br> <br>
					<br>
					<br>
					<input
						style="border: 1px solid #848484; border-radius: 30px; outline: 0; height: 25px; width: 150px; padding-left: 10px; padding-right: 10px;"
						type="text" id="cc" class="yearpicker" value="2014"
						name="pass"></td>
					<td><span style="width: 100px;"></span></td>
					<td><span style="width: 10px;"></span>
					<td></td>
					<td><span style="width: 10px;"></span></td>
					<td><a href="timeline.html"><img src="images/timeline.png"
							width="200" height="200"></a></td>
				</tr>
				<tr>
					<td><span style="width: 10px;"></span></td>
					<td><span style="width: 10px;"></span></td>
					<td><input
						type="submit" value="Submit"></td>
					<td><span style="width: 10px;"></span></td>
					<td><span style="width: 10px;"></span></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>