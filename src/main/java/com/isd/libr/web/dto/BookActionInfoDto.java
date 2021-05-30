package com.isd.libr.web.dto;


import com.isd.libr.web.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookActionInfoDto {
    private String firstName;
    private String lastName;
    private String date;
    private String status;


   public static BookActionInfoDto from(String firstName,String lastName,String date,String status){
       BookActionInfoDto result = new BookActionInfoDto();
       result.setFirstName(firstName);
       result.setLastName(lastName);
       result.setDate(date);
       result.setStatus(status);
       return result;
   }


}
