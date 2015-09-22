/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisal.model.wapper;

import br.unisal.model.Sensor;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jether
 */
@XmlRootElement(name = "sensores")
@XmlAccessorType (XmlAccessType.FIELD)
public class Sensores implements Serializable{
    @XmlElement(name = "sensor")
    private List<Sensor> sensores = null;

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }    
}
