package ro.sapientia.furniture.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDTO {

	private Long id;
	private Long orderId;
	private String street;
	private String nr;
	private String city;
	private int postCode;

}
