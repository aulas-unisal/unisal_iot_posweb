<%-- 
    Document   : index
    Created on : 09/09/2015, 19:41:30
    Author     : jether
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Integração de Sistemas - Conceitos e IoT</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="refresh" content="30">        
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/datatables.bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-table.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.datatables.min.js"></script>
        <script src="js/bootstrap.datatables.min.js"></script>
        <script src="js/bootstrap-table.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/highcharts.js"></script>
        <script src="js/exporting.js"></script>        
        <script src="js/angularApp.js"></script>
        <script src="js/moment.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#tabelaDados').DataTable({
                    data: loadDataTable()
                });
            });
            function loadDataTable() {
                $.ajax({
                    'global': true,
                    'async': false,
                    'type': 'GET',
                    'url': "http://localhost:8080/wsiot/api/sensor/",
                    //'jsonpCallback': "i3sig",
                    'dataType': 'json',
                    'crossDomain': true,
                    'success': function (data) {
                        if (data.sensor.length > 0) {
                            for (var i = 0; i < data.sensor.length; i++) {
                                var html = '<tr><th width=\"150\">'
                                        + data.sensor[i].idSensor
                                        + '</th><th width=\"150\">'
                                        + data.sensor[i].temperatura
                                        + '</th><th>'
                                        + data.sensor[i].dataInsercao
                                        + '</th></tr>';
                                $("#tBodyValores").append(html);
                            }
                            ;
                        }
                    }
                });
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h2>Consumo dos dados via API de Sensor</h2><br/>
            <ul class="nav nav-tabs">
                <li><a data-toggle="tab" href="#tabela">Tabela com Bootstrap e AngularJS</a></li>
                <li class="active"><a data-toggle="tab" href="#grafico">Gráfico com Highcharts</a></li>    
            </ul>
            <div class="tab-content">
                <div id="tabela" class="tab-pane fade"
                     ng-app="sensorApp" ng-controller="sensorCtrl"><br/><br/>
                    <table id="tabelaDados" data-cache="false"                           
                           class="table table-striped table-bordered table-responsive" 
                           cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th width="150">ID</th>
                                <th width="150">Temperatura</th>
                                <th>Cadastro</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>ID</th>
                                <th>Temperatura</th>
                                <th>Cadastro</th>                
                            </tr>
                        </tfoot>
                        <tbody id="tBodyValores"></tbody>                        
                    </table>
                    <br/><br/>
                    <table id="tabelaAngular" class="table table-striped table-bordered table-responsive" cellspacing="0" width="100%" at-table at-paginated at-list="sensores" at-config="config">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Temperatura</th>
                                <th>Cadastro</th>                
                            </tr>
                        </thead> 
                        <tfoot>
                            <tr>
                                <th>ID</th>
                                <th>Temperatura</th>
                                <th>Cadastro</th>                
                            </tr>
                        </tfoot>
                        <tbody>
                            <tr ng-repeat="s in sensores">
                                <td at-implicit at-sortable width="150" at-initial-sorting="asc">{{ s.idSensor}}</td>
                                <td at-implicit at-sortable width="150">{{ s.temperatura}}</td>
                                <td at-implicit at-sortable>{{ s.dataInsercao}}</td>
                            </tr>                            
                        </tbody>
                    </table>
                    <at-pagination at-list="sensores" at-config="config"></at-pagination>
                </div>
                <div id="grafico" class="tab-pane fade in active">
                    <br/><br/>
                    <div id="container" style="width:80%; margin: 0 auto;"></div>      
                </div>    
            </div>
        </div>
        <script src="js/grafico.js"></script>
    </body>
</html>
