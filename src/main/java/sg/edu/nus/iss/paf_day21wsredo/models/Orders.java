package sg.edu.nus.iss.paf_day21wsredo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Orders {

    private Integer id;
    private Integer employeeID;
    private Integer customerID;
    private String orderDate;
    private String shippedDate;
    private Integer shipperID;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipStateProvince;
    private String shipZipPostalCode;
    private String shipCountryRegion;
    private String shippingFee;
    private String taxes;
    private String paymentType;
    private String paidDate;
    private String notes;
    private String taxRate;
    private Integer taxStatusID;
    private Integer statusID;

}