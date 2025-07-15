package com.example.SpringBoot.Application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Schema(description = "Employee's address information")
public class AddressDTO {

    @NotBlank(message = "State is required")
    @Schema(description = "State of the employee", example = "Telangana", required = true)
    private String state;

    @NotBlank(message = "Area is required")
    @Schema(description = "Area name", example = "Kukatpally", required = true)
    private String area;

    @NotBlank(message = "District is required")
    @Schema(description = "District name", example = "Rangareddy", required = true)
    private String district;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "\\d{6}", message = "Pincode must be 6 digits")
    @Schema(description = "PIN Code", example = "500072", required = true)
    private String pincode;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
