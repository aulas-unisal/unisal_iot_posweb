var arrayCategoria = [], arrayValores = [];
$(document).ready(function () { 
    loadArray();
    loadArrayHicharts();    
});

function loadArray(){
    $.ajax({
        'global':true,
        'async': false,
        'type': 'GET',
        'url': "http://localhost:8080/wsiot/api/sensor/",
        //'jsonpCallback': "i3sig",
        'dataType': 'json',
        'crossDomain': true,
        'success': function (data) {
            if (data.sensor.length > 0) {
                //console.log(data.sensor[0]);
                //data.sensor.reverse();
                for (var i = 0; i < data.sensor.length; i++) {
                    arrayCategoria.push(formatDateTime(data.sensor[i].dataInsercao));
                    arrayValores.push(parseFloat(data.sensor[i].temperatura));  
                };                
            }
        }
    });
}

function loadArrayHicharts(){
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    $('#container').highcharts({
        chart: {
            type: 'line',
            zoomType: 'x',                
            marginRight: 10
        },
        title: {
            text: 'Temperatura em tempo real'
        },
        xAxis: {            
            tickPixelInterval: 150,
            categories: arrayCategoria
        },
        yAxis: {
            title: {
                text: 'Valor'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            crosshairs: true,
            shared: true
        },
        legend: {
            enabled: true
        },
        exporting: {
            enabled: true
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        series: [{
            name: "Temperatura(C)",
            data: arrayValores
        }]
    });
}

function formatDateTime(string){
    var d = new Date(string);
    var dformat = [ d.getDate().padLeft(),
                    (d.getMonth()+1).padLeft(),
                    d.getFullYear()].join('/')+
                    ' ' +
                  [ d.getHours().padLeft(),
                    d.getMinutes().padLeft(),
                    d.getSeconds().padLeft()].join(':');
    return dformat;
}

Number.prototype.padLeft = function(base,chr){
   var  len = (String(base || 10).length - String(this).length)+1;
   return len > 0? new Array(len).join(chr || '0')+this : this;
}