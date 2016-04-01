<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal Digital Journal</title>
</head>

<base target="mainFrame">
<frameset cols="60%,*" onload="reloadPage()">

	<frame id="mainFrame" src="displayActivities.jsp" frameborder="0" noresize="noresize">

	<frameset rows="40%,*">

		<frame src="dateSelector.html" frameborder="0" noresize="noresize">
		<frame id="stats" src="viewStats.html" frameborder="0" noresize="noresize">
	</frameset>

</frameset>
<body >
<script type="text/javascript">
function reloadPage()
{
	window.location.reload();
}
</script>



</body>
</html>