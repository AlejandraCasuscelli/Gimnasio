 $(document).ready(function (){
	 $.get("getSucursales", function (data) {
		 var datos = JSON.parse(data);
		 cargarMapa(datos);
     });


 });



function cargarMapa(marcadores) {

    var mapOptions = {
        center:new google.maps.LatLng(-34.6357663,-58.4812772),
        zoom:12,
        panControl: true,
        zoomControl: true,
        scaleControl: false,
        mapTypeControl:false,
        streetViewControl:true,
        overviewMapControl:true,
        rotateControl:true,
        mapTypeId:google.maps.MapTypeId.ROADMAP
    };

    var map = new google.maps.Map(document.getElementById("mapa"),mapOptions);

    cargarMarcadores(marcadores, map);
   
}

function cargarMarcadores(marcadores, map){
    $.each(marcadores, function(i,item){
        var marker = i;
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(item['lat'],item['lng']),
            map: map
        });
        var info = new google.maps.InfoWindow({
            content: item['nombre']
        });

        google.maps.event.addListener(marker, 'click', function() {
            info.open(map,marker);
        });

    });
}