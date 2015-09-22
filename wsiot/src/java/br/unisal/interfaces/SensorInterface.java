/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisal.interfaces;

import br.unisal.model.Sensor;
import java.util.List;

/**
 *
 * @author jether
 */
public interface SensorInterface {
    void insert(Sensor s);
    void update(Sensor s);
    void remove(Sensor s);    
    Sensor getById(Sensor s);
    List<Sensor> getAll();
}
