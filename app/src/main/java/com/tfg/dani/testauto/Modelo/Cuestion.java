package com.tfg.dani.testauto.Modelo;

public class Cuestion {
	
	private int id;
	private String urlFoto;
	private String pregunta,respuestaA,respuestaB,respuestaC;
	private String correcta;
	private int idTest;
	
	public Cuestion(int id, String urlFoto, String pregunta, String respuestaA, String respuestaB, String respuestaC,
			String correcta, int idTest) {
		super();
		this.id = id;
		this.urlFoto = urlFoto;
		this.pregunta = pregunta;
		this.respuestaA = respuestaA;
		this.respuestaB = respuestaB;
		this.respuestaC = respuestaC;
		this.correcta = correcta;
		this.idTest = idTest;
	}
	
	public Cuestion() {
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuestaA() {
		return respuestaA;
	}
	public void setRespuestaA(String respuestaA) {
		this.respuestaA = respuestaA;
	}
	public String getRespuestaB() {
		return respuestaB;
	}
	public void setRespuestaB(String respuestaB) {
		this.respuestaB = respuestaB;
	}
	public String getRespuestaC() {
		return respuestaC;
	}
	public void setRespuestaC(String respuestaC) {
		this.respuestaC = respuestaC;
	}
	public String getCorrecta() {
		return correcta;
	}
	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}
	public int getIdTest() {
		return idTest;
	}
	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

}
