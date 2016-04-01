<!DOCTYPE HTML>
<html>
<head>
<script src="js/canvasjs.js" type="text/javascript"></script>
<script src="js/excanvas.js" type="text/javascript"></script>
  <script type="text/javascript">
  renderChart = function (ck,ps,tw,name) {
	  var lk1 = name.split(",")[0];
	  var lk2 = name.split(",")[1];
	  var lk3 = name.split(",")[2];
	  
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
  }
  
  
  
  </script>
  <script type="text/javascript" src="/assets/script/canvasjs.min.js"></script>
  <body onload="renderChart(${stats.totalCheckins},${stats.totalPosts},${stats.totalTweets},'${stats.topLikers}')">
  <table>
  <tr>
  <td><div id="chartContainer" style="height: 200px;width: 200px;">
    </div></td>
  <td><div id="chartContainer2" style="height: 200px;width: 200px">
    </div></td>
  </tr>
  <tr>
  <td><div id="chartContainer3" style="height: 50%;width: 50%;">
    <p>dsmk</p></div></td>
  <td><div id="chartContainer3" style="height: 50%;width: 50%;"><p>dsmk</p>
    </div></td>
  </tr>
  </table>
    
 </body>
 </html>