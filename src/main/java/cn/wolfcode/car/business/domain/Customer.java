package cn.wolfcode.car.business.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    /** */
    private Long id;

    /** */
    private String name;

    /** */
    private Integer age;

    /** */
    private String phone;


}