<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>www.onlinemovietickets.com</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }
    header, footer {
      background-color: #333;
      color: #fff;
      text-align: center;
      padding: 15px 0;
    }
    .movies, .booking-form {
      padding: 20px;
      background-color: #fff;
    }
    .movie-list {
      display: flex;
      gap: 20px;
      flex-wrap: wrap;
    }
    .movie {
      background: #eee;
      padding: 10px;
      width: 200px;
      text-align: center;
      border-radius: 8px;
    }
    .movie img {
      width: 100%;
      height: auto;
      border-radius: 5px;
    }
    form {
      display: flex;
      flex-direction: column;
      gap: 10px;
      max-width: 400px;
    }
    button {
      background-color: #333;
      color: #fff;
      border: none;
      padding: 10px;
      cursor: pointer;
      border-radius: 5px;
    }
    button:hover {
      background-color: #555;
    }
    .seat-map {
      display: grid;
      grid-template-columns: repeat(5, 50px);
      gap: 10px;
      margin-top: 10px;
    }
    .seat {
      width: 50px;
      height: 50px;
      background-color: #ccc;
      border-radius: 5px;
      display: flex;
      align-items: center;
      justify-content: center;
      cursor: pointer;
    }
    .seat.selected {
      background-color: #6c5ce7;
      color: white;
    }
    .seat.occupied {
      background-color: #aaa;
      cursor: not-allowed;
    }
  </style>
</head>
<body>
  <header>
    <h1>Online Movie Ticket Booking</h1>
  </header>
  <fieldset>
    <section class="movies">
      <h2>Now Showing</h2>
      <div class="movie-list">
        <div class="movie">
          <img src="images3.jpg" alt="Movie 1" />
          <h3>HIT: The Third Case CBFC: A 2025</h3>
          <p>Show Times: 10:00 AM, 2:00 PM, 6:00 PM</p>
          <button onclick="bookMovie('HIT: The Third Case CBFC: A 2025')">Book Now</button>
        </div>
        <div class="movie">
          <img src="images.jpg" alt="Movie 2" />
          <h3>Hari Hara Veera Mallu 2025</h3>
          <p>Show Times: 11:00 AM, 3:00 PM, 7:00 PM</p>
          <button onclick="bookMovie('Hari Hara Veera Mallu 2025')">Book Now</button>
        </div>
      </div>
    </section>
    <section class="booking-form">
      <h2>Book Your Ticket</h2>
      <form onsubmit="return confirmBooking(event)">
        <label for="movie">Select Movie:</label>
        <select id="movie" name="movie" onchange="updateShowTimes()">
          <option value=""></option>
          <option value="HIT: The Third Case CBFC: A 2025">HIT: The Third Case CBFC: A 2025</option>
          <option value="Hari Hara Veera Mallu 2025">Hari Hara Veera Mallu 2025</option>
        </select>
        <label for="showtime">Select Show Time:</label>
        <select id="showtime" name="showtime"></select>
        <label for="date">Date:</label>
        <input type="date" id="date" name="date" />
        <label>Select Seats:</label>
        <div class="seat-map" id="seatMap"></div>
        <input type="hidden" id="selectedSeats" name="selectedSeats">
        <button type="submit">Confirm Booking</button>
      </form>
    </section>
    <footer>
      <p>&copy; 2025 MovieBooking.com</p>
    </footer>
  </fieldset>
  <script>
    const showTimes = {
      "HIT: The Third Case CBFC: A 2025": ["10:00 AM", "2:00 PM", "6:00 PM"],
      "Hari Hara Veera Mallu 2025": ["11:00 AM", "3:00 PM", "7:00 PM"]
    };
    const seatLabels = ["A1", "A2", "A3", "A4", "A5", "B1", "B2", "B3", "B4", "B5"];
    function bookMovie(movieName) {
      document.getElementById('movie').value = movieName;
      updateShowTimes();
      generateSeatMap();
      document.querySelector('.booking-form').scrollIntoView({ behavior: 'smooth' });
    }
    function updateShowTimes() {
      const movieSelect = document.getElementById('movie');
      const showtimeSelect = document.getElementById('showtime');
      const movieName = movieSelect.value;
      showtimeSelect.innerHTML = '';
      if (showTimes[movieName]) {
        showTimes[movieName].forEach(time => {
          const opt = document.createElement('option');
          opt.value = time;
          opt.textContent = time;
          showtimeSelect.appendChild(opt);
        });
      }
    }
    function generateSeatMap() {
      const seatMap = document.getElementById('seatMap');
      seatMap.innerHTML = '';
      seatLabels.forEach(label => {
        const seat = document.createElement('div');
        seat.className = 'seat';
        seat.textContent = label;
        seat.addEventListener('click', () => toggleSeatSelection(seat));
        seatMap.appendChild(seat);
      });
    }
    function toggleSeatSelection(seat) {
      if (seat.classList.contains('occupied')) return;
      seat.classList.toggle('selected');
      updateSelectedSeats();
    }
    function updateSelectedSeats() {
      const selected = Array.from(document.querySelectorAll('.seat.selected'))
        .map(seat => seat.textContent);
      document.getElementById('selectedSeats').value = selected.join(',');
    }
    function confirmBooking(e) {
      e.preventDefault();
      const movie = document.getElementById('movie').value;
      const showtime = document.getElementById('showtime').value;
      const date = document.getElementById('date').value;
      const seats = document.getElementById('selectedSeats').value;
      if (!movie || !showtime || !date || !seats) {
        alert("Please fill all fields and select seats.");
        return false;
      }
      alert(`Booking Confirmed!\nMovie: ${movie}\nShowtime: ${showtime}\nDate: ${date}\nSeats: ${seats}`);
      return false;
    }
  </script>
</body>
</html>
