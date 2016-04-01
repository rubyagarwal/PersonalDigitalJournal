<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head></head>

<frameset  cols="60%,*">
<frame  frameborder="0" noresize="noresize">
		<c:forEach var="ck" items="${data.fb}">
			<table class="fb" width="500px" height="110%">
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
						<td><img src="${ck.picture}" width="50%" height="50%"></img></td>
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
		</c:forEach>
		<!-- foursquare data -->

		<c:forEach var="fs" items="${data.fs}">
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
								<img src="images/like1.jpg" width="45" height="46" alt="" /> <span
									class="textdesign1">${fs.numberOfLikes} </span>
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
		</c:forEach>

		<c:forEach var="tw" items="${data.tw}">
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
								<%-- <c:if test="${tw.numberOfLikes > 0}"> --%>
									<img src="images/retweet.png" width="40" height="41" alt="" />
									<span class="textdesign1">${tw.retweetCount}</span>
								<%-- </c:if> --%>
							</div>
							<div
								style="display: inline-block; position: relative; top: 20px;">
								<%-- <c:if test="${tw.retweetCount > 0}"> --%>
									<img src="images/like1.jpg" width="45" height="46" alt="" />
									<span class="textdesign1">${tw.numberOfLikes} </span>
								<%-- </c:if> --%>
							</div>


						</td>
					</tr>
				</table>
				<div style="height: 20px; background-color: black"></div>
			</div>
		</c:forEach>
	</frame>
	<frame></frame>
</frameset>
<body>
</body>
</html>