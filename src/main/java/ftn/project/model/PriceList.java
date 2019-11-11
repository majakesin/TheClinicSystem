package ftn.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "PriceList")
public class PriceList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

//	@OneToOne
//    @MapsId
//	private Operation operation;

	@Column(name = "Price", nullable = false)
	private double price;

	@Column(name = "Dicount", nullable = false)
	private double discount;
	
//	@OneToOne(mappedBy = "priceList", cascade = CascadeType.ALL)
//	private Clinic clinic;
	
	
}
