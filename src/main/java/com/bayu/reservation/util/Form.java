package com.bayu.reservation.util;

import lombok.Data;

public class Form {

    @Data
    public static class UserBookingForm {
        private String startDate;
        private String endDate;
        private String description;
        private String roomName;
    }

    @Data
    public static class BookingUpdateForm {
        private String startDate;
        private String endDate;
        private String description;
    }

    @Data
    public static class DateForm {
        String startDate;
        String endDate;
    }

    @Data
    public static class RoomForm {
        String name;
        Long departmentId;
    }
}
