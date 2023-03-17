CREATE SCHEMA reservations;
go CREATE TABLE reservations.airport(
        airport_code INTEGER,
        ap_state VARCHAR,
        city VARCHAR,
        ap_name VARCHAR,
        PRIMARY KEY(airport_code)
    );
CREATE TABLE reservations.can_land(airplane_type VARCHAR, airport INTEGER);
CREATE TABLE reservations.airplane_type(
    type_of_name VARCHAR,
    max_seats INTEGER,
    company VARCHAR PRIMARY KEY(type_of_name)
);
CREATE TABLE reservations.airplane(
    airplane_id INTEGER,
    total_seats INTEGER,
    airplane_type VARCHAR,
    PRIMARY KEY(airplane_id)
);
CREATE TABLE reservations.leg_instance(
    a_date DATE,
    available_seats INTEGER,
    airplane INTEGER,
    airport_departure INTEGER,
    airport_arrival INTEGER,
    departure_time TIME,
    arrival_time TIME,
    flight_leg INTEGER,
    PRIMARY KEY(a_date)
);
CREATE TABLE reservations.flight_leg(
    leg_number INTEGER,
    airport_departure INTEGER,
    airport_arrival INTEGER,
    sched_dep_time TIME,
    sched_arr_time TIME,
    flight_id INTEGER,
    PRIMARY KEY(leg_number)
);
CREATE TABLE reservations.flight(
    number INTEGER,
    airline VARCHAR,
    weekdays VARCHAR,
    PRIMARY KEY(number)
);
CREATE TABLE reservations.fare(
    code INTEGER,
    amount MONEY,
    restrictions VARCHAR,
    flight INTEGER,
    PRIMARY KEY(code)
);
CREATE TABLE reservations.seat(
    seat_number INTEGER,
    customer_name VARCHAR,
    cphone INTEGER,
    leg_instance DATE,
    PRIMARY KEY(seat_number)
);
ALTER TABLE reservations.can_land
ADD CONSTRAINT FK_CAN FOREIGN KEY (airport) REFERENCES reservations.airport(airport_code);
ALTER TABLE reservations.can_land
ADD CONSTRAINT FK_1 FOREIGN KEY (airplane_type) REFERENCES reservations.airplane_type(type_of_name);
ALTER TABLE reservations.airplane
ADD CONSTRAINT FK_2 FOREIGN KEY (airplane_type) REFERENCES reservations.airplane_type(type_of_name);
ALTER TABLE reservations.leg_instance
ADD CONSTRAINT FK_3 FOREIGN KEY (airplane) REFERENCES reservations.airplane(airplane_id);
ALTER TABLE reservations.leg_instance
ADD CONSTRAINT FK_4 FOREIGN KEY (airport_departure) REFERENCES reservations.airport(airport_code);
ALTER TABLE reservations.leg_instance
ADD CONSTRAINT FK_5 FOREIGN KEY (airport_arrival) REFERENCES reservations.airport(airport_code);
ALTER TABLE reservations.leg_instance
ADD CONSTRAINT FK_6 FOREIGN KEY (flight_leg) REFERENCES reservations.flight_leg(leg_number);
ALTER TABLE reservations.flight_leg
ADD CONSTRAINT FK_7 FOREIGN KEY (airport_departure) REFERENCES reservations.airport(airport_code);
ALTER TABLE reservations.flight_leg
ADD CONSTRAINT FK_8 FOREIGN KEY (airport_arrival) REFERENCES reservations.airport(airport_code);
ALTER TABLE reservations.flight_leg
ADD CONSTRAINT FK_9 FOREIGN KEY (flight_id) REFERENCES reservations.flight(number);
ALTER TABLE reservations.fare
ADD CONSTRAINT FK_10 FOREIGN KEY (flight) REFERENCES reservations.flight(number);
ALTER TABLE reservations.seat
ADD CONSTRAINT FK_11 FOREIGN KEY (leg_instance) REFERENCES reservations.leg_instance(a_date);