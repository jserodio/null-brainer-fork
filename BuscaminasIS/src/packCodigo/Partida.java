package packCodigo;

public class Partida {
	private int puntuacion;
	private int tiempo;
	private String tipo;
	private boolean acabado;
	private Tablero juego;
	private Usuario jugador;
	private String nombrePartida;

	public Partida(int puntuacion, int tiempo, String tipo, boolean acabado, Tablero juego, Usuario jugador, String nombrePartida) {
		this.puntuacion = puntuacion;
		this.tiempo = tiempo;
		this.tipo = tipo;
		this.acabado = acabado;
		this.juego = juego;
		this.jugador = jugador;
		this.nombrePartida= nombrePartida;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isAcabado() {
		return acabado;
	}

	public void setAcabado(boolean acabado) {
		this.acabado = acabado;
	}

	public Tablero getJuego() {
		return juego;
	}

	public void setJuego(Tablero juego) {
		this.juego = juego;
	}

	public Usuario getJugador() {
		return jugador;
	}

	public void setJugador(Usuario jugador) {
		this.jugador = jugador;
	}

	public String getNombrePartida() {
		return nombrePartida;
	}

	public void setNombrePartida(String nombrePartida) {
		this.nombrePartida = nombrePartida;
	}

}
