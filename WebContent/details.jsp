<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/styleslook.css">
<script src="js/canvasjs.js" type="text/javascript"></script>
<script src="js/excanvas.js" type="text/javascript"></script>
<script type="text/javascript">
  renderChart = function (ck,ps,tw,name,cmts) {
	  var lk1 = name.split(",")[0];
	  var lk2 = name.split(",")[1];
	  var lk3 = name.split(",")[2];
	  
	  var cm1 = cmts.split(",")[0];
	  var cm2 = cmts.split(",")[1];
	  var cm3 = cmts.split(",")[2];
	  
	  var chart = new CanvasJS.Chart("chartContainer",
    {
      theme: "theme1",
      title:{
        text: "Activities"
      },
      legend:{
        verticalAlign: "bottom",
        horizontalAlign: "center"
      },
      data: [
      {       
       type: "pie",
       showInLegend: false,
       dataPoints: [
       {  y: ck, legendText:"Checkins", indexLabel: "Checkins" },
       {  y: ps, legendText:"Posts", indexLabel: "Posts" },
       {  y: tw, legendText:"Tweets" , indexLabel: "Tweets"}
       ]
     }
     ]
   });

    chart.render();
    
    var chart2 = new CanvasJS.Chart("chartContainer2",
    	    {
    	      title:{
    	      text: "Top Likers"
    	      },
    	     
    	      data: [
    	      {
    	        type: "stackedColumn",
    	        legendText: "Foursqaure",
    	        showInLegend: "true",
    	        dataPoints: [
    	        {  y: parseInt(lk1.split(":")[2]) , label: lk1.split(":")[0]},
    	        {  y: parseInt(lk2.split(":")[2]), label: lk2.split(":")[0]},
    	        {  y: parseInt(lk3.split(":")[2]), label: lk3.split(":")[0]}

    	        ]
    	      },  {
    	        type: "stackedColumn",
    	        legendText: "Facebook",
    	        showInLegend: "true",
    	        dataPoints: [
    	        {  y: parseInt(lk1.split(":")[3]) , label: lk1.split(":")[0]},
    	        {  y: parseInt(lk2.split(":")[3]), label: lk2.split(":")[0]},
    	        {  y: parseInt(lk3.split(":")[3]), label: lk3.split(":")[0]}

    	        ]
    	      }
    	      ]
    	    });

    	    chart2.render();
    	    
    	    
    	    var chart3 = new CanvasJS.Chart("chartContainer3",
    	    	    {
    	    	      title:{
    	    	      text: "Top Commenters"
    	    	      },
    	    	     
    	    	      data: [
    	    	      {
    	    	        type: "stackedColumn",
    	    	        legendText: "Foursqaure",
    	    	        showInLegend: "true",
    	    	        dataPoints: [
    	    	        {  y: parseInt(cm1.split(":")[2]) , label: cm1.split(":")[0]},
    	    	        {  y: parseInt(cm2.split(":")[2]), label: cm2.split(":")[0]},
    	    	        {  y: parseInt(cm3.split(":")[2]), label: cm3.split(":")[0]}

    	    	        ]
    	    	      },  {
    	    	        type: "stackedColumn",
    	    	        legendText: "Facebook",
    	    	        showInLegend: "true",
    	    	        dataPoints: [
    	    	        {  y: parseInt(cm1.split(":")[3]) , label: cm1.split(":")[0]},
    	    	        {  y: parseInt(cm2.split(":")[3]), label: cm2.split(":")[0]},
    	    	        {  y: parseInt(cm3.split(":")[3]), label: cm3.split(":")[0]}

    	    	        ]
    	    	      }
    	    	      ]
    	    	    });

    	    	    chart3.render();

  }
    </script>
