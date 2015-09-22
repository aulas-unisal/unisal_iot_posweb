/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisal.ws;

import br.unisal.dao.SensorDao;
import br.unisal.model.Sensor;
import br.unisal.model.wapper.Sensores;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jether
 */
@Path("sensor")
public class SensorResource implements Serializable {

    @Context
    private UriInfo context;
    private SensorDao dao = new SensorDao();

    /**
     * Creates a new instance of SensorResource
     */
    public SensorResource() {
    }

    /**
     * Retrieves representation of an instance of br.unisal.ws.SensorResource
     *
     * @return a list of Sensor
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensores() {
        GenericEntity<List<Sensor>> sensores = new GenericEntity<List<Sensor>>(getDao().getAll()){};
        /*Sensores sensores = new Sensores();
        sensores.setSensores(new ArrayList<Sensor>());
        for (Sensor sensor : getDao().getAll()) {
            sensores.getSensores().add(sensor);
        }*/      
        return Response.ok(sensores).build();
    }

    /**
     * Retrieves representation of an instance of br.unisal.ws.SensorResource when receave a id
     * 
     * @param idSensor
     * @return an instance of Sensor
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sensor getSensor(@PathParam("id") Integer idSensor) {
        Sensor s = new Sensor();
        s.setIdSensor(idSensor);        
        return getDao().getById(s);
    }

    /**
     * DELETE method for deleting an instance of SensorResource
     * 
     * @param idSensor
     * @return message of deleted sensor with error or not
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSensor(@PathParam("id") Integer idSensor) {
        Sensor s = new Sensor();
        s.setIdSensor(idSensor);
        getDao().remove(s);
        String msg = "Sensor deletado com sucesso!";
        return Response.ok(msg).build();
    }

    /**
     * PUT method for updating or creating an instance of SensorResource
     *
     * @param sensor
     * @param idSensor
     * @return message of the operation done
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSensor(Sensor sensor, @PathParam("id") Integer idSensor) {
        sensor.setIdSensor(idSensor);
        getDao().update(sensor);
        String msg = "Sensor atualizado com sucesso!";
        return Response.ok(msg).build();
    }

    /**
     * POST method for creating an instance of SensorResource
     * 
     * @param s
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSensor(Sensor s) {
        s.setDataInsercao(new Date());
        getDao().insert(s);
        String msg = "Sensor criado com sucesso!";
        return Response.ok(msg).build();
    }

    public SensorDao getDao() {
        return dao;
    }
}
