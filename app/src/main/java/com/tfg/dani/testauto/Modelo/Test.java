package com.tfg.dani.testauto.Modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Test {
	
	private int id,contRealizado,suspendido,aprobado;
    private ArrayList<Cuestion>listPreguntas;
    private String uid; //le pongo un uid de acuerdo con la persistencia de datos firebase sea más segura
    
    private Date date= new Date();     //meter esta fecha de realizaci�n para que se ejecute en el programa.
	private String fechaRealizacion ="";



	public Test(int id, String uid, int contRealizado, int suspendido, int aprobado, ArrayList<Cuestion> listPreguntas) {
		
		super();
		this.id = id;
		this.uid=uid;
		this.contRealizado = contRealizado;
		this.listPreguntas = listPreguntas;
		this.aprobado=aprobado;
		this.suspendido=suspendido;
		fechaRealizacion = new SimpleDateFormat("dd/MM/yyyy").format(date);
		
	}
	
	public Test() {
		
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getSuspendido() {
		return suspendido;
	}

	public void setSuspendido(int suspendido) {
		this.suspendido = suspendido;
	}

	public int getAprobado() {
		return aprobado;
	}

	public void setAprobado(int aprobado) {
		this.aprobado = aprobado;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContRealizado() {
		return contRealizado;
	}
	public void setContRealizado(int contRealizado) {
		this.contRealizado = contRealizado;
	}
	public ArrayList<Cuestion> getListPreguntas() {
		return listPreguntas;
	}
	public void setListPreguntas(ArrayList<Cuestion> listPreguntas) {
		this.listPreguntas = listPreguntas;
	}
	public String getFechaRealizacion() {
		return fechaRealizacion;
	}
	public void setFechaRealizacion(String fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	
	
	
	
}
