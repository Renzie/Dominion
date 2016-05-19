<%--
  Created by IntelliJ IDEA.
  User: Yentl-PC
  Date: 17/05/2016
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dominion</title>
    <meta name="author" content="Laurens Visser"/>
    <link rel="stylesheet" type="text/css" href="lib/css/reset.css"/>
    <link rel="stylesheet" href="lib/css/styleGame.css" type="text/css"/>
</head>
<body>
<div id="wrapper">


    <ul class="overwinningskaarten">
        <li><img src="lib/images/kaarten/ProvinciePS.jpg" alt="province" title="province"/></li>
        <li><img src="lib/images/kaarten/HertogdomPS.jpg" alt="duchy" title="duchy::"/></li>
        <li><img src="lib/images/kaarten/LandgoedPS.jpg" alt="estate" title="estate"/></li>
    </ul>


    <ul class="stapel">
        <li><img src="lib/images/kaarten/Dief.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/achterkant.jpg" alt="avonturier" title="avonturier"/></li>
    </ul>


    <ul class="actiekaarten" id="actiekaarten">
        <li><span id="linebreaker"> </span></li>
    </ul>



    <div id="buttons">
        <input type="button" value="Gooi alle geldkaarten">
        <input type="button" value="Eindig je beurt">
    </div>
    <div id="log">
        <h2>Een cursekaart werd toegevoegd aan je deck</h2>
    </div>

    <div id="persoongegevens">
        <ul>

            <li id="acties">|ACTIES:</li>
            <li id="buys">|BUYS:</li>
            <li id="geld">|GELD:</li>
        </ul>

    </div>

    <ul class="kaartOpVeld">
        <li><img src="lib/images/kaarten/Goud.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/Goud.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/Goud.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/Goud.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/Goud.jpg" alt="gold" title="gold"></li>
    </ul>


    <ul class="geldcurse">
        <li><img src="lib/images/kaarten/Goud.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/Zilver.jpg" alt="silver" title="silver"></li>
        <li><img src="lib/images/kaarten/Koper.jpg" alt="copper" title="copper"></li>
        <li><img src="lib/images/kaarten/Vloek.jpg" alt="curse" title="curse"></li>
    </ul>


    <div id="speler">
        <ul>
            <li><img src="lib/images/dank.png" id="dank" alt="dank" title="dank"></li>

            <li id="naamspeler"></li>
        </ul>
    </div>

    <ul class="hand">
        <li><p>Hand :</p></li>
        <!--
        <li><img src="lib/images/kaarten/copper.jpg" alt="copper" title="copper"></li>
        <li><img src="lib/images/kaarten/copper.jpg" alt="copper" title="copper"></li>
        <li><img src="lib/images/kaarten/copper.jpg" alt="copper" title="copper"></li>
        <li><img src="lib/images/kaarten/estate.jpg" alt="estate" title="estate"></li>
        <li><img src="lib/images/kaarten/copper.jpg" alt="copper" title="copper"></li>-->
    </ul>
    <ul class="toonKaart">
        <li></li>
    </ul>

</div>




<script type="text/javascript" src="lib/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="lib/js/scriptGame.js"></script>
</body>
</html>
