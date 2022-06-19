package com.bayu.reservation.entities;

import com.bayu.reservation.entities.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bookings", uniqueConstraints = {
        @UniqueConstraint(name = "bookings_code_unique", columnNames = "code")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends UserDateAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

    @Column(name = "confirmed")
    private boolean confirmed = false;

    // 1 User bisa memiliki banyak Booking
    // tapi 1 Booking hanya bisa dimiliki oleh 1 User
    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "users_reservations",
            joinColumns = @JoinColumn(name = "booking_id", foreignKey = @ForeignKey(name = "fk_booking_id"), referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"), referencedColumnName = "id")
    )
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}