</head>
<body
	onload="renderChart(${stats.totalCheckins},${stats.totalPosts},${stats.totalTweets},'${stats.topLikers}','${stats.topCommenters }')">
	<div style="display:block;overflow-y:scroll;height:99%">
	<table>
		<tr>
			<td ><c:forEach
					var="ck" items="${data.fb}">
					<table class="fb" width="500px" height="100%">
						<tr class="fbheader">
							<td class="fblogo"><img src="images/fbicon.jpg" width="80"
								height="80" alt="" style="float: left" />
								<p class="textdesign5">
									<c:if test="${! empty ck.message}">
							${ck.message} 
							</c:if>
								</p></td>
						</tr>
						<c:if test="${! ck.picture.equals('null') && ! empty ck.picture }">
							<tr>
								<td><img src="${ck.picture}"  alt="Image missing."> </img></td>
							</tr>
						</c:if>
						<tr class="check-infooter">
							<td>
								<div style="display: inline-block; position: relative;">
									<img src="images/like1.jpg" width="45" height="46" alt="" /> <span
										class="textdesign1">${ck.numberOfLikes} </span>
								</div>
								<div
									style="display: inline-block; position: relative; left: 30px;">
									<c:if test="${ck.numberOfComments > 0}">
										<img src="images/comment.jpg" width="40" height="41" alt="" />
										<span class="textdesign1">${ck.numberOfComments}</span>
									</c:if>
								</div>

							</td>
						</tr>
					</table>
					<div style="height: 20px; background-color: black"></div>
				</c:forEach> <!-- foursquare data --> <c:forEach var="fs" items="${data.fs}">
					<div>
						<table class="check-in" width="500">
							<tr class="check-inplace">
								<td><img src="images/checkin2.jpg" width="80" height="81"
									alt="" style="float: left" /> <span class="textdesign4">${fs.place.name}</span></td>
							</tr>

							<tr class="check-inplace">

								<td><span class="textdesign3">${fs.place.street},
										${fs.place.city}, ${fs.place.state}, ${fs.place.country}</span></td>
							</tr>
							<tr class="check-inmessage">
								<td>
									<p class="textdesign2">${fs.message}</p>
								</td>
							</tr>

							<tr class="check-inmessage">
								<td><span class="boxedtext" style="text-align: right">
										<c:forEach var="with" items="${fs.withTag}">
						${with}
						</c:forEach>


								</span> <img src="images/withwhom.jpg" width="80" height="81" alt=""
									style="float: right" /></td>
							<tr>
								<td><div style="height: 10px;"></div></td>
							</tr>
							<tr class="check-infooter">
								<td>
									<div style="display: inline-block;">
										<img src="images/like1.jpg" width="45" height="46" alt="" />
										<span class="textdesign1">${fs.numberOfLikes} </span>
									</div>
									<div
										style="display: inline-block; position: relative; left: 30px;">
										<c:if test="${fs.numberOfComments > 0}">
											<img src="images/comment.jpg" width="40" height="41" alt="" />
											<span class="textdesign1">${fs.numberOfComments}</span>
										</c:if>
									</div>

								</td>

							</tr>
						</table>
						<div style="height: 20px; background-color: black"></div>
					</div>
				</c:forEach> <c:forEach var="tw" items="${data.tw}">
					<div>
						<table class="tweet" width="500px">
							<tr class="tweetheader">
								<td class="tweetlogo"><img src="images/twitter.png"
									width="80" height="79" alt="" style="float: left" />


									<p class="textdesign5">${tw.message }</p></td>
							</tr>

							<tr class="tweetfooter">
								<td>
									<div
										style="display: inline-block; position: relative; left: 30px; top: 20px">
										<c:if test="${tw.numberOfLikes > 0}">
											<img src="images/retweet.png" width="40" height="41" alt="" />
											<span class="textdesign1">${tw.retweetCount}</span>
										</c:if>
									</div>
									<div
										style="display: inline-block; position: relative; top: 20px;">
										<c:if test="${tw.retweetCount > 0}">
											<img src="images/like1.jpg" width="45" height="46" alt="" />
											<span class="textdesign1">${tw.numberOfLikes} </span>
										</c:if>
									</div>


								</td>
							</tr>
						</table>
						<div style="height: 20px; background-color: black"></div>
					</div>
				</c:forEach>
				</td>
			<td rowspan = "5" style = "display:inline; position : relative; left:200px; top:10px">
				<div id="chartContainer" style="height: 400px; width: 400px;">
				</div> <div style = "display:inline; position : relative; top:30px; height: 50px;width: 50px;" id="chartContainer3" ></div><br></br>
				<div style = "display:inline; position : relative; top:460px; height: 50px;width: 50px;" id="chartContainer2" >
    </div> 
			</td></tr>
	</table>
</div>
</body>
</html>