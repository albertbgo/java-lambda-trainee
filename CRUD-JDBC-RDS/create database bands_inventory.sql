create database bands_inventory;
use bands_inventory;

CREATE TABLE bands (
    band_id int primary key auto_increment,
    band_name varchar(255),
    band_genre varchar(255),
    band_year_init date
);

insert into bands() values(null, 'Pink Floyd', 'Rock', '1966-01-01');