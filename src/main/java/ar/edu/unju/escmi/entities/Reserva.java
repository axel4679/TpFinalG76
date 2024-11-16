package ar.edu.unju.escmi.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "salon_id", nullable = false)
	private Salon salon;
	@Column(nullable = false)
	private LocalDate fecha;
	@Column(nullable = false)
	private LocalTime hs_inicio;
	@Column(nullable = false)
	private LocalTime hs_fin;
	@Column(nullable = false)
	private double monto_pagado;
    @ManyToMany
    @JoinTable(
        name = "Reserva_ServiciosAdicionales",
        joinColumns = @JoinColumn(name = "reserva_id"), 
        inverseJoinColumns = @JoinColumn(name = "servicio_id") 
    )
    private List<ServiciosAdicionales> serviciosAdicionales;
	@Column(nullable = false)
	private double pago_adelantado;
	@Column(nullable = false)
	private boolean cancelado;
	@Column(nullable = false)
	private boolean estado;

	public Reserva() {
		// TODO Auto-generated constructor stub
	}

	public Reserva(Cliente cliente,
			// Salon salon,
			LocalDate fecha, LocalTime hs_inicio, LocalTime hs_fin, double monto_pagado,
			// List<ServiciosAdicionales> servicios_ad,
			double pago_adelantado, boolean cancelado, boolean estado) {
		super();
//		this.cliente = cliente;
//		this.salon = salon;
		this.fecha = fecha;
		this.hs_inicio = hs_inicio;
		this.hs_fin = hs_fin;
		this.monto_pagado = monto_pagado;
//		this.servicios_ad = servicios_ad;
		this.pago_adelantado = pago_adelantado;
		this.cancelado = cancelado;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public Cliente getCliente() {
//		return cliente;
//	}
//
//	public void setCliente(Cliente cliente) {
//		this.cliente = cliente;
//	}
//
//	public Salon getSalon() {
//		return salon;
//	}
//
//	public void setSalon(Salon salon) {
//		this.salon = salon;
//	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHs_inicio() {
		return hs_inicio;
	}

	public void setHs_inicio(LocalTime hs_inicio) {
		this.hs_inicio = hs_inicio;
	}

	public LocalTime getHs_fin() {
		return hs_fin;
	}

	public void setHs_fin(LocalTime hs_fin) {
		this.hs_fin = hs_fin;
	}

	public double getMonto_pagado() {
		return monto_pagado;
	}

	public void setMonto_pagado(double monto_pagado) {
		this.monto_pagado = monto_pagado;
	}

//	public List<ServiciosAdicionales> getServicios_ad() {
//		return servicios_ad;
//	}
//
//	public void setServicios_ad(List<ServiciosAdicionales> servicios_ad) {
//		this.servicios_ad = servicios_ad;
//	}

	public double getPago_adelantado() {
		return pago_adelantado;
	}

	public void setPago_adelantado(double pago_adelantado) {
		this.pago_adelantado = pago_adelantado;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

//	public double calcularCostoHorarioExtendido() {
//		// Hora máxima de reserva estándar (por ejemplo, 22:00)
//		LocalTime horaMaxima = LocalTime.of(23, 0);
//
//		// Si la hora de fin está después de la hora máxima, calcular las horas
//		// adicionales
//		if (hs_fin.isAfter(horaMaxima)) {
//			Duration duracionExtra = Duration.between(horaMaxima, hs_fin);
//			long horasExtras = duracionExtra.toHours();
//			return horasExtras * 10000; // $10,000 por cada hora extra
//		}
//		return 0;
//	}

//	public double calcularMontoTotal() {
//		double total = 0;
//		for (ServiciosAdicionales servicioAdicional : this.servicios_ad) {
//			total = total + servicioAdicional.getPrecio();
//		}
//		return total + calcularCostoHorarioExtendido();
//	}

//	public double calcularPagoPendiente() {
//		return calcularMontoTotal() - this.monto_pagado;
//	}

//	public void mostrarDatos() {
//		System.out.println("ID: " + this.id);
//		System.out.println("Cliente: " + this.cliente);
//		System.out.println("Salón: " + this.salon);
//		System.out.println("Fecha: " + this.fecha);
//		System.out.println("Hora de Inicio: " + this.hs_inicio);
//		System.out.println("Hora de Fin: " + this.hs_fin);
//		System.out.println("Monto Pagado: " + this.monto_pagado);
//		System.out.println("Servicios Adicionales: " + this.servicios_ad);
//		System.out.println("Pago Adelantado: " + this.pago_adelantado);
//		System.out.println("Cancelado: " + this.cancelado);
//		System.out.println("Estado: " + this.estado);
//	}

}
