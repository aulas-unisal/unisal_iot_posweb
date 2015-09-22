package br.unisal.model;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Sensor.class)
public abstract class Sensor_ {

	public static volatile SingularAttribute<Sensor, Date> dataInsercao;
	public static volatile SingularAttribute<Sensor, Integer> idSensor;
	public static volatile SingularAttribute<Sensor, Double> temperatura;

}

