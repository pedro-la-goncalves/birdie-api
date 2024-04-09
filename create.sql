create table accommodation (price float(53), id bigserial not null, description varchar(255), title varchar(255), primary key (id));
create table booking (departure date, entry date, accommodation_id bigint, check_in timestamp(6), check_out timestamp(6), guest_id bigint, id bigserial not null, primary key (id));
create table contact (guest_id bigint, id bigserial not null, type varchar(255) check (type in ('PHONE','EMAIL','OTHER')), value varchar(255) unique, primary key (id));
create table expense (multiplier float(53), value float(53), booking_id bigint, created_at timestamp(6), id bigserial not null, description varchar(255), title varchar(255), type varchar(255) check (type in ('AMOUNT','PERCENTAGE')), primary key (id));
create table guest (birthdate date, id bigserial not null, name varchar(255), social_name varchar(255), surname varchar(255), primary key (id));
alter table if exists booking add constraint FK5uxucbfmlrnnjunuxoei5ux0s foreign key (accommodation_id) references accommodation;
alter table if exists booking add constraint FKjn3lsroa8t8h7x5sld9b0ru2u foreign key (guest_id) references guest;
alter table if exists contact add constraint FKrgf04owibl59pe9j6m1gx5mwm foreign key (guest_id) references guest;
alter table if exists expense add constraint FKp6i5l1j8na3pcvyjxgfc5p9u5 foreign key (booking_id) references booking;
