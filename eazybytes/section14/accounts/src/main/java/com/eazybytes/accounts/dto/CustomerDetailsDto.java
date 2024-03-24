package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name="Customer",
        description = "Schema to hold customer,account, cards and loans details"
)
public class CustomerDetailsDto {

    @Schema(
            description = "Name of the customer", example = "Manick"
    )
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 3, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email of the customer", example = "mem@mem.com"
    )
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile number of the customer", example = "9845098450"
    )
    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Loan details of the customer"
    )
    private LoansDto loansDto;

    @Schema(
            description = "Card details of the customer"
    )
    private CardsDto cardsDto;


}
