/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_modelling;

/**
 *
 * @author vshanmugham
 */


/*



user

movies

theatres

show times

seats and type

pay and book

concurrency


city
city has many theatres
theatres has many shows
show has many seats of different types and cost
show has a movie

user can book 1 or more seats in a show
user has many payments
user hash many seats in a show
Payments has many booked seats

user can search a movie based on a time filter, like today, or a specific date

models:
User        id, name, dob, email, phone number, saved cards, email verified
City        id, name
Theatres    id, name, location, city_id
TheatreMovieMapping id, theatre_id, movie_id
Shows       id, name, start_time, end_time, theatre_id, movie_id
Seats       id, type, cost, theatre_id, show_id, booked, seat_number
Movie       id, name, duration, adult
Payments    id, user_id, show_id, amt, payment_status
booking     id, user_id, payment_id, Qrcode, booking_status
BookedSeats id, payment_id, seat_id

select t.name theatres t inner join TheatreMovieMapping tm on t.id = tm.theatre_id inner join Movie m.id = tm.movie_id where
m.name like '%#{movie_name}%'


API:
selectcity
searchMovies
searchtheatres.  - 

Filter params:
theatre location
timeline
movie name
theatre name'

handle concurrency:
database locks
redis locks
or an external lock manager micro service

relational transactional database, with sharded for theatres
and sharding information can be stored


*/

public class MovieTicketBooking {
  
}
