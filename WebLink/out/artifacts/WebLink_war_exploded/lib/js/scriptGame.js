/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
<<<<<<< HEAD
    $(".actiekaarten, .overwinningskaarten, .geldcurse, .kaartOpVeld").on("click", "img", zoomIn);

=======
    gekozenkaarten = [];
    masterkaart = "";
    $(".actiekaarten, .overwinningskaarten, .geldcurse, .kaartOpVeld").on("click", "img", zoomIn);
>>>>>>> b5ba3f67a9e04fd720ac86fa9ab9e24a65fcadd7
    beginBeurtServlet();
    showActieKaarten();
    showPlayerName();
    showPlayerGegevens();
    showHand();
    showKoopOpties();
    $("#gooigeld").on("click", gooiGeld);
    $("#eindigbeurt").on("click", eindigBeurt);
<<<<<<< HEAD
    $(".hand").on("click", "img", speelActieKaart);
    //$("ul li .koopKaart").on("click", koopKaart);
=======
    if($("#ok").hasClass("hide")){
        $(".hand").on("click", "img", checkActiekaart);
    } else {
        $(".hand").on("click", "img", voegKaartToe);
    }
    $("#ok").on("click", function(){
        speelActieKaart(masterkaart, 2, gekozenkaarten)
        $("#ok").addClass("hide");
    });
    $("ul li .koopKaart").on("click", koopKaart);
>>>>>>> b5ba3f67a9e04fd720ac86fa9ab9e24a65fcadd7
});

var speelActieKaart = function(kaart, janee, lijstkaarten){
    $.ajax({
        type:"POST",
        url:"SpelerServlet",
        success: function(result){
            console.log(result[0]);
            if(result[0] == 0){
                $("#log").html("Je hebt geen acties meer over.");
            } else {
                $.ajax({
                    type:"POST",
                    data:{kaart:kaart, janee:janee, lijstkaarten:lijstkaarten},
                    url:"ActieKaartSpelenServlet",
                    success: function(result){
                        $(".kaartOpVeld").append("<li class='"+result+"'><img src='lib/images/kaarten/" + result + ".jpg' title='" + result + "'/></li>");
                        $(".hand").slice(1).remove("." + result + "");
                    }
                });
            }
        }
    });
    showPlayerGegevens();
    showHand();
};

function checkActiekaart(){
    var kaart = this.src;
    kaart = kaart.replace("http://localhost:8081/lib/images/kaarten/","");
    kaart = kaart.replace(".jpg","");
    
    switch(kaart){
        case "Kanselier":
            var answer = window.confirm("Wil je je deck op de aflegstapel plaatsen?");
            if(answer == true){
                speelActieKaart(kaart, 1, "");
            } else {
                speelActieKaart(kaart, 0, "");
            }
            break;
        case "Kelder":
            $("#log").html("Kies de kaarten die je wilt afleggen");
            masterkaart = "Kelder";
            $("#ok").removeClass("hide");
            break;
        default:
            speelActieKaart(kaart, 2, "");
            break;
    }
}

function voegKaartToe(){
    var kaart = this.src;
    kaart = kaart.replace("http://localhost:8081/lib/images/kaarten/","");
    kaart = kaart.replace(".jpg","");
    gekozenkaarten.push(kaart);
}

var koopKaart = function () {
    console.log(this);
    console.log("koopkaart connected");

    var kaart = this.id;
    console.log(kaart + " var kek");

    $.ajax({
        type:"POST",
        data:{kaart:kaart},
        url:"KoopKaartServlet",
        success: function (result) {
            console.log("kaart "+ result + " is gekocht");
        }
    });
    showPlayerGegevens();
    showKoopOpties();
};


var showKoopOpties = function () {
    $(".koopKaart").remove();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"KoopServlet",
        success: function(result) {
            for (i = 0; i<result.length; i++){
                $("#" + result[i] +"").append('<input type="button" value="koop" class ="koopKaart">').click(koopKaart);
            }
        }
    });
    showPlayerGegevens();
};

function beginBeurtServlet(){
    $.ajax({
        type:"POST",
        url:"BeurtServlet"
    });
    showKoopOpties();
}

var eindigBeurt = function(){
    $.ajax({
        type:"POST",
        url:"EindeBeurtServlet"
    });
    clearVeld();
    beginBeurtServlet();
    showPlayerName();
    showPlayerGegevens();
    showHand();
};

function clearVeld(){
    $(".kaartOpVeld").remove();
    $("#persoongegevens").after("<ul class='kaartOpVeld'></ul>")
    $("#log").html("");
}

var gooiGeld = function(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"GooiGeldServlet",
        success: function(result){
            for(i=0;i<result.length;i++){
                $(".kaartOpVeld").append("<li class='"+result[i]+"'><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + result[i] + "'/></li>");
                $(".hand").remove("."+result[i]+"");
            }
        }
    });
    showPlayerGegevens();
    showHand();
    showKoopOpties();
};

var zoomIn = function () {
    console.log("hey");
    if ($(".toonKaart li").has("img")) {
        $(".toonKaart li img").remove();
    }
    $(this).clone().appendTo(".toonKaart li").click(function () {
        $(this).remove();
    });
};

function showPlayerName() {
    $.ajax({
        type:"POST",
        url:"NaamServlet",
        success: function(result){
            $("#naamspeler").html(result);
        }
    })
}

function showActieKaarten(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"ActieKaartServlet",
        success: function(result){
            for(i=0;i<result.length/2;i++){

                $("#actiekaarten").prepend("<li id=" +result[i] +"><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + result[i] + "'/></li>");
            }
            for(i=result.length/2;i<result.length;i++){

                $("#actiekaarten").append("<li id=" +result[i] +"><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + result[i] + "'/></li>");
            }
        }
    })
}
function showHand() {
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"HandServlet",
        success: function (result) {

                $(".hand").html("<li class='" + result[0] + "'><img src='lib/images/kaarten/" + result[0] + ".jpg' title='" + "temp" + "'/></li>");
                console.log(result + " " + typeof result);
                if(result != 0){
                    for(i=1;i<result.length;i++){
                        $(".hand").append("<li class='" + result[i] + "'><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + "temp" + "'/></li>");
                    }
                
            }
        }
    })
}

function showPlayerGegevens() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "SpelerServlet",
        success: function (result) {
            $("#acties").html(result[0]);
            $("#buys").html(result[1]);
            $("#geld").html(result[2]);
        }
    })
}