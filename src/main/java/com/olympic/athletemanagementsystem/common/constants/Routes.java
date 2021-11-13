package com.olympic.athletemanagementsystem.common.constants;

public class Routes {
    //Gender Routes
    public static final String API_GENDER = "/api/gender";
    public static final String API_GENDER_BY_ID = "/{genderId}";

    //Athlete Routes
    public static final String API_ATHLETE = "/api/athletes";
    public static final String API_ATHLETE_BY_EVENT_ENABLED = "/event";
    public static final String API_ATHLETE_BY_GENDER = "/gender/{genderId}";
    public static final String API_ATHLETE_BY_ID = "/{athleteId}";

    //Event category Routes
    public static final String API_CATEGORY = "/api/categories";
    public static final String API_CATEGORY_BY_ID = "/{categoryId}";

    //Event category Routes
    public static final String API_EVENT = "/api/events";
    public static final String API_EVENT_BY_ID = "/{eventId}";
}