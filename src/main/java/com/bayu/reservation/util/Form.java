package com.bayu.reservation.util;

import lombok.Data;

public class Form {

    @Data
    public static class UserBookingForm {
        private String dateStart;
        private String dateEnd;
        private String description;
        private String roomName;
    }

    @Data
    public static class BookingUpdateForm {
        private String dateStart;
        private String dateEnd;
        private String description;
    }

    @Data
    public static class DateForm {
        String start;
        String end;
    }

    @Data
    public static class RoomForm {
        String name;
        Long id;
    }
}
